package main.controller;

/**
 *  * Interface defining the contract for a thermostat controller in the MVC pattern
 *  * The controller coordinates between the model and view components
 */
public interface IThermostatController {

  /**
   * Starts the controller and runs the application.
   * This method should:
   * - Register event listeners with the view
   * - Start the temperature update timer
   * - Display the view to the user
   * The application will run until the user closes the window.
   */
  void run();

}
