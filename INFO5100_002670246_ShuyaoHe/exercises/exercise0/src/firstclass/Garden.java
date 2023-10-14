package firstclass;


public class Garden extends Building {
    public String trees;
    public int ticketPrice;
    public Garden(int area, int floors, String location) {
        super(area,floors,location);
        System.out.println("Creation of Garden.");
    }
    public void getFlowers(){
        System.out.println("get flowers");
    }
    public void get_trees(){
        System.out.println("get trees");
    }
    public int getTicketPrice(){
        return ticketPrice;
    }
}
