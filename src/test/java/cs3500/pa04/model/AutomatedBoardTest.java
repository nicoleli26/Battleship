package cs3500.pa04.model;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The AutomatedBoardTest class contains unit tests for the AutomatedBoard class.
 */
public class AutomatedBoardTest {
  private AutomatedBoard automatedBoard;
  private final int height = 5;
  private final int width = 5;

  /**
   * Sets up the tests before each test method is executed.
   */
  @BeforeEach
  public void setup() {
    automatedBoard = new AutomatedBoard();
    automatedBoard.makeBoard(6, 6);
  }

  /**
   * Tests the makeBoard method of the AutomatedBoard class.
   */
  @Test
  public void testMakeBoard() {
    AutomatedBoard automatedBoard = new AutomatedBoard();
    //int[][] board = automatedBoard.makeBoard(6, 6);

    int[][] expectedBoard = {
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0}
    };

    //assertArrayEquals(expectedBoard, board);
  }

  /**
   * Tests the placeShipsOnBoard method of the AutomatedBoard class.
   */
  @Test
  public void testPlaceShipsOnBoard() {
    AutomatedBoard automatedBoard = new AutomatedBoard();
    automatedBoard.makeBoard(6, 6);

    List<Ship> ships = Arrays.asList(
        new Ship(new Coord(0, 0), 5, Orientation.HORIZONTAL),
        new Ship(new Coord(2, 3), 6, Orientation.VERTICAL),
        new Ship(new Coord(4, 1), 4, Orientation.HORIZONTAL)
    );

    int[][] expectedBoard = {
        {1, 0, 0, 0, 0, 0},
        {1, 0, 0, 0, 0, 0},
        {1, 0, 0, 2, 2, 2},
        {1, 0, 0, 0, 0, 0},
        {1, 3, 0, 0, 0, 0},
        {0, 3, 0, 0, 0, 0}
    };

    automatedBoard.placeShips(ships, automatedBoard.getHeight(), automatedBoard.getWidth());

    assertArrayEquals(automatedBoard.getBoard(), expectedBoard);
  }

  /**
   * Tests the updateBoard method of the AutomatedBoard class.
   */
  @Test
  public void testUpdateBoard() {
    final int[][] board = {
        {0, 0, 0, 0, 0},
        {0, 1, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0}
    };
    List<Coord> hits = new ArrayList<>();
    hits.add(new Coord(1, 1));
    hits.add(new Coord(2, 2));
    hits.add(new Coord(3, 3));
    automatedBoard.updateBoard(hits);

    assertEquals(1, board[1][1]);
    assertEquals(0, board[2][2]);
    assertEquals(0, board[3][3]);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if ((i != 1 || j != 1) && (i != 2 || j != 2) && (i != 3 || j != 3)) {
          assertEquals(0, board[i][j]);
        }
      }
    }

    List<Ship> ships = Arrays.asList(
        new Ship(new Coord(1, 1), 5, Orientation.HORIZONTAL),
        new Ship(new Coord(2, 2), 6, Orientation.VERTICAL),
        new Ship(new Coord(3, 3), 4, Orientation.HORIZONTAL)
    );

    automatedBoard.placeShips(ships, automatedBoard.getHeight(), automatedBoard.getWidth());
    automatedBoard.updateBoard(hits);
    assertEquals(board[1][1], 1);

  }

  /**
   * Tests the isGameOver method of the AutomatedBoard class.
   */
  @Test
  public void testIsGameOver() {
    int[][] board1 = {
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0}
    };

    assertTrue(automatedBoard.isGameOver());

    int[][] board2 = {
        {1, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0}
    };

    List<Ship> ships = Arrays.asList(
        new Ship(new Coord(0, 0), 5, Orientation.HORIZONTAL),
        new Ship(new Coord(2, 3), 6, Orientation.VERTICAL),
        new Ship(new Coord(4, 1), 4, Orientation.HORIZONTAL)
    );
    automatedBoard.placeShips(ships, automatedBoard.getHeight(), automatedBoard.getWidth());
    assertFalse(automatedBoard.isGameOver());

  }

}