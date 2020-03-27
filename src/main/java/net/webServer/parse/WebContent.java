package net.webServer.parse;

import net.bean.Entity;
import net.bean.EntityMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 保存web.xml类信息
 */
@SuppressWarnings("all")
public class WebContent {
    private List<Entity> entities = null;
    private List<EntityMapping> mappings = null;
    /**
     * 用来保存servlet的name和class
     * key 为name
     * value为 class
     */
    private Map<String, String> entityMap = new HashMap<>();
    /**
     * key为pattern
     * value为name
     */
    private Map<String, String> mappingMap = new HashMap<>();

    /**
     * 解析并保存信息
     *
     * @param entities webhandler属性
     * @param mappings webhandler属性
     */
    public WebContent(List<Entity> entities, List<EntityMapping> mappings) {
        this.entities = entities;
        this.mappings = mappings;
        for (Entity entity : entities) {
            entityMap.put(entity.getName(), entity.getClassPath());
        }
        for (EntityMapping mapping : mappings) {
            /**
             * url-pattern和name是多对一的关系
             * 因此key为url,value为name
             */
            for (String s : mapping.getPattern()) {
                mappingMap.put(s, mapping.getName());
            }
        }
    }

    /**
     * 根据pattern获取类名
     *
     * @param pattern
     * @return
     */
    public String getClass(String pattern) {
        String name = mappingMap.get(pattern);
        return entityMap.get(name);
    }
}
