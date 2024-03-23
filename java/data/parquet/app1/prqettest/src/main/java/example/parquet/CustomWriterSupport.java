package example.parquet;

import java.util.HashMap;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.hadoop.api.WriteSupport;
import org.apache.parquet.io.ParquetEncodingException;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.io.api.RecordConsumer;
import org.apache.parquet.schema.MessageType;

public class CustomWriterSupport extends WriteSupport<List<String>> {

    MessageType schema;
    RecordConsumer recordConsumer;
    List<ColumnDescriptor> cols;

    /**
     * Constructor
     * 
     * @param schema
     */
    public CustomWriterSupport(MessageType schema) {
        this.schema = schema;
        this.cols = schema.getColumns();
    }


    @Override
    public WriteContext init(Configuration configuration) {
        return new WriteContext(schema, new HashMap<String, String>());
    }

    @Override
    public void prepareForWrite(RecordConsumer recordConsumer) {
        this.recordConsumer = recordConsumer;
    }

    @Override
    public void write(List<String> list) {
        if (list.size() != this.cols.size()) {
            throw new ParquetEncodingException(
                "Invalid input data. Expecting " + cols.size()
                + " columns. Input had " + list.size() + " columns ("
                + cols + ") : " + list);
        }

        recordConsumer.startMessage();
        for (int i = 0; i < cols.size(); ++i) {
            String value = list.get(i);
            if (value.length() > 0) {
                recordConsumer.startField(cols.get(i).getPath()[0], i);
                switch (cols.get(i).getPrimitiveType().getPrimitiveTypeName()) {
                    case BOOLEAN:
                         recordConsumer.addBoolean(Boolean.parseBoolean(value));
                         break;
                    case FLOAT:
                         recordConsumer.addFloat(Float.parseFloat(value));
                         break;
                    case DOUBLE:
                         recordConsumer.addDouble(Double.parseDouble(value));
                         break;
                    case INT32:
                         recordConsumer.addInteger(Integer.parseInt(value));
                         break;
                    case INT64:
                         recordConsumer.addLong(Long.parseLong(value));
                         break;
                    case BINARY:
                         recordConsumer.addBinary(this.stringToBinary(value));
                         break;
                    default:
                      throw new ParquetEncodingException(
                        "Unsupported column type: " + cols.get(i).getPrimitiveType().getPrimitiveTypeName()
                      );
                }
                recordConsumer.endField(cols.get(i).getPath()[0], i);
            }
        }
        recordConsumer.endMessage();
    }

    private Binary stringToBinary(Object value) {
        return Binary.fromString(value.toString());
    }
    
}
