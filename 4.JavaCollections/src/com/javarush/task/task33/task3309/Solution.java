package com.javarush.task.task33.task3309;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.regex.Pattern;

/*
Комментарий внутри xml
Реализовать метод toXmlWithComment, который должен возвращать строку — xml представление объекта obj.
В строке перед каждым тегом tagName должен быть вставлен комментарий comment.
Сериализация obj в xml может содержать CDATA с искомым тегом. Перед ним вставлять комментарий не нужно.
Пример вызова:
toXmlWithComment(firstSecondObject, «second», «it’s a comment»)
Пример результата:
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<first>
<!--it's a comment-->
<second>some string</second>
<!--it's a comment-->
<second>some string</second>
<!--it's a comment-->
<second><![CDATA[need CDATA because of < and >]]></second>
<!--it's a comment-->
<second/>
</first>
Требования:
1. Количество комментариев вставленных в xml должно быть равно количеству тегов tagName.
2. Метод toXmlWithComment должен возвращать xml в виде строки преобразованной в соответствии с условием задачи.
3. Метод toXmlWithComment не должен изменять входящий xml в случае, если искомый тег отсутствует в нём.
4. Метод toXmlWithComment должен быть статическим.
5. Метод toXmlWithComment должен быть публичным.
*/
//Временное решение, которое не принимается. И.. здесь нужен обход всего DOM, и работа с узлами документа...
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) {
        try {

            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            marshaller.marshal(obj, doc);

            NodeList nodes = doc.getElementsByTagName("*");

            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);

                if (node.getNodeName().equals(tagName)) {
                    Comment com = doc.createComment(comment);
                    node.getParentNode().insertBefore(com, node);
                }
                replaceTextWithCDATA(node, doc);
            }
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            StringWriter sw = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void replaceTextWithCDATA(Node node, Document doc) {
        if ((node.getNodeType() == 3) && (Pattern.compile("[<>&'\"]").matcher(node.getTextContent()).find())) {

            Node cnode = doc.createCDATASection(node.getNodeValue());
            node.getParentNode().replaceChild(cnode, node);
        }

        NodeList list = node.getChildNodes();

        for (int i = 0; i < list.getLength(); i++) {
            replaceTextWithCDATA(list.item(i), doc);
        }
    }

    public static void main(String[] args) {
        String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><first><second>some string</second><second>some string</second><second><![CDATA[need CDATA because of < and >]]></second><second/></first>";
       String comment = "it's a comment";
        String tagName = "second";

       String res = null;
       if (s.indexOf(tagName) > -1)
            res = s.replace("<" + tagName + ">", "<!--" + comment + "-->" + "<" + tagName + ">");

        System.out.println(res);
    }
}
