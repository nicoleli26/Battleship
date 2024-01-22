package cs3500.pa04.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa04.model.AutomatedBoard;
import cs3500.pa04.model.PlayerBoard;
import cs3500.pa04.view.BoardSpecs;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for BattleSalvo test
 */
class BattleSalvoTest {

  private BattleSalvo game;
  Readable readable;
  BoardSpecs boardSpecs = new BoardSpecs();

  /**
   * Setup for BattleSalvoTest
   */
  @BeforeEach
  public void setup() {
    try {
      File tempFile = File.createTempFile("ExampleInputs", ".txt");
      PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
      writer.println("6 6");
      writer.println("1 2 2 1");
      writer.close();
      readable = new FileReader(tempFile.getAbsolutePath());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    game = new BattleSalvo(readable);
  }

  @Test
  public void testGameOver() {

    game.playerBoard = new PlayerBoard();
    game.automatedBoard = new AutomatedBoard();
    game.playerBoard.makeBoard(6, 6);
    game.automatedBoard.makeBoard(6, 6);


    assertTrue(game.playerBoard.isGameOver());


    game.gameOver();
    assertTrue(game.automatedBoard.isGameOver());
  }

  @Test
  public void testMakeBoards() {
    assertEquals(null, boardSpecs.asker);
    game.makeBoards();
    assertNotNull(boardSpecs.asker);
  }

}