package main.model;

/**
 * Implementation of the thermostat model following MVC pattern
 * Manages thermostat state, temperature control logic, and system behavior
 */
public class ThermostatModel implements IThermostatModel {

  // Temperature bounds
  private static final double MIN_TEMPERATURE = 10.0;
  private static final double MAX_TEMPERATURE = 35.0;

  // System behavior constants
  private static final double TEMPERATURE_CHANGE_RATE = 0.1;
  private static final double TOLERANCE = 0.05; // small tolerance to prevent oscillation

  // Initial temperature
  private static final double DEFAULT_TEMPERATURE = 20.0; // 20°C

  // State variables
  private double currentTemperature;
  private double targetTemperature;
  private boolean heatingOn;
  private boolean coolingOn;

  /**
   * Constructs a new ThermostatModel with default settings
   */
  public ThermostatModel() {
    this.currentTemperature = DEFAULT_TEMPERATURE;
    this.targetTemperature = DEFAULT_TEMPERATURE;
    this.heatingOn = false;
    this.coolingOn = false;
  }

  @Override
  public void setTargetTemperature(double temperature) {
    // first check bounds
    if(temperature < MIN_TEMPERATURE || temperature > MAX_TEMPERATURE) {
      throw new IllegalArgumentException(
          String.format("Temperature range: %.1f°C – %.1f°C. Provided: %.1f°C",
              MIN_TEMPERATURE, MAX_TEMPERATURE, temperature)
      );
    }

    // check if it's in 0.1 increments
    double scaled = temperature * 10;
    if(scaled != Math.floor(scaled)) {
      throw new IllegalArgumentException(
          "Temperature must be in 0.1°C increments (e.g., 20.0, 20.1, 20.2)"
      );
    }

    this.targetTemperature = temperature;
  }

  @Override
  public double getCurrentTemperature() {
    return this.currentTemperature;
  }

  @Override
  public double getTargetTemperature() {
    return this.targetTemperature;
  }

  @Override
  public boolean isHeating() {
    return this.heatingOn;
  }

  @Override
  public boolean isCooling() {
    return this.coolingOn;
  }

  @Override
  public void updateSystem() {
    if(currentTemperature < targetTemperature - TOLERANCE) {
      // too cold - heat up
      currentTemperature += TEMPERATURE_CHANGE_RATE;
      heatingOn = true;
      coolingOn = false;
    } else if(currentTemperature > targetTemperature + TOLERANCE) {
      // too hot - cool down
      currentTemperature -= TEMPERATURE_CHANGE_RATE;
      heatingOn = false;
      coolingOn = true;
    } else {
      // within tolerance - turn off
      coolingOn = false;
      heatingOn = false;
    }
  }

  @Override
  public double getMinTemperature() {
    return MIN_TEMPERATURE;
  }

  @Override
  public double getMaxTemperature() {
    return MAX_TEMPERATURE;
  }

  @Override
  public String toString() {
    return "curr temp: " + currentTemperature + "\n" +
        "target temp: " + targetTemperature + "\n" +
        "heatingOn: " + heatingOn + "\n" +
        "coolingOn: " + coolingOn + "\n";
  }

//  public static void main(String[] args) {
//    ThermostatModel model = new ThermostatModel();
//    System.out.println(model);
//
//    model.setTargetTemperature(21);
//    model.updateSystem();
//    System.out.println(model);
//  }
}
