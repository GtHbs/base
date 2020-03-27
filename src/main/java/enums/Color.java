package enums;

/**
 * @author: 李昭
 * @Date: 2020/3/23 19:41
 */
public enum Color {
    /**
     * 颜色枚举
     */
    RED("红色",100),
    YELLOW("黄色",200),
    GREEN("绿色",300)
    ;
    private String name;
    private int code;

    Color() {
    }

    Color(final String name, final int code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public String toString() {
        return "Color{" +
                "name='" + name + '\'' +
                ", code=" + code +
                '}';
    }
}
