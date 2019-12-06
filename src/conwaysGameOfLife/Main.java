package conwaysGameOfLife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener{

    public static Main mainClass;

    public static final int WIDTH = 810, HEIGHT = 850;

    private int ticks = 0, delay = 25;

    private JFrame frame;

    private Timer timer = new Timer(delay, this);

    private RenderPanel renderPanel;

    private Dimension dimension;

    public Board board = new Board(81, 81);

    //class constructor
    private Main(){
        //get screen info
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame("Conway's Game of Life");

        //set frame size to show all game
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setResizable(false);

        //center frame on screen
        frame.setLocation(dimension.width / 2 - WIDTH / 2, dimension.height / 2 - HEIGHT / 2);

        //add render panel to draw images to frame
        frame.add(renderPanel = new RenderPanel());
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        board.createBoard();

        //start the game
        startGame();
    }

    private void startGame(){

        timer.setDelay(delay);

        //set current active nodes
        for(int i = 0; i < 81; i++) {
            board.setActiveNodes(new Coordinate(i, 40));
            board.setActiveNodes(new Coordinate(i, 39));
            board.setActiveNodes(new Coordinate(i, 41));
            board.setActiveNodes(new Coordinate(40, i));
            board.setActiveNodes(new Coordinate(39, i));
            board.setActiveNodes(new Coordinate(41, i));
        }

        //start timer for frame
        timer.start();
    }

    //MAIN METHOD
    public static void main(String[] args) {
        mainClass = new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //render board to the screen
        renderPanel.repaint();

        ticks++;
        //update every other tick
        if (ticks % 2 == 0) {
            board.printBoard();
        }
    }
}
