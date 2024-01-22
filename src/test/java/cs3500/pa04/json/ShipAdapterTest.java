package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.Coord;
import cs3500.pa04.model.Orientation;
import cs3500.pa04.model.Ship;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for ShipAdapter class
 */
public class ShipAdapterTest {
  ShipAdapter shipAdapter;
  List<Ship> ships;
  List<ShipJson> shipJsons;
  Ship ship1;
  Ship ship2;

  /**
   * Setup for ShipAdapter test
   */
  @BeforeEach
  public void setup() {
    shipAdapter = new ShipAdapter();
    ships = new ArrayList<>();
    shipJsons = new ArrayList<>();
    ship1 = new Ship(new Coord(1, 1), 4, Orientation.VERTICAL);
    ship2 = new Ship(new Coord(2, 2), 4, Orientation.VERTICAL);
    ships.add(ship1);
    ships.add(ship2);
    ShipJson shipJson1 = ship1.shipToJson();
    ShipJson shipJson2 = ship2.shipToJson();
    shipJsons.add(shipJson1);
    shipJsons.add(shipJson2);
  }

  @Test
  public void adapt() {
    assertEquals(shipJsons, shipAdapter.adapt(ships));
  }
}
