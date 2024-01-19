package example;

import java.io.FileInputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Hello world!
 *
 */
public class AppFail {
    public static void main( String[] args ) throws Exception {

        StringBuilder sb =new StringBuilder();
        sb.append("{\"Language\": \"Java\"}"); 

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(sb.toString()));
        Document doc = builder.parse(is);
        Element elm = doc.getDocumentElement();
        NodeList list = elm.getElementsByTagName("Language");

        for (int i=0; i<list.getLength(); i++) {
            Element e = (Element)list.item(i);
            System.out.println(e.getAttribute("id"));
            System.out.println(e.getAttribute("name"));
        }
    }
}
