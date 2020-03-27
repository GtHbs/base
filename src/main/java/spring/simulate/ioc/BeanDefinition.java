package spring.simulate.ioc;

/**
 * @author 李昭
 */
public class BeanDefinition {

    /**
     * bean本身
     */
    private Object bean;

    /**
     * 类信息
     */
    private Class beanClass;

    /**
     * 类名
     */
    private String beanClassName;

    /**
     * 属性
     */
    private PropertyValues propertyValues = new PropertyValues();

    public BeanDefinition() {}

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        try {
            this.beanClass = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Object getBean() {
        return bean;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}