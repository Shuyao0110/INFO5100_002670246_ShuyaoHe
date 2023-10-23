import java.io.Serializable;

public class Square extends Shape implements Serializable {
    Float side;
    static String name="Square";

    public Square(String color, Float side){
        super(color);
        this.side=side;
        System.out.println("Creation of "+name);
    }
    public void setSide(String color){
        this.side=side;
    }

    @Override
    public float calculateArea(){
        return side*side;
    }
    @Override
    public float calculatePerimeter(){
        return 4*side;
    }
}
