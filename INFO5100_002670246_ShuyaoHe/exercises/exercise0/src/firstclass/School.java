package firstclass;

public class School extends Building {
    public int numberOfBuildings;
    public String schoolName;
    public School(int area, int floors, String location){
        super(area,floors,location);
        System.out.println("Creation of School.");
    }

    public School(int area, int floors, String location, String name) {
        super(area,floors,location);
        this.schoolName=name;
        System.out.println("Creation of School.");
    }

    public School() {
        System.out.println("Creation of School.");
    }

    public String getName(){
        return schoolName;
    }
    public void setNumberOfBuildings(int num){
        numberOfBuildings=num;
    }
 }
