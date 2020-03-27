package net.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * servlet-mapping的实体类
 */
public class EntityMapping {
    private String name;
    private Set<String> pattern;

    public EntityMapping() {
        pattern = new HashSet<>();
    }

    public EntityMapping(String name, Set<String> pattern) {
        this.name = name;
        this.pattern = pattern;
    }
    public void addPattern(String p) {
        pattern.add(p);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPattern() {
        return pattern;
    }

    public void setPattern(Set<String> pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return "EntityMapping{" +
                "name='" + name + '\'' +
                ", pattern=" + pattern +
                '}';
    }
}
