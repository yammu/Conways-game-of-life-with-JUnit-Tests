package game;

public class GameRules {

  static boolean ALIVE = true;

  public static boolean isAlive(boolean currentState, int numberOfLiveNeighbors) {
    return numberOfLiveNeighbors == 3 || currentState == ALIVE && numberOfLiveNeighbors == 2;
  }

  static boolean isInBounds(int i, int j, int length) {
    return i >= 0 && i < length && j >= 0 && j < length;
  }

  public static int numberOfLiveNeighbors(boolean[][] grid, int x, int y) {
    int numberOfLiveNeighbor = 0;
    
    for (int i = x - 1; i <= x + 1; i++) {

      for (int j = y - 1; j <= y + 1; j++) {

        if (isInBounds(i, j, grid.length) && grid[i][j] == ALIVE)
          numberOfLiveNeighbor++;

      }
    }
    
    if (grid[x][y] == ALIVE)
      numberOfLiveNeighbor = numberOfLiveNeighbor - 1;

    return numberOfLiveNeighbor;
  }

  public static boolean[][] nextGeneration(boolean[][] inputGrid) {
    boolean resultGrid[][] = new boolean[inputGrid.length][inputGrid.length];
    
    for (int i1 = 0; i1 < inputGrid.length; i1++) {
      for (int j = 0; j < inputGrid.length; j++) {
        int numberOfLiveNeighbors = numberOfLiveNeighbors(inputGrid, i1, j);
        resultGrid[i1][j] = isAlive(inputGrid[i1][j], numberOfLiveNeighbors);
      }
    }
    
    return resultGrid;
  }

}
