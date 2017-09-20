/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticdrift;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author m2105810
 */
public class GeneticDrift extends JPanel implements ActionListener {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        Creation of frame and setting of size and visiblity
        */
        JFrame frame = new JFrame();
        JPanel panel = new GeneticDrift();
        frame.setContentPane(panel);
        frame.setSize(1500,1000);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /*
    declaration of variables
    */
    private int count = 0;
    private int blueCount = 0;
    private int whiteCount = 0;
    private int blackCount = 0;
    private int orangeCount = 0;
    private Board newBoard = new Board();
    private JPanel bottom = new JPanel();
    private JPanel right = new JPanel();
    private JPanel left = new JPanel();
    private JButton makeBoard = new JButton();
    private JButton goButton = new JButton();
    private JSlider boardSize = new JSlider(JSlider.HORIZONTAL,100,1000,500);
    private Font font = new Font("Serif", Font.ITALIC, 15);
    private JLabel generations = new JLabel();
    private JLabel blue = new JLabel();
    private JLabel white = new JLabel();
    private JLabel black = new JLabel();
    private JLabel orange = new JLabel();
    private Timer timer;
    public GeneticDrift(){
        /*
        This method sets the layout of the frame. It uses a three by three
        system placing the board in the center, the button at the bottom and the
        various labels that keep count of values in the system to the right
        */
        
        setLayout(new BorderLayout (3,3));
        setBackground(Color.WHITE);
        add(newBoard,BorderLayout.CENTER);
        add(bottom,BorderLayout.SOUTH);
        add(right,BorderLayout.EAST);
        add(left,BorderLayout.WEST);
        goButton = new JButton("GO");
        makeBoard = new JButton("Make Board");
        bottom.add(makeBoard);
        bottom.add(goButton);
        generations = new JLabel("Generations " + count);
        blue = new JLabel("BLUE: " + blueCount);
        white = new JLabel("WHITE: " + whiteCount);
        black = new JLabel("BLACK: " + blackCount);
        orange = new JLabel("ORANGE: " + orangeCount);
        right.add(generations);
        right.add(blue);
        right.add(white);
        right.add(black);
        right.add(orange);
        boardSize.setMajorTickSpacing(200);
        boardSize.setMinorTickSpacing(50);
        boardSize.setPaintTicks(true);
        boardSize.setPaintLabels(true);
        boardSize.setFont(font);
        left.add(boardSize);
        makeBoard.addActionListener(this);
        goButton.addActionListener(this);
        timer = new Timer (0,this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        this method simply checks if the timer is running. If it is then it will 
        execute the code relating to the timer. If not it waits for the user to 
        press the go button to activate the timer. If the user presses the go
        button as the timer is running it will pause the timer.
        */
        Object src = e.getSource();
        if(src == goButton){
            if(timer.isRunning()){
                timer.stop();
            }
            else{
                timer.start();
            }
        }
        else if (src == makeBoard){
            if(timer.isRunning()){
                timer.stop();
            }
            int i = boardSize.getValue();
            count = 0;
            blueCount = 0;
            whiteCount = 0;
            orangeCount = 0;
            blackCount = 0;
            System.out.println(i);
            Board madeBoard = new Board(i);
            remove(newBoard);
            newBoard = madeBoard;
            add(newBoard,BorderLayout.CENTER);
            revalidate();
            repaint();
            
        }
        else if (src == timer){
            /*
            This method calls the getNeighbours method from the board class and 
            also gets the amounts of each colour from the board class
            it then updates the labels to reflect the new values and increments
            the count variable to show that a generation has passed
            */
            int i = boardSize.getValue();
            int maxCount = i*i;
            newBoard.getNeighbours();
            count++;
            blueCount = newBoard.getblueCount();
            whiteCount = newBoard.getwhiteCount();
            orangeCount = newBoard.getorangeCount();
            blackCount = newBoard.getblackCount();
            generations.setText("Generations " + count);
            blue.setText("BLUE: " + blueCount);
            white.setText("WHITE: " + whiteCount);
            black.setText("BLACK: " + blackCount);
            orange.setText("ORANGE: " + orangeCount);
            if(blueCount == maxCount){
                timer.stop();
                System.out.println("BLUE VICTORY");
            }
            if(whiteCount == maxCount){
                timer.stop();
                System.out.println("WHITE VICTORY");
            }
            if(blackCount == maxCount){
                timer.stop();
                System.out.println("BLACK VICTORY");
            }
            if(orangeCount == maxCount){
                timer.stop();
                System.out.println("ORANGE VICTORY");
            }
        }
        
    }
}
