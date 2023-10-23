import java.io.Serializable;

public class Triangle extends Shape implements Serializable {
    Float side1;
    Float side2;
    Float side3;
    static String name="Triangle";
    public Triangle(String color, Float side1,Float side2,Float side3){
        super(color);
        this.side1=side1;
        this.side2=side2;
        this.side3=side3;
        System.out.println("Creation of "+name);
    }
    @Override
    public float calculateArea(){
        double s = (side1+side2+side3)/2;
        return  (float) (Math.sqrt(s * (s - side1) * (s - side2) * (s - side3)));
    }
    @Override
    public float calculatePerimeter(){
        return side1+side2+side3;
    }
}
