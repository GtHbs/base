package objectoriented.constructor;

public class TriAngle {
    private float width;
    private float height;
    public TriAngle(){}

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float area()
    {
        return width * height / 2;
    }
}

/**
 * 初始化赋值顺序
 * (1)默认初始化
 * (2)显式初始化
 * (3)构造器初始化
 * (4)对象初始化
 */
class TriAngleTest{
    public static void main(String[] args) {
        TriAngle triAngle = new TriAngle();
        triAngle.setHeight(22.2f);
        triAngle.setWidth(10.2f);
        float area = triAngle.area();
        System.out.println(area);
    }
}
