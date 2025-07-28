package test.controller;

import java.awt.event.ActionListener;
import main.view.IThermostatView;

public class MockThermostatView implements IThermostatView {

  @Override
  public void addListener(ActionListener listener) {

  }

  @Override
  public String getInput() {
    return "";
  }

  @Override
  public void clearInput() {

  }

  @Override
  public void showMessage(String message, boolean isError) {

  }

  @Override
  public void updateDisplay(double currentTemp, double targetTemp, boolean isHeating,
      boolean isCooling) {

  }

  @Override
  public void setVisible(boolean visible) {

  }
}
