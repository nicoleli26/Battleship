package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.json.ShipJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for Ship class
 */
public class ShipTest {
  Ship testShip;
  Coord coord;

  /**
   * Setup for ShipTest
   */
  @BeforeEach
  public void setup() {
    this.coord = new Coord(1, 1);
    this.testShip = new Ship(coord, 4, Orientation.VERTICAL);
  }

  @Test
  public void testShipToJson() {
    ShipJson testShipJson = new ShipJson(coord, 4, Orientation.VERTICAL);
    assertEquals(testShipJson, testShip.shipToJson());
    assertEquals(coord, testShipJson.coord());
    assertEquals(4, testShipJson.length());
    assertEquals(Orientation.VERTICAL, testShipJson.direction());
  }
}
