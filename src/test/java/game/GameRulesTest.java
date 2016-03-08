package game;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameRulesTest {

  final boolean ALIVE = true;
  final boolean DEAD = false;

  @Test
  public void liveCellWithZeroNeigbhborsDies() {
    int numberOfLiveNeighbors = 0;
    assertEquals(DEAD, GameRules.isAlive(ALIVE, numberOfLiveNeighbors));
  }

  @Test
  public void liveCellWithOneNeighbourDies() {
    int numberOfLiveNeighbors = 1;
    assertEquals(DEAD, GameRules.isAlive(ALIVE, numberOfLiveNeighbors));
  }

  @Test
  public void liveCellWithTwoNeighborAlives() {
    int numberOfLiveNeighbors = 2;
    assertEquals(ALIVE, GameRules.isAlive(ALIVE, numberOfLiveNeighbors));
  }

  @Test
  public void liveCellWithThreeNeighbourAlives() {
    int numberOfLiveNeighbors = 3;
    assertEquals(ALIVE, GameRules.isAlive(ALIVE, numberOfLiveNeighbors));
  }

  @Test
  public void liveCellWithMoreThanThreeNeighborDies() {
    int numberOfLiveNeighbors = 4;
    assertEquals(DEAD, GameRules.isAlive(ALIVE, numberOfLiveNeighbors));
  }

  @Test
  public void deadCellWithZeroLiveNeighbourDies() {
    int numberOfLiveNeighbors = 0;
    assertEquals(DEAD, GameRules.isAlive(DEAD, numberOfLiveNeighbors));
  }

  @Test
  public void deadCellWithOneLiveNeighbourDies() {
    int numberOfLiveNeighbors = 1;
    assertEquals(DEAD, GameRules.isAlive(DEAD, numberOfLiveNeighbors));
  }

  @Test
  public void deadCellWithTwoLiveNeighbourDies() {
    int numberOfLiveNeighbors = 2;
    assertEquals(DEAD, GameRules.isAlive(DEAD, numberOfLiveNeighbors));
  }

  @Test
  public void deadCellWithThreeLiveNeighbourLives() {
    int numberOfLiveNeighbors = 3;
    assertEquals(ALIVE, GameRules.isAlive(DEAD, numberOfLiveNeighbors));
  }

  @Test
  public void deadCellWithFourLiveNeighbourDies() {
    int numberOfLiveNeighbors = 4;
    assertEquals(DEAD, GameRules.isAlive(DEAD, numberOfLiveNeighbors));
  }

  @Test
  public void emptyGridHaveZeroLiveNeighbour() {

    
    boolean[][] grid = { 
        { DEAD, DEAD, DEAD, DEAD },
        { DEAD, DEAD, DEAD, DEAD }, 
        { DEAD, DEAD, DEAD, DEAD },
        { DEAD, DEAD, DEAD, DEAD } };

    int x = 1, y = 2;

    assertEquals(0, GameRules.numberOfLiveNeighbors(grid, x, y));
  }

  @Test
  public void oneLiveCellGridHaveOneNeighborForAdjacentCells() {
    boolean[][] grid = { { DEAD, ALIVE, DEAD, DEAD },
                         { DEAD, DEAD, DEAD, DEAD }, 
                         { DEAD, DEAD, DEAD, DEAD },
                         { DEAD, DEAD, DEAD, DEAD }, };

    int x = 1, y = 2;

    assertEquals(1, GameRules.numberOfLiveNeighbors(grid, x, y));
  }

  @Test
  public void twoLiveCellGridHaveTwoNeighborForAdjacentCells() {
    boolean[][] grid = { { DEAD, ALIVE, DEAD, DEAD },
                         { DEAD, ALIVE, DEAD, DEAD },
                         { DEAD, ALIVE, DEAD, DEAD },
                         { DEAD, DEAD, DEAD, DEAD }, };

    int x = 1, y = 1;

    assertEquals(2, GameRules.numberOfLiveNeighbors(grid, x, y));
  }

  @Test
  public void oneLiveCellCanHaveZeroLiveNeighbours() {
    boolean[][] grid = { { DEAD, DEAD, DEAD, DEAD },
                         { DEAD, DEAD, DEAD, DEAD },
                         { ALIVE, DEAD, DEAD, DEAD },
                         { DEAD, DEAD, DEAD, DEAD }, };

    int x = 2, y = 0;

    assertEquals(0, GameRules.numberOfLiveNeighbors(grid, x, y));
  }

  @Test
  public void topLeftCornerCanHaveAtMostThreeLiveNeighbour() {
    
    boolean[][] grid = { { DEAD, ALIVE, DEAD, DEAD },
                         { ALIVE, ALIVE, DEAD, DEAD },
                         { DEAD, DEAD, DEAD, DEAD },
                         { DEAD, DEAD, DEAD, DEAD }, };

    int x = 0, y = 0;

    assertEquals(3, GameRules.numberOfLiveNeighbors(grid, x, y));
  }

  @Test
  public void topRightCornerCanHaveAtMostThreeLiveNeighbour() {
    boolean[][] grid = { { DEAD, DEAD, ALIVE, DEAD },
                         { DEAD, DEAD, ALIVE, ALIVE }, 
                         { DEAD, DEAD, DEAD, DEAD },
                         { DEAD, DEAD, DEAD, DEAD }, };

    int x = 0, y = 3;

    assertEquals(3, GameRules.numberOfLiveNeighbors(grid, x, y));
  }

  @Test
  public void bottomLeftCornerCanHaveAtMostThreeLiveNeighbour() {
    
    boolean[][] grid = { { DEAD, DEAD, ALIVE, DEAD },
                         { DEAD, DEAD, ALIVE, ALIVE }, 
                         { ALIVE, ALIVE, DEAD, DEAD },
                         { ALIVE, ALIVE, DEAD, DEAD }, };

    int x = 0, y = 3;

    assertEquals(3, GameRules.numberOfLiveNeighbors(grid, x, y));
  }

  @Test
  public void bottomRightCornerCanHaveAtMostThreeLiveNeighbour() {
    boolean[][] grid = { { DEAD, DEAD, ALIVE, DEAD },
                         { DEAD, DEAD, ALIVE, ALIVE }, 
                         { DEAD, DEAD, DEAD, DEAD },
                         { DEAD, DEAD, DEAD, ALIVE }, };

    int x = 3, y = 3;

    assertEquals(0, GameRules.numberOfLiveNeighbors(grid, x, y));
  }
  
  
 
  @Test
  public void gridWithAllDeadCellsResultsAllDeadCells(){
    boolean[][] inputGrid = { { DEAD, DEAD, DEAD, DEAD },
                              { DEAD, DEAD, DEAD, DEAD }, 
                              { DEAD, DEAD, DEAD, DEAD },
                              { DEAD, DEAD, DEAD, DEAD }, };
    boolean[][] OutputGrid = { { DEAD, DEAD, DEAD, DEAD },
                               { DEAD, DEAD, DEAD, DEAD }, 
                               { DEAD, DEAD, DEAD, DEAD },
                               { DEAD, DEAD, DEAD, DEAD }, };
    
     assertArrayEquals(OutputGrid, GameRules.nextGeneration(inputGrid)); 
  }
  
  @Test
  public void gridWithOneAliveCellsResultsAllDeadCells(){
    boolean[][] inputGrid = { { DEAD, DEAD, DEAD, DEAD },
                              { DEAD, ALIVE, DEAD, DEAD }, 
                              { DEAD, DEAD, DEAD, DEAD },
                              { DEAD, DEAD, DEAD, DEAD }, };
    
    boolean[][] OutputGrid = { { DEAD, DEAD, DEAD, DEAD },
                               { DEAD, DEAD, DEAD, DEAD }, 
                               { DEAD, DEAD, DEAD, DEAD },
                               { DEAD, DEAD, DEAD, DEAD }, };
    
    assertArrayEquals(OutputGrid, GameRules.nextGeneration(inputGrid)); 
  }
  
   boolean[][] verticalBlinker = { { DEAD, ALIVE, DEAD, DEAD }, 
                                  { DEAD, ALIVE, DEAD, DEAD }, 
                                  { DEAD, ALIVE, DEAD, DEAD },
                                  { DEAD, DEAD, DEAD, DEAD }, };
   
  boolean[][] horizontalBlinker = { { DEAD, DEAD, DEAD, DEAD },
                                  { ALIVE, ALIVE, ALIVE, DEAD }, 
                                  { DEAD, DEAD, DEAD, DEAD },
                                  { DEAD, DEAD, DEAD, DEAD }, };
  @Test
  public void gridWithVerticalAliveCellsResultsHorizontalLiveCells(){    
    
    assertArrayEquals(horizontalBlinker, GameRules.nextGeneration(verticalBlinker));
  }
  
  @Test
  public void gridWithHorizontalAliveCellsResultsVerticalLiveCells(){
    
    assertArrayEquals(verticalBlinker, GameRules.nextGeneration(horizontalBlinker)); 
  }
  
}
