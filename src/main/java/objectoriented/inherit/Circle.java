package objectoriented.inherit;

import static java.lang.Math.*;

/**
 * @author 李昭
 */
public class Circle {
    private double radius;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
    public Circle()
    {
        radius = 1;
    }
    public double findArea()
    {
        return pow(radius,2) * PI;
    }

}
