package cs3500.pa04.model;

/**
 * Mock Random class for testing
 */
public class MockRandom implements Randomable {
  private int index = 0;
  private final int[] numbers = {0, 1, 0, 4, 2, 1, 3, 2, 5, 3, 1}; // you choose these

  /**
   * Constructor for MockRandom
   */
  public MockRandom() {}

  @Override
  public int nextInt(int bound) {
    /* Retrieve */ int next = numbers[index];
    /* Iterate */ this.index = this.index < numbers.length - 1 ? this.index + 1 : 0;
    /* Return */ return next;
  }
}
