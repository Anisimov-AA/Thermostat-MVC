package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import main.model.IThermostatModel;
import main.model.ThermostatModel;
import main.view.IThermostatView;
import main.view.ThermostatView;

/**
 * Implementation of the thermostat controller following MVC pattern.
 * The controller's job is to:
 * 1. Listen for user actions from the View
 * 2. Update the Model based on those actions
 * 3. Update the View to reflect Model changes
 */
public class ThermostatController implements IThermostatController, ActionListener {
  private IThermostatModel model;
  private IThermostatView view;

  private Timer updateTimer;
  private static final int UPDATE_INTERVAL_MS = 1000; // 1 second

  // Fields to track previous state
  private boolean wasHeating = false;
  private boolean wasCooling = false;


  /**
   * Creates a new controller with the given model and view
   *
   * Note: We use interfaces (IThermostatModel, IThermostatView) not concrete classes
   * This makes our controller flexible - it can work with ANY implementation
   * of these interfaces
   *
   * @param model the thermostat model containing business logic
   * @param view the user interface
   */
  public ThermostatController(IThermostatModel model, IThermostatView view) {
    this.model = model;
    this.view = view;
  }

  /**
   * Starts the thermostat application
   */
  @Override
  public void run() {
    view.addListener(this);
    setupUpdateTimer();
    updateView();
    view.setVisible(true);
  }

  /**
   * Sets up the timer that updates the temperature regularly
   * This simulates a real thermostat that constantly monitors temperature
   */
  private void setupUpdateTimer() {
    updateTimer = new Timer(UPDATE_INTERVAL_MS, e -> {
        // this code runs every second
        model.updateSystem();
        updateView();
    });
    updateTimer.start(); // start the timer
  }

  /**
   * Updates the view with current values from the model
   * This is called both by the timer and after user actions
   */
  private void updateView(){
    boolean isHeating = model.isHeating();
    boolean isCooling = model.isCooling();

    if(wasHeating && !isHeating) { // Was heating AND now IS NOT heating
      view.showMessage("Heating complete", false);
    }

    if(wasCooling && !isCooling) {
      view.showMessage("Cooling complete", false);
    }

    view.updateDisplay(
        model.getCurrentTemperature(),
        model.getTargetTemperature(),
        model.isHeating(),
        model.isCooling()
    );

    // remember current state for next update
    wasHeating = isHeating;
    wasCooling = isCooling;
  }

  /**
   * Handles the "Set Temperature" button click
   * This method is called automatically when user clicks the button
   * @param e the event details (we don't need these for a single button)
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    String userInput = view.getInput();

    try {
      userInput = userInput.replace(",", ".");

      // try to convert text to a number
      double temperature = Double.parseDouble(userInput);

      // this might throw IllegalArgumentException for TWO reasons:
      // 1. out of bounds (e.g., 123)
      // 2. wrong precision (e.g., 20.15)
      model.setTargetTemperature(temperature);

      view.showMessage("Target set to " + temperature + "°C", false);
      view.clearInput();
    } catch (NumberFormatException ex) {
      // user typed something that's not a number (like "abc")
      view.showMessage("Please enter a valid number", true);
    } catch (IllegalArgumentException ex) {
      // model validation failed (bounds OR precision)
      // the model gives us the specific error message
      view.showMessage(ex.getMessage(), true);
    }
  }

//  public static void main(String[] args) {
//      IThermostatModel model = new ThermostatModel();
//      IThermostatView view = new ThermostatView();
//      IThermostatController controller = new ThermostatController(model, view);
//
//      controller.run();
//    }
}
