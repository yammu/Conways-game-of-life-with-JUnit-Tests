package ui.game;


import game.GameRules;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GuiDisplay extends JFrame {
  private JButton[][] cells;
  int size;
  boolean[][] liveCells=new  boolean[size][size];
  static ArrayList<String> listOfLiveCell = new ArrayList<String>();
  private int userInputBoardSize() {
    String sizeOfBoard = JOptionPane.showInputDialog("Enter the size of board: ");
    return Integer.parseInt(sizeOfBoard.trim());
  }
  
  public void frameInit() {
    super.frameInit();
    
     size = userInputBoardSize();
    
    setLayout(new GridLayout(size + 1, size));
    setSize(size * 50, size * 50);
    
    cells = new JButton[size][size];
    for(int i = 0; i < size; i++) {
      for(int j = 0; j < size; j++) {
        JButton cell = new JButton();
        String location = String.format("%d %d", i , j);
         cell.setName(location);
        getContentPane().add(cell);
        cell.addActionListener(this::cellClicked);
        cells[i][j] = cell;
        //System.out.println(cells[i][j]);
      }
    }
    
    JButton startButton = new JButton();
    startButton.setText("Start");
    startButton.addActionListener(this::start);
    getContentPane().add(startButton);
  }
  
  public void cellClicked(ActionEvent event) {
    JButton cell = (JButton) event.getSource();
    
    String[] location = cell.getName().split(" ");
  
  cell.setBackground(Color.blue);
    
    int row = Integer.parseInt(location[0]);
    int column = Integer.parseInt(location[1]);
   
    System.out.println(row);
    System.out.println(column);
    
    liveCells[row][column]=true;
    
    toggleCell(liveCells,cell);
  }
  
  public void start(ActionEvent event) {
    JButton button = (JButton) event.getSource();
    button.setEnabled(false);
    
    new javax.swing.Timer(1000, this::toggleCells).start();
  }
  
  private void toggleCells(ActionEvent event) {
    
    for(int i = 0; i < cells.length; i++) {
      for(int j = 0; j < cells.length; j++) {        
        toggleCell(liveCells, cells[i][j]);
              }
    }
    repaint();
  }
  
  private void toggleCell(boolean[][] liveCells, JButton cell) {
    boolean[][] generation=new  boolean[size][size];
    generation = GameRules.nextGeneration(liveCells);
    for(int i = 0; i < cells.length; i++)
    {
      for(int j = 0; j < cells.length; j++) 
      {
       if(generation[i][j]==true)
       {
           cells[i][j].setBackground(Color.blue);;
       }
       }
          }
    liveCells=generation;
  }
  public static void main(String[] args) {
    JFrame frame = new GuiDisplay();
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}

