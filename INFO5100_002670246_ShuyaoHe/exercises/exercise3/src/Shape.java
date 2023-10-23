import java.io.Serializable;

abstract class Shape implements Serializable {
    String color;


    public Shape(String color){
        this.color=color;
    }
    public String getColor(){
        return color;
    }
    protected abstract float calculateArea();
    protected abstract float calculatePerimeter();

}
