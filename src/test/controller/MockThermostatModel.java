package test.controller;

import main.model.IThermostatModel;

public class MockThermostatModel implements IThermostatModel {

  // Tracking variables
  private boolean setTargetTemperatureCalled  = false;
  private double lastSetTargetTemperature;

  // Simulating validation errors when needed
  private boolean shouldThrowException = false;
  private String exceptionMessage = "";

  @Override
  public void setTargetTemperature(double temperature) {
    setTargetTemperatureCalled = true;
    lastSetTargetTemperature = temperature;

    if (shouldThrowException) {
      throw new IllegalArgumentException(exceptionMessage);
    }
  }

  // These methods are NOT used by actionPerformed
  // So we just return simple, predictable values
  @Override
  public double getCurrentTemperature() {
    return 20.0;
  }

  @Override
  public double getTargetTemperature() {
    return 20.0;
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
    // empty
  }

  @Override
  public double getMinTemperature() {
    return 10.0;
  }

  @Override
  public double getMaxTemperature() {
    return 10.0;
  }

  // TEST HELPER METHODS
  // Let test check if method was called
  boolean wasTheSetTargetTemperatureMethodCalled() {
    return setTargetTemperatureCalled;
  }

  // Let test check what value was passed
  double whatValueWasPassedToSetTargetTemperature() {
    return lastSetTargetTemperature;
  }

  // Let test configure error behavior
  void configureToThrowException(String message) {
    this.shouldThrowException = true;
    this.exceptionMessage = message;
  }
}