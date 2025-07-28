package test.model;

import static org.junit.jupiter.api.Assertions.*;

import main.model.IThermostatModel;
import main.model.ThermostatModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for ThermostatModel.
 * Tests core business logic and edge cases.
 */
class ThermostatModelTest {

  private IThermostatModel model;

  /**
   * Sets up a fresh model instance before each test.
   */
  @BeforeEach
  void setUp() {
    model = new ThermostatModel();
  }

  /**
   * Tests that the model initializes with correct default values.
   */
  @Test
  void testInitialState() {
    assertEquals(20.0, model.getCurrentTemperature(), 0.01);
    assertEquals(20.0, model.getTargetTemperature(), 0.01);
    assertFalse(model.isHeating());
    assertFalse(model.isCooling());
  }

  @Test
  /**
   * Test heating activates when cold
   */
  void testHeatingActivation() {
    model.setTargetTemperature(21.0);
    model.updateSystem();

    assertTrue(model.isHeating());
    assertFalse(model.isCooling());
    assertTrue(model.getCurrentTemperature() > 20.0);
  }

  @Test
  /**
   * Test cooling activates when hot
   */
  void testCoolingActivation() {
    model.setTargetTemperature(19.0);
    model.updateSystem();

    assertFalse(model.isHeating());
    assertTrue(model.isCooling());
    assertTrue(model.getCurrentTemperature() < 20.0);
  }

  /**
   * Test the core business rule: systems off within tolerance
   */
  @Test
  void testSystemsOffWithinTolerance() {
    model.setTargetTemperature(20.03);
    model.updateSystem();

    assertFalse(model.isHeating());
    assertFalse(model.isCooling());
  }

  /**
   * Test temperature bounds validation
   */
  @Test
  void testTemperatureBoundaries() {
    // valid boundaries
    assertDoesNotThrow(() -> model.setTargetTemperature(10.0));
    assertDoesNotThrow(() -> model.setTargetTemperature(35.0));

    // invalid boundaries
    assertThrows(IllegalArgumentException.class,
        () -> model.setTargetTemperature(9.9));
    assertThrows(IllegalArgumentException.class,
        () -> model.setTargetTemperature(35.1));
  }
}