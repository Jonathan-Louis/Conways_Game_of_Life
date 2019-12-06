package conwaysGameOfLife;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class RenderPanel extends JPanel {

    private static final int SCALE = 10;

    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        Main main = Main.mainClass;

        //set background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);

        //catch null pointer exception on first call
        try{
            ArrayList<Node> board = main.board.getBoard();

            //output white squares for each live node
            g.setColor(Color.WHITE);
            for(int i = 0; i < board.size(); i++){
                if(board.get(i).getState()){
                    g.fillRect(board.get(i).getCoordinates().getX() * SCALE, board.get(i).getCoordinates().getY() * SCALE, 9, 9);
                }
            }

        } catch(NullPointerException ignored){
        }
    }
}
