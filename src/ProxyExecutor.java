import com.sun.xml.internal.txw2.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class ProxyExecutor {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        String xml = "<ED462 EDAuthor=\"4525101000\" EDDate=\"2020-03-26\" EDNo=\"147507494\" xmlns=\"urn:cbr-ru:ed:v2.0\"><Request Sum=\"462\" Acc=\"11234567890123456789\" OperationDate=\"2020-03-26\" OperationType=\"2\" NamePBR=\"Организация получателя\" BicPBR=\"044583292\" NameClient=\"Организация отправителя\" OrgBIC=\"123456789\" DocDate=\"2020-03-26\" DocNo=\"462\"/><Cash ValsPackType=\"10\" Sum=\"462\" Nominal=\"100\" CashType=\"1\"/><CashInfo CashSum=\"446622\" CashCode=\"23\"/><CustomerData CustomerName=\"ФИО\"/><Annotation>Примечание</Annotation><CreatorInfo FIO=\"ФИО специалиста\" Position=\"специалист\"/></ED462>";

        System.out.println("The proxy pattern from MAIN method");
        org.w3c.dom.Document response = parseXmlFromString(xml);

        NodeList hiList = response.getElementsByTagName("ED462");

        for (int i = 0; i < hiList.getLength(); i++) {
            Node child = hiList.item(i);
            for(int j=0;j < child.getAttributes().getLength();j++) {
                System.out.println(child.getAttributes().item(j).getNodeName() + " : " + child.getAttributes().item(j).getNodeValue());
            }
            Node nextChild;
            for(int k=0;k < child.getChildNodes().getLength();k++ ){
                System.out.println("_________________________________");
                nextChild = child.getChildNodes().item(k);
                for(int j=0;j < nextChild.getAttributes().getLength();j++) {
                    System.out.println(nextChild.getAttributes().item(j).getNodeName() + " : " + nextChild.getAttributes().item(j).getNodeValue());
                }
            }
        }
    }



    public static org.w3c.dom.Document parseXmlFromString(String xmlString) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xmlString));
        org.w3c.dom.Document document = builder.parse(is);
        return document;
    }
}
