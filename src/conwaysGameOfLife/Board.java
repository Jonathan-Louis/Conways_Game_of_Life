package conwaysGameOfLife;


import java.util.ArrayList;


public class Board {
    private int width;
    private int height;
    private ArrayList<Node> board = new ArrayList<>();

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public ArrayList<Node> getBoard(){
        return board;
    }

    public void createBoard(){
        for(int i = 0; i < height; i ++){
            for(int j = 0; j < width; j++){
                board.add(new Node(new Coordinate(j, i)));
            }
        }
    }

    public void printBoard(){

        //uncomment to print to console
//        for(int i = 0; i < board.size(); i++){
//            if(i % (height) == 0 && i != 0){
//                System.out.println();
//            }
//
//            System.out.print(board.get(i).squareToPrint() + " ");
//        }
//        System.out.println("\n");

        updateNodes();
    }

    private void updateNodes(){

        //create temp board to save updates to
        ArrayList<Node> tempBoard = new ArrayList<>();

        for(int i = 0; i < board.size(); i++){
            Node node = new Node(board.get(i).getCoordinates());

            tempBoard.add(node);
        }


        //check each node for number of live nodes next to it to determine next iteration
        for(int i = 0; i < board.size(); i++){
            int liveCount = 0;

            Coordinate current = board.get(i).getCoordinates();

            Coordinate above = new Coordinate(current.getX(), current.getY() - 1);
            if(searchBoard(above) != -1){
                Node aboveNode = board.get(searchBoard(above));
                if(aboveNode.getState()){
                    liveCount++;
                }
            }

            Coordinate bellow = new Coordinate(current.getX(), current.getY() + 1);
            if(searchBoard(bellow) != -1){
                Node bellowNode = board.get(searchBoard(bellow));
                if(bellowNode.getState()){
                    liveCount++;
                }
            }

            Coordinate right = new Coordinate(current.getX() + 1, current.getY());
            if(searchBoard(right) != -1){
                Node rightNode = board.get(searchBoard(right));
                if(rightNode.getState()){
                    liveCount++;
                }
            }

            Coordinate left = new Coordinate(current.getX() - 1, current.getY());
            if(searchBoard(left) != -1){
                Node leftNode = board.get(searchBoard(left));
                if(leftNode.getState()){
                    liveCount++;
                }
            }

            Coordinate topLeft = new Coordinate(current.getX() - 1, current.getY() - 1);
            if(searchBoard(topLeft) != -1){
                Node topLeftNode = board.get(searchBoard(topLeft));
                if(topLeftNode.getState()){
                    liveCount++;
                }
            }

            Coordinate topRight = new Coordinate(current.getX() + 1, current.getY() - 1);
            if(searchBoard(topRight) != -1){
                Node topRightNode = board.get(searchBoard(topRight));
                if(topRightNode.getState()){
                    liveCount++;
                }
            }

            Coordinate bottomLeft = new Coordinate(current.getX() - 1, current.getY() + 1);
            if(searchBoard(bottomLeft) != -1){
                Node bottomLeftNode = board.get(searchBoard(bottomLeft));
                if(bottomLeftNode.getState()){
                    liveCount++;
                }
            }

            Coordinate bottomRight = new Coordinate(current.getX() + 1, current.getY() + 1);
            if(searchBoard(bottomRight) != -1){
                Node bottomRightNode = board.get(searchBoard(bottomRight));
                if(bottomRightNode.getState()){
                    liveCount++;
                }
            }

            //determine if node is alive or dead in next frame
            if(board.get(i).getState()){
                if(liveCount <= 1){
                    tempBoard.get(i).setState(false);
                } else if(liveCount >= 3){
                    tempBoard.get(i).setState(false);
                } else {
                    tempBoard.get(i).setState(true);
                }
            } else {
                if(liveCount == 3){
                    tempBoard.get(i).setState(true);
                } else {
                    tempBoard.get(i).setState(false);
                }
            }
        }

        //update current board with new states
        for(int i = 0; i < tempBoard.size(); i++){
            board.get(i).setState(tempBoard.get(i).getState());
        }
    }

    //set first live nodes
    public boolean setActiveNodes(Coordinate coordinates){
        int position = searchBoard(coordinates);
        if(position != -1) {
            board.get(position).setState(true);
            return true;
        } else {
            return false;
        }
    }

    private int searchBoard(Coordinate coordinates){
        for(int i = 0; i < board.size(); i++){
            if(board.get(i).getCoordinates().getX() == coordinates.getX() &&
                    board.get(i).getCoordinates().getY() == coordinates.getY()){
                return i;
            }
        }

        return -1;
    }

    public boolean checkBoard(){
        for(int i = 0; i < board.size(); i++){
            if(board.get(i).getState()){
                return true;
            }
        }

        return false;
    }
}
