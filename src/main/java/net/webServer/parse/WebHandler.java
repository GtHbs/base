package net.webServer.parse;

import net.bean.Entity;
import net.bean.EntityMapping;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理器
 */
@SuppressWarnings("all")
public class WebHandler extends DefaultHandler {
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
     * 每个标签开始
     *
     * @param qName 标签名
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName != null) {
            tag = qName;        //每次解析都保存当前标签名
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