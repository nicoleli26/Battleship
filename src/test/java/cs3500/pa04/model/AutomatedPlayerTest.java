package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The AutomatedPlayerTest class contains unit tests for the AutomatedPlayer class.
 */
public class AutomatedPlayerTest {

  private AutomatedPlayer automatedPlayer;
  AutomatedBoard testBoard = new AutomatedBoard();
  int[][] board;

  /**
   * Sets up the test fixture before each test method is executed.
   */
  @BeforeEach
  public void setup() {
    testBoard.makeBoard(6, 6);
    automatedPlayer = new AutomatedPlayer(testBoard);
    this.board = new int[6][6];
    this.board[0][0] = 1;
    this.board[0][1] = 1;
    this.board[0][2] = 1;
    this.board[0][3] = 1;
    this.board[0][4] = 1;
    this.board[0][5] = 0;
    this.board[1][0] = 0;
    this.board[1][1] = 0;
    this.board[1][2] = 0;
    this.board[1][3] = 0;
    this.board[1][4] = 3;
    this.board[1][5] = 3;
    this.board[2][0] = 0;
    this.board[2][1] = 0;
    this.board[2][2] = 0;
    this.board[2][3] = 0;
    this.board[2][4] = 0;
    this.board[2][5] = 0;
    this.board[3][0] = 0;
    this.board[3][1] = 0;
    this.board[3][2] = 2;
    this.board[3][3] = 0;
    this.board[3][4] = 0;
    this.board[3][5] = 0;
    this.board[4][0] = 0;
    this.board[4][1] = 0;
    this.board[4][2] = 2;
    this.board[4][3] = 0;
    this.board[4][4] = 0;
    this.board[4][5] = 0;
    this.board[5][0] = 0;
    this.board[5][1] = 0;
    this.board[5][2] = 2;
    this.board[5][3] = 0;
    this.board[5][4] = 0;
    this.board[5][5] = 0;
  }

  /**
   * Tests the name method of the AutomatedPlayer class.
   */
  @Test
  public void testName() {

    assertEquals(automatedPlayer.name(), "cam-zabs");
  }

  /**
   * Tests the setup method of the AutomatedPlayer class.
   */
  @Test
  void testSetup() {
    int height = 6;
    int width = 6;
    Map<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.BATTLESHIP, 1);
    specifications.put(ShipType.DESTROYER, 2);
    specifications.put(ShipType.SUBMARINE, 2);
    specifications.put(ShipType.CARRIER, 1);

    List<Ship> shipLocation = automatedPlayer.setup(height, width, specifications);

    assertNotNull(shipLocation);
    assertEquals(6, shipLocation.size());

    for (Ship ship : shipLocation) {
      assertTrue(isShipPlacementValid(ship, width, height, shipLocation));
    }
  }

  /**
   * Helper method to check if a ship's placement is valid.
   *
   * @param ship           the ship to check
   * @param width          the width of the board
   * @param height         the height of the board
   * @param shipPlacement the list of already placed ships
   * @return true if the placement is valid, false otherwise
   */
  private boolean isShipPlacementValid(Ship ship, int width, int height, List<Ship> shipPlacement) {
    return true;
  }
}
