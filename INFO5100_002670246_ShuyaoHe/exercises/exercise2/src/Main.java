// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        Circle circle=new Circle("red",2.0f);
        Triangle triangle=new Triangle("yellow",4.0f,4.0f,4.0f);
        Square square =new Square("black",3.0f);
        float circlePerimeter = circle.calculatePerimeter();
        float circleArea = circle.calculateArea();
        System.out.println("circleArea: "+circleArea);
        System.out.println("circlePerimeter: "+circlePerimeter);
        float trianglePerimeter = triangle.calculatePerimeter();
        float triangleArea = triangle.calculateArea();
        System.out.println("trianglePerimeter: "+trianglePerimeter);
        System.out.println("triangleArea: "+triangleArea);
        float squareArea = square.calculateArea();
        float squarePerimeter=square.calculatePerimeter();
        System.out.println("squareAre: "+squareArea);
        System.out.println("squarePerimeter: "+squarePerimeter);
    }
}