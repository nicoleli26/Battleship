package cs3500.pa04.view;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import cs3500.pa04.model.Coord;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The BoardDisplayTest class contains unit tests for the BoardDisplay class.
 */
public class BoardDisplayTest {
  private BoardDisplay boardDisplay;

  @BeforeEach
  public void setup() {
    boardDisplay = new BoardDisplay();
  }

  /**
   * Tests the printBoards method of the BoardDisplay class.
   */
  @Test
  public void testPrintBoards() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputStream));

    String[][] board = {
        {"1", "2", "3"},
        {"4", "5", "6"},
        {"7", "8", "9"}
    };
    String[][] board2 = {
        {"0", "0", "0"},
        {"0", "0", "0"},
        {"0", "0", "0"}
    };

    BoardDisplay boardDisplay = new BoardDisplay();
    boardDisplay.printBoards(board, board2);

    System.setOut(originalOut);

    String expectedOutput = "0 0 0 \n0 0 0 \n0 0 0 \n- - - \n1 2 3 \n4 5 6 \n7 8 9 \n";
    Assertions.assertEquals(expectedOutput, outputStream.toString());
  }

  /**
   * Tests the boardDisplay method of the BoardDisplay class.
   */
  @Test
  public void testBoardDisplay() {
    int[][] board = {
        {1, 0, 2},
        {0, 3, 0},
        {4, 0, 5}
    };

    String[][] expectedDisplay = {
        {"1", "0", "2"},
        {"0", "3", "0"},
        {"4", "0", "5"}
    };

    String[][] actualDisplay = boardDisplay.boardDisplay(board);

    assertArrayEquals(expectedDisplay, actualDisplay);
  }

  /**
   * Tests the updateBoardDisplay method of the BoardDisplay class.
   */
  @Test
  public void testUpdateBoardDisplay() {
    String[][] board = {
        {"0", "0", "2"},
        {"0", "3", "0"},
        {"4", "-1", "5"}
    };

    List<Coord> attacks = new ArrayList<>();
    attacks.add(new Coord(0, 0));
    attacks.add(new Coord(1, 1));

    String[][] expectedUpdatedBoard = {
        {"M", "0", "2"},
        {"0", "H", "0"},
        {"4", "-1", "5"}
    };

    String[][] actualUpdatedBoard = boardDisplay.updateBoardDisplay(board, attacks);

    assertArrayEquals(expectedUpdatedBoard, actualUpdatedBoard);
  }

  /**
   * Tests the changeValuesToZero method of the BoardDisplay class.
   */
  @Test
  public void testChangeValuesToZero() {
    String[][] board = {
        {"H", "0", "2"},
        {"0", "M", "0"},
        {"4", "0", "H"}
    };

    String[][] expectedBoard = {
        {"H", "0", "0"},
        {"0", "M", "0"},
        {"0", "0", "H"}
    };

    boardDisplay.changeValuesToZero(board);

    assertArrayEquals(expectedBoard, board);
  }

  @Test
  public void testChangeNegativeValuesToString() {
    String[][] board = {
        {"0", "0", "0", "0", "0", "0"},
        {"0", "0", "0", "0", "0", "0"},
        {"0", "0", "0", "0", "0", "0"},
        {"0", "0", "0", "0", "0", "0"},
        {"0", "0", "0", "-1", "0", "0"},
        {"0", "0", "0", "0", "-5", "0"}
    };

    String[][] board2 = {
        {"0", "0", "0", "0", "0", "0"},
        {"0", "0", "0", "0", "0", "0"},
        {"0", "0", "0", "0", "0", "0"},
        {"0", "0", "0", "0", "0", "0"},
        {"0", "0", "0", "H", "0", "0"},
        {"0", "0", "0", "0", "H", "0"}
    };

    boardDisplay.changeNegativeValuesToString(board);
    assertArrayEquals(board, board2);

  }
}
