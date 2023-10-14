package firstclass;



public class Library extends Building {
    private int numberOfBooks;
    public boolean hasStarbucks;
    public Library(int area, int floors, String location) {
        super(area, floors, location);
        System.out.println("Creation of Library.");
    }
    public void setNumberOfBooks(int num){
        numberOfBooks=num;
    }
    public int getNumberOfBooks(){
        return numberOfBooks;
    }
    public boolean isHasStarbucks() {
        return hasStarbucks;
    }
}
