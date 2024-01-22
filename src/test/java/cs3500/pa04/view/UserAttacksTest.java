package cs3500.pa04.view;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa04.model.Coord;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The UserAttacksTest class contains unit tests for the UserAttacks class.
 */
public class UserAttacksTest {
  UserAttacks userAttacks;
  Scanner scanner;
  int[][] board;

  @BeforeEach
  void setup() {
    try {
      File tempFile = File.createTempFile("ExampleShots", ".txt");
      PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
      writer.println("1 2");
      writer.println("1 1");
      writer.println("1 3");
      writer.println("1 2");
      writer.close();
      scanner = new Scanner(tempFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    userAttacks = new UserAttacks(scanner);
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
   * Tests the isValidCoordinate method of the UserAttacks class with valid input
   */
  @Test
  void testIsValidCoordinateValidInput() {
    int x = 2;
    int y = 3;
    boolean isValid = UserAttacks.isValidCoordinate(x, y);
    assertTrue(isValid);

    assertThrows(NumberFormatException.class, () -> {
      UserAttacks.isValidCoordinate(x, Integer.parseInt("x"));
    });
  }

  @Test
  void testListOfShots() {
    List<Coord> shots = userAttacks.listOfShots(board);
    userAttacks.setScanner(scanner);
    Coord coord1 = new Coord(1, 2);
    Coord coord2 = new Coord(1, 1);
    Coord coord3 = new Coord(1, 3);
    assertTrue(shots.get(0).equals(coord1));
    assertTrue(shots.get(1).equals(coord2));
    assertTrue(shots.get(2).equals(coord3));
  }
}
