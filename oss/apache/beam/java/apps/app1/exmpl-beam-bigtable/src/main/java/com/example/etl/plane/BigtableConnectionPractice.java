// package com.example.etl.plane;


// import java.io.IOException;

// import org.apache.hadoop.conf.Configuration;
// import org.apache.hadoop.hbase.TableName;
// import org.apache.hadoop.hbase.client.Connection;
// import org.apache.hadoop.hbase.client.Get;
// import org.apache.hadoop.hbase.client.Result;
// import org.apache.hadoop.hbase.client.Table;
// import org.apache.hadoop.hbase.util.Bytes;

// import com.google.cloud.bigtable.hbase.BigtableConfiguration;
// import com.google.cloud.bigtable.hbase.BigtableOptionsFactory;

// public class BigtableConnectionPractice {

//     public static void main(String[] args) throws Exception {
//         String projectId = "fake";
//         String instanceId = "fake";
//         String tableId = "student_class_school_master";

//         Configuration config = BigtableConfiguration.configure(projectId, instanceId);
//         config.set(BigtableOptionsFactory.BIGTABLE_ADMIN_HOST_KEY, "localhost");
//         config.set(BigtableOptionsFactory.BIGTABLE_HOST_KEY, "localhost");
//         config.set(BigtableOptionsFactory.BIGTABLE_PORT_KEY, "8086");
//         config.set(BigtableOptionsFactory.BIGTABLE_NULL_CREDENTIAL_ENABLE_KEY, "true");
//         config.set(BigtableOptionsFactory.BIGTABLE_USE_PLAINTEXT_NEGOTIATION, "true");

//         try (Connection con = BigtableConfiguration.connect(config)) {
//             try(Table table = con.getTable(TableName.valueOf(tableId))) {

//                 String rowKey = "123456#elementary";
//                 Result result = table.get(new Get(Bytes.toBytes(rowKey)));

//                 //String rowValue = Bytes.toString(result.value());
//                 String rowValue = Bytes.toString(result.getValue("student".getBytes(), "student_name".getBytes()));

//                 System.out.println(rowValue);

//             } catch (Exception e) {
//                 e.printStackTrace();
//                 throw e;
//             }
            
//         } catch (IOException ex) {
//             ex.printStackTrace();
//             throw ex;
//         }
//     }

// }
