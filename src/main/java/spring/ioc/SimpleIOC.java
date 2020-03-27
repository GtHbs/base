package spring.ioc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * IOC容器
 *
 * @author: 李昭
 * @Date: 2020/3/25 16:34
 */
@SuppressWarnings("all")
public class SimpleIOC {

    /**
     * 用来存储bean的map
     */
    private Map<String, Object> beanMap = new HashMap<>();

    /**
     * 根据构造器获取配置文件,并解析
     *
     * @param location
     */
    public SimpleIOC(String location) throws Exception {
        loadBeans(location);
    }

    /**
     * 获取bean(根据名字获取)
     *
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) {
        Object bean = beanMap.get(beanName);
        if (Objects.isNull(bean)) {
            throw new IllegalArgumentException("there is no bean with name " + beanName);
        }
        return bean;
    }

    private void loadBeans(String location) throws Exception {
        FileInputStream inputStream = new FileInputStream(location);
        /**
         * 文档解析器
         */
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        /**
         * 解析文档
         */
        Document doc = builder.parse(inputStream);
        /**
         * 获取根元素
         */
        Element root = doc.getDocumentElement();
        //获取子节点
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); ++i) {
            //获取结点
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Element element = (Element) node;
                String id = element.getAttribute("id");
                String className = element.getAttribute("class");
                Class beanClass = null;
                try {
                    beanClass = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return;
                }
                //创建bean
                Object bean = beanClass.getConstructor().newInstance();
                //遍历property标签
                NodeList propertyNodes = element.getElementsByTagName("property");
                for (int j = 0; j < propertyNodes.getLength(); ++j) {
                    Node propertyNode = propertyNodes.item(j);
                    if (propertyNode instanceof Element) {
                        Element propertyElement = (Element) propertyNode;
                        String name = propertyElement.getAttribute("name");
                        String value = propertyElement.getAttribute("value");
                        Field field = bean.getClass().getDeclaredField(name);
                        field.setAccessible(true);
                        if (value != null && value.length() > 0) {
                            field.set(bean, value);
                        } else {
                            String ref = propertyElement.getAttribute("ref");
                            if (ref == null || ref.length() == 0) {
                                throw new IllegalArgumentException("ref config error");
                            }
                            field.set(bean, getBean(ref));
                        }
                        registerBean(id, bean);
                    }
                }
            }
        }
    }

    private void registerBean(String beanName, Object bean) {
        beanMap.put(beanName, bean);
    }
}
