package test.controller;

import main.model.IThermostatModel;

public class MockThermostatModel implements IThermostatModel {

  @Override
  public void setTargetTemperature(double temperature) {

  }

  @Override
  public double getCurrentTemperature() {
    return 0;
  }

  @Override
  public double getTargetTemperature() {
    return 0;
  }

  @Override
  public boolean isHeating() {
    return false;
  }

  @Override
  public boolean isCooling() {
    return false;
  }

  @Override
  public void updateSystem() {

  }

  @Override
  public double getMinTemperature() {
    return 0;
  }

  @Override
  public double getMaxTemperature() {
    return 0;
  }
}
