package others.xml.parse;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import others.xml.bean.Entity;
import others.xml.bean.EntityMapping;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * 解析web.xml文件
 */
public class WebParser {
    public static void main(String[] args) throws Exception, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        WebXMLHandler handler = new WebXMLHandler();
        parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("others/xml/web.xml"), handler);
        List<Entity> entities = handler.getEntities();
        List<EntityMapping> mappings = handler.getMappings();
        for (Entity entity : entities) {
            System.out.println(entity);
        }
        for (EntityMapping mapping : mappings) {
            System.out.println(mapping);
        }
    }
}

/**
 * web.xml解析器
 */
class WebXMLHandler extends DefaultHandler {
    //保存所有的servlet
    private List<Entity> entities = new ArrayList<>();
    //保存所有的servlet-mapping
    private List<EntityMapping> mappings = new ArrayList<>();
    private Entity entity;
    private EntityMapping mapping;
    private String tag;
    //是否在操作servlet-mapping
    private boolean isMapping = false;

    public List<Entity> getEntities() {
        return entities;
    }

    public List<EntityMapping> getMappings() {
        return mappings;
    }

    /**
     * @param qName 标签名
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName != null) {
            tag = qName;
        }
        if (qName.equals("servlet")) {
            entity = new Entity();
        }
        if (qName.equals("servlet-mapping")) {
            isMapping = true;
            mapping = new EntityMapping();
        }
    }

    /**
     * 标签结束时需要将标记转为null否则会造成空指针异常
     *
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("servlet")) {
            entities.add(entity);
            tag = null;
        } else if (qName.equals("servlet-mapping")) {
            mappings.add(mapping);
            tag = null;
        }
    }

    /**
     * 获取每个标签的内容并且给对象赋值
     *
     * @param ch
     * @param start
     * @param length
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String msg = new String(ch, start, length).trim();
        //操作mapping
        if (isMapping && tag != null) {
            if (tag.equals("servlet-name") && msg.length() > 0) {
                mapping.setName(msg);
            }
            if (tag.equals("url-pattern") && msg.length() > 0) {
                mapping.addPattern(msg);
            }
        } else {        //操作servlet
            if (tag != null) {
                if (tag.equals("servlet-name") && msg.length() > 0) {
                    entity.setName(msg);
                }
                if (tag.equals("servlet-class") && msg.length() > 0) {
                    entity.setClassPath(msg);
                }
            }
        }
    }
}
