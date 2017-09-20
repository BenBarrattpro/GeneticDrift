/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticdrift;

import java.awt.Color;

/**
 *
 * @author m2105810
 */
/*
Class contains relevent data for a cell with standard getter and setter methods
for each variable
*/
public class Cell {
    private Color cellColor;

 public Cell getCell(){
     return this;
 }   
 public void setCellColor(Color newColor){
     cellColor = newColor;
 }
 public Color getCellColor(){
     return cellColor;
 }
}
