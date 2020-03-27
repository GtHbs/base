package spring.simulate.ioc.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import spring.simulate.ioc.BeanDefinition;
import spring.simulate.ioc.BeanDefinitionReader;
import spring.simulate.ioc.BeanReference;
import spring.simulate.ioc.PropertyValue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 该类用于解析配置文件
 *
 * @author 李昭
 */
public class XmlBeanDefinitionReader implements BeanDefinitionReader {

    /**
     * 保存配置信息的map
     */
    private Map<String, BeanDefinition> registry;

    public XmlBeanDefinitionReader() {
        registry = new HashMap<>();
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        //使用文件流获取配置文件的字节流
        InputStream inputStream = new FileInputStream(location);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        //解析配置文件
        Document doc = docBuilder.parse(inputStream);
        //获取根节点(beans)
        Element root = doc.getDocumentElement();
        //解析bean
        parseBeanDefinitions(root);
    }

    private void parseBeanDefinitions(Element root) throws Exception {
        //获取每个bean结点
        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                //解析bean结点
                parseBeanDefinition(ele);
            }
        }
    }

    private void parseBeanDefinition(Element ele) throws Exception {
        //获取id
        String name = ele.getAttribute("id");
        String className = ele.getAttribute("class");
        //保存bean的容器
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName(className);
        //处理bean的属性
        processProperty(ele, beanDefinition);
        //一个id对于一个bean的配置信息
        registry.put(name, beanDefinition);
    }

    /**
     * 处理bean的属性信息
     *
     * @param ele
     * @param beanDefinition
     */
    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        NodeList propertyNodes = ele.getElementsByTagName("property");
        for (int i = 0; i < propertyNodes.getLength(); i++) {
            Node propertyNode = propertyNodes.item(i);
            if (propertyNode instanceof Element) {
                Element propertyElement = (Element) propertyNode;
                String name = propertyElement.getAttribute("name");
                String value = propertyElement.getAttribute("value");
                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                } else {
                    String ref = propertyElement.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("ref config error");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
                }
            }
        }
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }
}
