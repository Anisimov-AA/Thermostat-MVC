package view;

import java.awt.event.ActionListener;

/**
 * Interface defining the contract for a thermostat view
 */
public interface IThermostatView {

  // For user input

  /**
   * Adds an action listener to respond to temperature setting requests
   * @param listener the ActionListener to be notified when user clicks the set temp button
   */
  void addListener (ActionListener listener);

  /**
   * Retrieves the temperature value entered by the user
   * @return the temperature input as a String, trimmed of leading and trailing whitespace
   */
  String getInput();

  /**
   * Clears the temperature input field and returns focus on it preparing for the next user input
   */
  void clearInput();

  // For user feedback
  /**
   * Displays a feedback message to the user
   * The message should be displayed in a non-intrusive way and may automatically disappear after a short duration
   * @param message the message text to display.
   * @param isError true if it is an error message (shown in red)
   *                false for information messages (shown in blue)
   */
  void showMessage(String message, boolean isError);

  // For display updates

  /**
   * Updates the temperature display and system status indicators.
   * This method should be called whenever the thermostat state changes
   * @param currentTemp the current temperature reading in degrees Celsius
   * @param targetTemp the target temperature setting in degrees Celsius
   * @param isHeating true if the heating system is currently active, false otherwise
   * @param isCooling true if the cooling system is currently active, false otherwise
   */
  void updateDisplay(double currentTemp, double targetTemp,
      boolean isHeating, boolean isCooling);
}
