package others.xml.parse;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import others.xml.bean.Person;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;

public class XMLTest {
    public static void main(String[] args) throws Exception{
        //获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //从解析工厂中获取解析器
        SAXParser parser = factory.newSAXParser();
        PersonHandler handler = new PersonHandler();
        parser.parse(PersonHandler.class.
                getResourceAsStream("person.xml"),handler);
        List<Person> list = handler.getList();
        for (Person person : list) {
            System.out.println(person);
        }
    }
}
class PersonHandler extends DefaultHandler {
    private List<Person> list;
    private Person person;
    private String tag;         //存储当前操作的标签名

    public List<Person> getList() {
        return list;
    }

    //文档开始
    @Override
    public void startDocument() throws SAXException {
        list = new ArrayList<>();
    }
    //文档结束
    @Override
    public void endDocument() throws SAXException {}

    //每个标签内的内容
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String msg = new String(ch,start,length).trim();
        /**
         * <person>这一段也是标签内的内容会被判空
         *     <name></name>这一段会被判定为name的标签内容因此在此标签结束后需要将tag标记为null
         * </person>
         */
        if (tag != null && tag.equals("name") && msg.length() > 0) {
            person.setName(msg);
        } else if (tag != null && tag.equals("age") && msg.length() > 0) {
            person.setAge(Integer.parseInt(msg));
        }
    }
    //标签开始,qName为标签名
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName != null) {
            tag = qName;
        }
        if (qName.equals("person")) {
            person = new Person();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("person")) {
            list.add(person);
        }
        tag = null;
    }
}