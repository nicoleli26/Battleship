package cs3500.pa04.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.ShipType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for BoardSpecs class
 */
class BoardSpecsTest {
  BoardSpecs boardSpecs;
  int[] testBoard;
  Scanner boardDimensionScanner;
  Scanner fleetSpecScanner;
  Scanner invalidFleetSpec;

  /**
   * Setup for BoardSpecsTest
   */
  @BeforeEach
  void setUp() {
    try {
      File tempFile = File.createTempFile("ExampleInputs", ".txt");
      PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
      writer.println("6 6");
      writer.println("1 2 2 1");
      writer.close();
      boardDimensionScanner = new Scanner(tempFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    try {
      File tempFile = File.createTempFile("ExampleFleetSpecs", ".txt");
      PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
      writer.println("1 2 2 1");
      writer.println("1 2 2 1");
      writer.println("1 2 2 1");
      writer.close();
      fleetSpecScanner = new Scanner(tempFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    try {
      File tempFile = File.createTempFile("InvalidFleetSpecs", ".txt");
      PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
      writer.println("hi hi hi bye");
      writer.close();
      invalidFleetSpec = new Scanner(tempFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    boardSpecs = new BoardSpecs();
    testBoard = new int[2];
    testBoard[0] = 6;
    testBoard[1] = 8;
  }

  @Test
  void testGetHeightAndWidth() {
    boardSpecs.setScanner(boardDimensionScanner);
    int[] dimensions = new int[2];
    dimensions[0] = 6;
    dimensions[1] = 6;
    int[] heightAndWidth = boardSpecs.getHeightAndWidth(boardDimensionScanner);
    assertEquals(dimensions[0], heightAndWidth[0]);
    assertEquals(dimensions[1], heightAndWidth[1]);
  }

  @Test
  void testMaxFleetSize() {
    int maxFleetSize = boardSpecs.maxFleetSize(testBoard);
    assertEquals(6, maxFleetSize);
    testBoard[0] = 8;
    testBoard[1] = 6;
    int maxFleetSize2 = boardSpecs.maxFleetSize(testBoard);
    assertEquals(6, maxFleetSize2);
    testBoard[0] = 6;
    testBoard[1] = 6;
    int maxFleetSize3 = boardSpecs.maxFleetSize(testBoard);
    assertEquals(6, maxFleetSize3);
  }

  @Test
  void testGetShipSpecifications() {
    boardSpecs.setScanner(fleetSpecScanner);
    Map<ShipType, Integer> testFleet;
    testFleet = new HashMap<>();
    testFleet.put(ShipType.CARRIER, 1);
    testFleet.put(ShipType.BATTLESHIP, 2);
    testFleet.put(ShipType.DESTROYER, 2);
    testFleet.put(ShipType.SUBMARINE, 1);
    Map<ShipType, Integer> fleetSpecs = boardSpecs.getShipSpecifications(6);
    assertEquals(testFleet, fleetSpecs);
  }
}