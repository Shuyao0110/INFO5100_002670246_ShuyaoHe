import java.io.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

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

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("circle.txt"));
            oos.writeObject(circle);
            oos.close();
            System.out.println("circle output finished");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("triangle.txt"));
            oos.writeObject(triangle);
            oos.close();
            System.out.println("triangle output finished");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("square.txt"));
            oos.writeObject(square);
            oos.close();
            System.out.println("square output finished");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream("circle.txt"));
            Circle circle1=(Circle) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream("triangle.txt"));
            Triangle triangle1=(Triangle) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream("square.txt"));
            Square square1=(Square) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}