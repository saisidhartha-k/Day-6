package Controller;



public class OdometerControllerpackage {


  private static final String DIGITS = "123456789";

  private int reading;

  private static int getMinReading(int size) {
    return Integer.valueOf(DIGITS.substring(0, size));
  }

  private static int getMaxReading(int size) {
    return Integer.valueOf(DIGITS.substring(DIGITS.length() - size, DIGITS.length()));
  }

  private static int getSize(int reading) {
    return String.valueOf(reading).length();
  }
  
  public int getSize() {
    return getSize(this.reading);
  }

  public OdometerControllerpackage(int size) {
    reading = getMinReading(size);
  }

  public OdometerControllerpackage(OdometerControllerpackage copy) {
    reading = copy.reading;
  }

  public int getReading() {
    return reading;
  }

  /**
   * Reading must match the size of the existing reading.
   * All digits in the reading must be in strictly ascending
   * order left to right.

   * @param reading the reading to set
   * @throws ReadingException if reading doesn't satisfy the above validations
   */
  public void setReading(int reading) throws ReadingException {
    if (!isAscending(reading)) {
      throw new NonAscendingReadingException("Reading has to have all its digits "
          + "in strictly ascending order. (got: " + reading + ")");
    } else if (getSize(reading) != getSize(this.reading)) {
      throw new ReadingSizeMismatchException("Reading has to be "
          + "the same size as the previous reading. "
          + "(Existing: " + this.reading + " got : " + reading + ")");
    } else {
      this.reading = reading;
    }
  }

  @Override
  public String toString() {
    return "(" + reading + ")";
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof OdometerControllerpackage)) {
      return false;
    }
    OdometerControllerpackage otherOdo = (OdometerControllerpackage) other;
    return otherOdo.reading == this.reading;
  }

  /** "Ascending" here means strictly ascending.

   * @return true iff the digits are in strictly ascending order
   */
  public static boolean isAscending(int reading) {
    if (reading < 10) {
      return true;
    }
    if (reading % 10 <= (reading / 10) % 10) {
      return false;
    }
    return isAscending(reading / 10);
  }
  
  /**
   * This mutates the reading.
   */
  public void increment() {
    do {
      if (reading == getMaxReading(getSize(reading))) {
        reading = getMinReading(getSize(reading));
      } else {
        reading++;
      }
    } while (!isAscending(reading));
  }

  /**
   * This doesn't mutate the reading.

   * @return a new Odometer object that has the next reading to this.
   */
  public OdometerControllerpackage nextReading() {
	  OdometerControllerpackage temp = new OdometerControllerpackage(this);
    temp.increment();
    return temp;
  }

  /**
   * This mutates the reading.
   */
  public void decrementReading() {
    do {
      if (reading == getMinReading(getSize(reading))) {
        reading = getMaxReading(getSize(reading));
      } else {
        reading--;
      }
    } while (!isAscending(reading));
  }

  public void reset() {
    this.reading = getMinReading(getSize(this.reading));
  }

  
}