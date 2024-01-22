package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The AutoAttacksTest class contains unit tests for the AutoAttacks class.
 */
public class AutoAttacksTest {
  AutoAttacks autoAttacks;
  int[][] board;

  /**
   * Setup for AutoAttacksTest - creates test board
   */
  @BeforeEach
  public void setup() {
    this.autoAttacks = new AutoAttacks();
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
   * Tests the getNumOfShips method of the AutoAttacks class.
   */
  @Test
  public void testGetNumOfShips() {
    int[][] board = {
        {1, 0, 0, 0, 0, 0},
        {1, 0, 0, 0, 0, 0},
        {1, 0, 0, 2, 2, 2},
        {1, 0, 0, 0, 0, 0},
        {1, 3, 0, 0, 0, 0},
        {0, 3, 0, 0, 0, 0}
    };

    int numOfShips = AutoAttacks.getNumOfShips(board);

    assertEquals(3, numOfShips);
  }

  /**
   * Tests the listOfShots method of the AutoAttacks class.
   */
  @Test
  public void testListOfShots() {
    List<Coord> testSuccessfulShots = new ArrayList<>();
    Coord coord1 = new Coord(0, 0);
    Coord coord2 = new Coord(2, 2);
    Coord coord3 = new Coord(3, 3);
    testSuccessfulShots.add(coord1);
    testSuccessfulShots.add(coord2);
    testSuccessfulShots.add(coord3);
    autoAttacks.setSuccessfulShots(testSuccessfulShots);
    Coord coord1Adjacent = new Coord(0, 1);
    Coord coord2Adjacent = new Coord(2, 3);
    Coord coord3Adjacent = new Coord(3, 4);
    Randomable mockRandom = new MockRandom();
    List<Coord> listOfShots = autoAttacks.listOfShots(this.board, mockRandom);
    assertTrue(coord1Adjacent.equals(listOfShots.get(0)));
    assertTrue(coord2Adjacent.equals(listOfShots.get(1)));
    assertTrue(coord3Adjacent.equals(listOfShots.get(2)));
    List<Coord> shotTracker = new ArrayList<>();
    shotTracker.add(coord1Adjacent);
    Coord coord1Adjacent2 = new Coord(1, 0);
    shotTracker.add(coord1Adjacent2);
    autoAttacks.setShotTracker(shotTracker);
    Coord randCoord = new Coord(2, 1);
    List<Coord> newListOfShots = autoAttacks.listOfShots(this.board, mockRandom);
    assertTrue(randCoord.equals(newListOfShots.get(0)));
    assertTrue(coord2Adjacent.equals(newListOfShots.get(1)));
    assertTrue(coord3Adjacent.equals(newListOfShots.get(2)));
  }

  @Test
  public void testListOfShots2() {
    autoAttacks.getNumOfShips(board);
    autoAttacks.setSuccessfulShots(null);
    Coord newCoord1Adjacent = new Coord(0, 1);
    Coord newCoord2Adjacent = new Coord(2, 3);
    Coord newCoord3Adjacent = new Coord(3, 4);
    List<Coord> newShotTracker = new ArrayList<>();
    Coord newCoord1Adjacent2 = new Coord(1, 0);
    newShotTracker.add(newCoord1Adjacent);
    newShotTracker.add(newCoord1Adjacent2);
    Coord newRandCoord = new Coord(2, 1);
    newShotTracker.add(newCoord2Adjacent);
    newShotTracker.add(newRandCoord);
    Coord coord2Adjacent2 = new Coord(3, 2);
    Coord coord2Adjacent3 = new Coord(1, 2);
    Coord coord3Adjacent2 = new Coord(3, 2);
    Coord coord3Adjacent3 = new Coord(4, 3);
    Coord coord3Adjacent4 = new Coord(3, 4);
    newShotTracker.add(coord2Adjacent2);
    newShotTracker.add(coord2Adjacent3);
    newShotTracker.add(newCoord3Adjacent);
    newShotTracker.add(coord3Adjacent2);
    newShotTracker.add(coord3Adjacent3);
    newShotTracker.add(coord3Adjacent4);
    autoAttacks.setShotTracker(newShotTracker);
    Randomable mockRandom = new MockRandom();
    List<Coord> newestListOfShots = autoAttacks.listOfShots(this.board, mockRandom);
    Coord randCoord1 = new Coord(1, 3);
    Coord randCoord2 = new Coord(2, 5);
    Coord randCoord3 = new Coord(0, 4);
    assertTrue(randCoord1.equals(newestListOfShots.get(0)));
    assertTrue(randCoord2.equals(newestListOfShots.get(1)));
    assertTrue(randCoord3.equals(newestListOfShots.get(2)));
  }

  @Test
  public void testContains() {
    Coord coord1 = new Coord(1, 1);
    Coord coord2 = new Coord(2, 2);
    List<Coord> exampleShotTracker = new ArrayList<>();
    exampleShotTracker.add(coord1);
    exampleShotTracker.add(coord2);
    autoAttacks.setShotTracker(exampleShotTracker);
    Coord coord3 = new Coord(2, 2);
    Coord coord4 = new Coord(3, 3);
    Coord coord5 = new Coord(2, 3);
    assertTrue(autoAttacks.contains(coord3));
    assertFalse(autoAttacks.contains(coord4));
    assertFalse(autoAttacks.contains(coord5));
  }

  @Test
  public void testHasValidAdjacent() {
    Coord coordInvalidRight = new Coord(1, 0);
    Coord coordInvalidBottom = new Coord(0, 1);
    Coord coordValidBottom = new Coord(2, 1);
    Coord coord1 = new Coord(1, 1);
    List<Coord> exampleShotTracker = new ArrayList<>();
    exampleShotTracker.add(coord1);
    exampleShotTracker.add(coordInvalidRight);
    exampleShotTracker.add(coordInvalidBottom);
    exampleShotTracker.add(coordValidBottom);
    autoAttacks.setShotTracker(exampleShotTracker);
    Coord invalidAdjacent = new Coord(0, 0);
    Coord validAdjacent1 = new Coord(2, 0);
    Coord validAdjacent2 = new Coord(4, 4);
    assertFalse(autoAttacks.hasValidAdjacent(this.board, invalidAdjacent));
    assertTrue(autoAttacks.hasValidAdjacent(this.board, validAdjacent1));
    assertTrue(autoAttacks.hasValidAdjacent(this.board, validAdjacent2));
    Coord validAdjacent3 = new Coord(5, 5);
    assertTrue(autoAttacks.hasValidAdjacent(this.board, validAdjacent3));
    Coord validAdjacent3Top = new Coord(5, 4);
    Coord validAdjacent3Left = new Coord(4, 5);
    exampleShotTracker.add(validAdjacent3Top);
    exampleShotTracker.add(validAdjacent3Left);
    autoAttacks.setShotTracker(exampleShotTracker);
    assertFalse(autoAttacks.hasValidAdjacent(this.board, validAdjacent3));
  }

  @Test
  public void testGenerateAdjacentShot() {
    Coord coordValidBottom = new Coord(2, 1);
    Coord coordValidRight = new Coord(3, 0);
    List<Coord> exampleShotTracker = new ArrayList<>();
    exampleShotTracker.add(coordValidBottom);
    exampleShotTracker.add(coordValidRight);
    autoAttacks.setShotTracker(exampleShotTracker);
    Coord validAdjacent = new Coord(2, 0);
    Coord validAdjacentLeft = new Coord(1, 0);
    Coord generatedLeftAdjacent1 =
        autoAttacks.generateAdjacentShot(this.board, validAdjacent);
    assertTrue(validAdjacentLeft.equals(generatedLeftAdjacent1));
    Coord middle = new Coord(3, 3);
    Coord top = new Coord(3, 2);
    Coord bottom = new Coord(3, 4);
    Coord generatedBottomAdjacent =
        autoAttacks.generateAdjacentShot(this.board, middle);
    assertTrue(bottom.equals(generatedBottomAdjacent));
    exampleShotTracker.add(bottom);
    Coord generatedTopAdjacent =
        autoAttacks.generateAdjacentShot(this.board, middle);
    assertTrue(top.equals(generatedTopAdjacent));
    exampleShotTracker.add(top);
    Coord right = new Coord(4, 3);
    Coord generatedRightAdjacent =
        autoAttacks.generateAdjacentShot(this.board, middle);
    assertTrue(right.equals(generatedRightAdjacent));
    exampleShotTracker.add(right);
    Coord left = new Coord(2, 3);
    Coord generatedLeftAdjacent =
        autoAttacks.generateAdjacentShot(this.board, middle);
    assertTrue(left.equals(generatedLeftAdjacent));
  }

  @Test
  public void testGenerateRandomShot() {
    Randomable mockRandom = new MockRandom();
    Coord randomCoord = autoAttacks.generateRandomShot(board, mockRandom);
    Coord testRandomCoord = new Coord(2, 1);
    assertTrue(testRandomCoord.equals(randomCoord));
  }
}