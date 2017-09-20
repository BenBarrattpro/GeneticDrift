/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticdrift;

/**
 *
 * @author m2105810
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JComponent;

public class Board extends JComponent {
    /*
    Declatation of relevent variables to the board
    */
    private int gridSize = 500;
    private Cell[][] myGrid = new Cell[gridSize][gridSize];
    private int cellSize = 3;
    private int blueCount = 0;
    private int whiteCount = 0;
    private int blackCount = 0;
    private int orangeCount = 0;
    
public Board(){
    /*
    calls the setSquares method upon construction
    */
    setSquares();
}
public Board(int size){
    gridSize = size;
    if(gridSize < 200){
        cellSize = 10;
    }
    else if (gridSize < 500 && gridSize >= 200){
        cellSize = 5;
    }
    else{
        cellSize = 2;
    }
    myGrid = new Cell[gridSize][gridSize];
    setSquares();
}
public void setSquares(){
    /*
    loops through array myGrid and gives each item in the array a random 
    coloured cell.
    */
    for (int i = 0; i < gridSize; i++){
        for (int j = 0; j < gridSize; j++){
            Cell newCell = new Cell();
            newCell = randomCell(newCell,i,j);
            myGrid[i][j] = newCell;
        }
    }
}
    
public Cell randomCell(Cell newCell, int i, int j){
    /*
    generates a random number between 1 and 4 and chooses what color a cell 
    will be based on that
    */
    Random r = new Random();
    int randomNumber;
    randomNumber = r.ints(1,5).limit(1).findFirst().getAsInt();
    switch (randomNumber){
        case 1 : 
            newCell.setCellColor(Color.BLUE);
            blueCount++;
            break;
        case 2 :
            newCell.setCellColor(Color.BLACK);
            blackCount++;
            break;
        case 3 :
            newCell.setCellColor(Color.ORANGE);
            orangeCount++;
            break;
        case 4 :
            newCell.setCellColor(Color.WHITE);
            whiteCount++;
            break;
        default:
            break;
    }
    return newCell;
}

    @Override
    public void paintComponent (Graphics g){
    /*
    method loops through myGrid and checks each cells colour. it then draws a
    square for each cell at the relevent position in the correct colour.
    */
    super.paintComponent(g);
    for (int i = 0; i <gridSize; i++){
        for(int j = 0; j < gridSize; j++){
            g.setColor(myGrid[i][j].getCellColor());
            g.fillRect(i*cellSize, j*cellSize, cellSize, cellSize);
        }
    }
}
public void getNeighbours(){
    /*
    By creating a new two dimensional array of cells we can create an updated
    version based on the old array. Each cell in newGrid gets its colour from
    a related neighbour from myGrid. At the end newGrid replaces myGrid
    */
    Cell[][] newGrid = new Cell[gridSize][gridSize];
    /*
    counts are set to zero to allow them to be recounted for each new grid
    created
    */
    blueCount = 0;
    whiteCount = 0;
    blackCount = 0;
    orangeCount = 0;
    for (int i = 0; i < gridSize; i++){
        int above;
        int below;
        int left;
        int right;
        above = i > 0 ? i - 1: gridSize - 1;
        below = i < gridSize - 1 ? i+1 : 0;
        for (int j = 0; j < gridSize; j++){
            Cell newCell = new Cell();
            left = j > 0 ? j-1 : gridSize - 1;
            right = j < gridSize-1 ? j+1 : 0;
            Random r = new Random();
            int randomNumber;
            randomNumber = r.ints(1,9).limit(1).findFirst().getAsInt();
            /*
            switch statement takes the random number and picks one of the cells 
            eight neighbours
            */
            switch (randomNumber) {
                case 1:
                    newCell.setCellColor(myGrid[above][left].getCellColor());
                    break;
                case 2:
                    newCell.setCellColor(myGrid[above][j].getCellColor());
                    break;
                case 3:
                    newCell.setCellColor(myGrid[above][right].getCellColor());
                    break;
                case 4:
                    newCell.setCellColor(myGrid[i][left].getCellColor());
                    break;
                case 5:
                    newCell.setCellColor(myGrid[i][right].getCellColor());
                    break;
                case 6:
                    newCell.setCellColor(myGrid[below][left].getCellColor());
                    break;
                case 7:
                    newCell.setCellColor(myGrid[below][j].getCellColor());
                    break;
                case 8:
                    newCell.setCellColor(myGrid[below][right].getCellColor());
                    break;
                default:
                    break;
            }
            colorCount(newCell);
            
           newGrid[i][j] = newCell; 
        }
        
    }
    myGrid = newGrid;
    repaint();
}

public int colorCount(Cell newCell){
    /*
    Method is called when a cells colour is set in order to update the various
    counts tracking how many of each cell there are
    */
    int i = 0;
    if(newCell.getCellColor() == Color.BLUE){
        blueCount++;
    }
    if(newCell.getCellColor() == Color.BLACK){
        blackCount++;
    }
    if(newCell.getCellColor() == Color.WHITE){
        whiteCount++;
    }
    if(newCell.getCellColor() == Color.ORANGE){
        orangeCount++;
    }
    return i;
}
public int getblueCount(){
    return blueCount;
}
public int getwhiteCount(){
    return whiteCount;
}
public int getblackCount(){
    return blackCount;
}
public int getorangeCount(){
    return orangeCount;
}
}


