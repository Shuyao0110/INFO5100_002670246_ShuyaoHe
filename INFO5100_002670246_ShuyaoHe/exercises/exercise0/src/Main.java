import firstclass.*;
import firstclass.secondaryclass.*;

import java.util.ArrayList;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

//      University
        ArrayList<String> colleges1 = new ArrayList<String>();
        colleges1.add("Engineering");
        colleges1.add("Chemistry");
        colleges1.add("Social Science");
        University northeasternUniversity = new University(colleges1,"Northeastern University");

        ArrayList<String> colleges2 = new ArrayList<String>();
        colleges2.add("Computer");
        colleges2.add("Civil Engineering");
        colleges2.add("Social Science");
        University northwesternUniversity = new University(colleges2,"Northwestern University");

        ArrayList<String> colleges3 = new ArrayList<String>();
        colleges3.add("Design");
        colleges3.add("Architecture");
        colleges3.add("Literature");
        University Harvard = new University(colleges3,"Harvard University");

        Theater theater1 = new Theater(500,5,"5th Avenue","Braodway");
        Theater theater2 = new Theater(600,5,"San Jose","ACM");
        Theater theater3 = new Theater(700,5,"New York","Pacific Cinema");

        TeachingBuilding southBuilding1 = new TeachingBuilding(1000,3,"1239 Siping Road");
        TeachingBuilding southBuilding2 = new TeachingBuilding(1200,4,"4800 Caoan Express");
        TeachingBuilding southBuilding3 = new TeachingBuilding(1300,5,"3451 Downtown San Jose");

        Kindergarten kindergarten1 = new Kindergarten(12000,3,"Pudong District","小树苗");
        Kindergarten kindergarten2 = new Kindergarten(10500,2,"4th Street, San Jose","City Kindergarten");
        Kindergarten kindergarten3 = new Kindergarten(10030,4,"Pudong District","小树苗");

        Gymnasium sportsCenter1 = new Gymnasium(810,1,"Old Oakland Road","large");
        Gymnasium sportsCenter2 = new Gymnasium(820,1,"5th Road","medium");
        Gymnasium sportsCenter3 = new Gymnasium(830,1,"Foo Kwai Street","small");

        Garden garden1 = new Garden(1000,1,"Queensway");
        Garden garden2 = new Garden(1200,1,"Kennedy Road");
        Garden garden3 = new Garden(1300,1,"Borrett Rd");

        House house1 = new House(80,2,"Hoi King Street");
        House house2 = new House(90,3,"Cherry Street");
        House house3 = new House(100,2,"Tai Kok Tsui Road");

        Library library1 = new Library(500,3,"Lai Chi Kok Rd");
        Library library2 = new Library(600,3,"Kimberly Street");
        Library library3 = new Library(700,3,"Chueng Sha Wan Rd");

        School school1 = new School(10000,1,"Tai Tsun St");
        School school2 = new School(13000,1,"Ivy St");
        School school3 = new School(10500,1,"Li Tak St");
    }
}