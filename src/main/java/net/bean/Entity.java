package net.bean;

/**
 * servlet的实体类
 */
public class Entity {
    private String name;
    private String classPath;

    public Entity() {
    }

    public Entity(String name, String classPath) {
        this.name = name;
        this.classPath = classPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", classPath='" + classPath + '\'' +
                '}';
    }
}
