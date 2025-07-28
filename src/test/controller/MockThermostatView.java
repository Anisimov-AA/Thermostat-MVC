package test.controller;

import java.awt.event.ActionListener;
import main.view.IThermostatView;

public class MockThermostatView implements IThermostatView {

  // What we'll return when asked
  private String stubbedInput = "20.0";  // Default input
  private ActionListener registeredListener;  // Store the controller

  // Track what controller did to the view
  private String lastShownMessage;
  private boolean lastMessageWasError;
  private boolean clearInputCalled = false;

  @Override
  public void clearInput() {
    clearInputCalled = true;
    stubbedInput = "";
  }

  @Override
  public void addListener(ActionListener listener) {
    this.registeredListener = listener;  // store the listener (controller) so we can trigger it later
  }

  @Override
  public String getInput() {
    return stubbedInput;
  }

  @Override
  public void showMessage(String message, boolean isError) {
    this.lastShownMessage = message;
    this.lastMessageWasError = isError;
  }

  // These methods are NOT used by actionPerformed
  @Override
  public void updateDisplay(double currentTemp, double targetTemp, boolean isHeating,
      boolean isCooling) {
    // Empty - not called during button click
  }

  @Override
  public void setVisible(boolean visible) {
    // Empty - not called during button click
  }

  // TEST HELPER METHODS
  // Configure what "user typed"
  void setUserInput(String input){
    this.stubbedInput = input;
  }

  // Simulate button click
  void simulateButtonClick(){
    if(registeredListener != null) {
      registeredListener.actionPerformed(null);
    }
  }

  // Verify what happened
  String getLastShownMessage(){
    return lastShownMessage;
  }

  boolean wasLastMessageAnError() {
    return lastMessageWasError;
  }

  boolean wasClearInputCalled() {
    return clearInputCalled;
  }
}