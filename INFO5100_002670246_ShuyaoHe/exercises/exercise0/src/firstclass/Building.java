package firstclass;
public class Building {
    protected  int floors;
    protected  int area;
    protected  String location;
    protected String owner;
    protected String usage;
    protected  String material;
    public Building(){
        System.out.println("Creation of Building.");
    }
    public Building(int area, int floors,  String location){

        this.area = area;
        this.floors = floors;
        this.location = location;
    }

    public String getOwner(){
        return this.owner;
    }
    public void setUsage(String use){
        this.usage=usage;
        System.out.println("usage method");
    }}

