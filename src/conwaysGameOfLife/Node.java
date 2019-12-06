package conwaysGameOfLife;

public class Node {
    private boolean state;
    private Coordinate coordinates;
    private final static String blackSquare = "\u25A0";
    private final static String whiteSquare = "\u25A1";

    public Node(Coordinate coordinates) {
        this.state = false;
        this.coordinates = coordinates;
    }

    public boolean getState(){
        return this.state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }


    //used if printing squares to console
    public String squareToPrint(){
        if(this.state){
            return blackSquare;
        }

        return whiteSquare;
    }
}
