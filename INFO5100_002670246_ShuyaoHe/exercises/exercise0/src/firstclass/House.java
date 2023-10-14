package firstclass;



public class House extends Building {
    public int rooms;
    public boolean hasKitchen;
    public House(int area, int floors, String location){
        super(area, floors,location);
        System.out.println("Creation of House.");
    }
    public boolean HasKitchen(){
        return hasKitchen;
    }
    public int getRooms(){
        return rooms;
    }
}
