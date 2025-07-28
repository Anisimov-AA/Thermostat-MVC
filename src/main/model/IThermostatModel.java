package main.model;

/**
 * Interface defining the contract for a thermostat model in the MVC pattern
 * The model is responsible for maintaining thermostat state and business logic
 */
public interface IThermostatModel {

  /**
   * Sets the target temperature for the thermostat
   * @param temperature the desired temperature in Celsius
   * @throws IllegalArgumentException if temperature is outside valid range
   */
  void setTargetTemperature(double temperature);

  /**
   * Gets the current room temperature
   * @return the current temperature in Celsius
   */
  double getCurrentTemperature();

  /**
   * Gets the target temperature setting.
   * @return the target temperature in Celsius
   */
  double getTargetTemperature();

  /**
   * Checks if the heating system is currently active
   * @return true if heating is on, false otherwise
   */
  boolean isHeating();

  /**
   * Checks if the cooling system is currently active
   * @return true if cooling is on, false otherwise
   */
  boolean isCooling();

  /**
   * Updates the thermostat system state
   * This method should be called periodically to:
   * Adjust current temperature towards target</li>
   * Update heating/cooling system state</li>
   */
  void updateSystem();

  /**
   * Gets the minimum allowed temperature setting
   * @return the minimum temperature in Celsius
   */
  double getMinTemperature();

  /**
   * Gets the maximum allowed temperature setting
   * @return the maximum temperature in Celsius
   */
  double getMaxTemperature();
}
