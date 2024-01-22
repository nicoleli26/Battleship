package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.controller.ProxyController;
import cs3500.pa04.model.AutomatedBoard;
import cs3500.pa04.model.AutomatedPlayer;
import cs3500.pa04.model.Coord;
import cs3500.pa04.model.GameResult;
import cs3500.pa04.model.GameType;
import cs3500.pa04.model.ShipType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test correct responses for different requests from the socket using a Mock Socket (mocket)
 */
public class ProxyControllerTest {

  private ByteArrayOutputStream testLog;
  private ProxyController controller;
  int[][] board;

  /**
   * Reset the test log before each test is run.
   */
  @BeforeEach
  public void setup() {
    this.testLog = new ByteArrayOutputStream(2048);
    assertEquals("", logToString());
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

  @Test
  public void testDelegateMessage() {
    Map<ShipType, Integer> fleetSpecs = new HashMap<>();
    SetupJson setupJson = new SetupJson(6, 6, fleetSpecs);
    JsonNode setupMessage = createSampleMessage("setup", setupJson);

  }

  /**
   * Check that the server returns a guess when given a hint.
   */
  @Test
  public void testVoidForWin() {
    // Prepare sample message
    EndGameJson endGameJson = new EndGameJson(GameResult.WIN, "Someone has won");
    JsonNode sampleMessage = createSampleMessage("end-game", endGameJson);

    // Create the client with all necessary messages
    Mocket socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));

    // Create a Dealer
    try {
      this.controller = new ProxyController(socket, new AutomatedPlayer(new AutomatedBoard()));
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // run the dealer and verify the response
    this.controller.run();

    String expected = "{\"method-name\":\"end-game\",\"arguments\":\"void\"}\n";
    assertEquals(expected, logToString());
  }

  @Test
  public void testHandleSetup() {
    Map<ShipType, Integer> fleetSpecs = new HashMap<>();
    SetupJson setupJson = new SetupJson(6, 6, fleetSpecs);
    JsonNode sampleMessage = createSampleMessage("setup", setupJson);

    // Create the client with all necessary messages
    Mocket socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));

    // Create a Dealer
    try {
      this.controller = new ProxyController(socket, new AutomatedPlayer(new AutomatedBoard()));
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // run the dealer and verify the response
    this.controller.run();

    String expected = logToString();
    assertTrue(expected.contains("setup"));
    assertTrue(expected.contains("fleet"));
  }

  @Test
  public void testHandleTakeShots() {
    AutomatedPlayer automatedPlayer = new AutomatedPlayer(new AutomatedBoard());
    Map<ShipType, Integer> fleetSpecs = new HashMap<>();
    automatedPlayer.setup(6, 6, fleetSpecs);
    List<Coord> coords = new ArrayList<>();
    VolleyJson volleyJson = new VolleyJson(coords);
    JsonNode sampleMessage = createSampleMessage("take-shots", volleyJson);

    // Create the client with all necessary messages
    Mocket socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));

    // Create a Dealer
    try {
      this.controller = new ProxyController(socket, automatedPlayer);
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // run the dealer and verify the response
    this.controller.run();

    String expected = logToString();
    assertTrue(expected.contains("take-shots"));
    assertTrue(expected.contains("coordinates"));
  }

  @Test
  public void testHandleReportDamage() {
    AutomatedBoard automatedBoard = new AutomatedBoard();
    AutomatedPlayer automatedPlayer = new AutomatedPlayer(automatedBoard);
    Map<ShipType, Integer> fleetSpecs = new HashMap<>();
    automatedPlayer.setup(6, 6, fleetSpecs);
    List<Coord> coords = new ArrayList<>();
    Coord coord = new Coord(1, 1);
    coords.add(coord);
    VolleyJson volleyJson = new VolleyJson(coords);
    JsonNode sampleMessage = createSampleMessage("report-damage", volleyJson);

    // Create the client with all necessary messages
    Mocket socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));

    // Create a Dealer
    try {
      this.controller = new ProxyController(socket, automatedPlayer);
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // run the dealer and verify the response
    this.controller.run();

    String expected = logToString();
    assertTrue(expected.contains("report-damage"));
    assertTrue(expected.contains("coordinates"));
  }

  @Test
  public void testHandleSuccessfulHits() {
    List<Coord> coords = new ArrayList<>();
    VolleyJson volleyJson = new VolleyJson(coords);
    JsonNode sampleMessage = createSampleMessage("successful-hits", volleyJson);

    // Create the client with all necessary messages
    Mocket socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));

    // Create a Dealer
    try {
      this.controller = new ProxyController(socket, new AutomatedPlayer(new AutomatedBoard()));
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // run the dealer and verify the response
    this.controller.run();

    String expected = logToString();
    assertTrue(expected.contains("successful-hits"));
    assertTrue(expected.contains("void"));
  }

  /**
   * Check that the server returns a guess when given a hint.
   */
  @Test
  public void testJoin() {
    // Create sample hint
    JoinJson joinJson = new JoinJson("cam-zabs", GameType.SINGLE);
    JsonNode jsonNode = createSampleMessage("join", joinJson);

    // Create socket with sample input
    Mocket socket = new Mocket(this.testLog, List.of(jsonNode.toString()));

    // Create a dealer
    try {
      this.controller = new ProxyController(socket, new AutomatedPlayer(new AutomatedBoard()));
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // Run dealer and verify response.
    this.controller.run();

    String expected = logToString();
    assertTrue(expected.contains("join"));
    assertTrue(expected.contains("name"));
    assertTrue(expected.contains("game-type"));
  }

  @Test
  public void testException() {
    JoinJson joinJson = new JoinJson("cam-zabs", GameType.SINGLE);
    JsonNode jsonNode = createSampleMessage("hi", joinJson);

    // Create socket with sample input
    Mocket socket = new Mocket(this.testLog, List.of(jsonNode.toString()));

    // Create a dealer
    try {
      this.controller = new ProxyController(socket, new AutomatedPlayer(new AutomatedBoard()));
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // Run dealer and verify response.
    assertThrows(IllegalStateException.class, () -> this.controller.run());
  }

  /**
   * Converts the ByteArrayOutputStream log to a string in UTF_8 format
   *
   * @return String representing the current log buffer
   */
  private String logToString() {
    return testLog.toString(StandardCharsets.UTF_8);
  }

  /**
   * Try converting the current test log to a string of a certain class.
   *
   * @param classRef Type to try converting the current test stream to.
   * @param <T>      Type to try converting the current test stream to.
   */
  private <T> void responseToClass(@SuppressWarnings("SameParameterValue") Class<T> classRef) {
    try {
      JsonParser jsonParser = new ObjectMapper().createParser(logToString());
      jsonParser.readValueAs(classRef);
      // No error thrown when parsing to a GuessJson, test passes!
    } catch (IOException e) {
      // Could not read
      // -> exception thrown
      // -> test fails since it must have been the wrong type of response.
      fail();
    }
  }

  /**
   * Create a MessageJson for some name and arguments.
   *
   * @param messageName name of the type of message; "hint" or "win"
   * @param messageObject object to embed in a message json
   * @return a MessageJson for the object
   */
  private JsonNode createSampleMessage(String messageName, Record messageObject) {
    MessageJson messageJson = new MessageJson(messageName,
        JsonUtils.serializeRecord(messageObject));
    return JsonUtils.serializeRecord(messageJson);
  }
}