package example;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {

        StringBuilder sb =new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        sb.append("<ProgramingList>");
        sb.append("<Language id=\"001\" name=\"Java\">Javaは標準的なプログラミング言語です</Language>");
        sb.append("<Language id=\"002\" name=\"Python\">Pythonは標準的なプログラミング言語です</Language>");
        sb.append("</ProgramingList>");

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
