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
 * The PlayerBoardTest class contains unit tests for the PlayerBoard class.
 */
public class PlayerBoardTest {

  private PlayerBoard playerBoard;
  private final int height = 5;
  private final int width = 5;

  /**
   * Sets up the test fixture before each test method is executed.
   */
  @BeforeEach
  public void setup() {
    playerBoard = new PlayerBoard();
    playerBoard.makeBoard(6, 6);
  }

  /**
   * Tests the makeBoard method of the PlayerBoard class.
   */
  @Test
  public void testMakeBoard() {
    PlayerBoard automatedBoard = new PlayerBoard();
    playerBoard.makeBoard(6, 6);

    int[][] expectedBoard = {
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0}
    };

    assertArrayEquals(expectedBoard, playerBoard.getBoard());
  }

  /**
   * Tests the placeShipsOnBoard method of the PlayerBoard class.
   */
  @Test
  public void testPlaceShipsOnBoard() {
    PlayerBoard automatedBoard = new PlayerBoard();
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

    playerBoard.placeShips(ships, playerBoard.getHeight(), playerBoard.getWidth());

    assertArrayEquals(expectedBoard, playerBoard.getBoard());
  }

  /**
   * Tests the updateBoard method of the PlayerBoard class.
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
    playerBoard.updateBoard(hits);

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
    hits.add(new Coord(1, 1));
    hits.add(new Coord(2, 2));
    hits.add(new Coord(3, 3));
    playerBoard.updateBoard(hits);

    assertEquals(1, board[1][1]);
    assertEquals(0, board[2][2]);
    assertEquals(0, board[3][3]);

  }

  /**
   * Tests the isGameOver method of the PlayerBoard class.
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

    assertTrue(playerBoard.isGameOver());

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
    playerBoard.placeShips(ships, playerBoard.getHeight(), playerBoard.getWidth());
    assertFalse(playerBoard.isGameOver());

  }
}
