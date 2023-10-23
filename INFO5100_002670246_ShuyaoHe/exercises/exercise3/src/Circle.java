import java.io.Serializable;

public class Circle extends Shape implements Serializable {
    public float radius;
    static String name="Circle";
    public Circle(String color, Float radius){
        super(color);
        this.radius=radius;
        System.out.println("Creation of "+name);
    }
    @Override
    public float calculateArea(){
        return (float)(Math.PI*radius*radius);
    }

    @Override
    protected float calculatePerimeter() {
        return (float)(Math.PI*2*radius);
    }
}
