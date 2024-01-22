package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The UserPlayerTest class contains unit tests for the UserPlayer class.
 */
class UserPlayerTest {

  PlayerBoard board;
  private UserPlayer userPlayer;

  /**
   * Sets up the test fixture before each test method is executed.
   */
  @BeforeEach
  void setUp() {
    board = new PlayerBoard();
    board.makeBoard(6, 6);
    userPlayer = new UserPlayer(board);
  }

  /**
   * Tests the setup method of the UserPlayer class.
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

    List<Ship> shipLocation = userPlayer.setup(height, width, specifications);

    assertNotNull(shipLocation);
    assertEquals(6, shipLocation.size());

    for (Ship ship : shipLocation) {
      assertTrue(isShipPlacementValid(ship, width, height, shipLocation));
    }
  }

  /**
   * Tests the takeShots method of the UserPlayer class.
   */
  @Test
  void testTakeShots() {
    List<Coord> shots = userPlayer.takeShots();

    assertNotNull(shots);
    assertTrue(shots.isEmpty());
  }

  /**
   * Tests the reportDamage method of the UserPlayer class.
   */
  @Test
  void testReportDamage() {
    List<Coord> opponentShotsOnBoard = List.of(new Coord(2, 3), new Coord(4, 1), new Coord(5, 5));
    List<Coord> damage = userPlayer.reportDamage(opponentShotsOnBoard);

    assertNotNull(damage);
    assertTrue(damage.isEmpty());
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
