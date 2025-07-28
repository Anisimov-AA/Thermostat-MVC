package test.controller;

import static org.junit.jupiter.api.Assertions.*;

import main.controller.IThermostatController;
import main.controller.ThermostatController;
import main.model.IThermostatModel;
import main.view.IThermostatView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing: "Does the controller correctly coordinate between Model and View?"
 *
 * // In ThermostatController:
 * public void actionPerformed(ActionEvent e) {
 *     // 1. Gets input from view
 *     // 2. Validates input (parsing)
 *     // 3. Sends to model
 *     // 4. Handles model validation errors
 *     // 5. Updates view with results
 * }
 *
 * Test Cases We Need:
 * - Valid temperature input → Success flow
 * - Invalid number input → Parse error handling
 * - Out of bounds temperature → Model validation error
 * - Edge cases (empty input, decimal formats)
 */
public class ThermostatControllerTest {
  private ThermostatController controller;
  private MockThermostatModel mockModel;
  private MockThermostatView mockView;

  @BeforeEach
  void setUp(){
    mockModel = new MockThermostatModel();
    mockView = new MockThermostatView();
    controller = new ThermostatController(mockModel, mockView);
    controller.run();
  }

  // Happy path tests
  @Test
  void testValidTemperatureInput(){
    // set up the test scenario
    mockView.setUserInput("25.0"); // user "types" 25.0
    mockView.simulateButtonClick(); // uer "clicks" button

    // check what happened
    // 1. check model was called correctly
    assertTrue(mockModel.wasSetTargetTemperatureCalled());
    assertEquals(25.0, mockModel.getLastSetTargetTemperature());

    // 2. check success message was shown
    assertEquals("Target set to 25.0°C", mockView.getLastShownMessage());
    assertFalse(mockView.wasLastMessageAnError());

    // 3. check input was cleared
    assertTrue(mockView.wasClearInputCalled());
  }

  // Error handling tests
  @Test
  void testInvalidNumberInput() {
    // set up the test scenario
    mockView.setUserInput("abc");
    mockView.simulateButtonClick();

    // check what happened
    // 1. model should NOT be called
    assertFalse(mockModel.wasSetTargetTemperatureCalled());

    // 2. error message should be shown
    assertEquals("Please enter a valid number", mockView.getLastShownMessage());
    assertTrue(mockView.wasLastMessageAnError());

    // 3. input should NOT be cleared (let user fix it)
    assertFalse(mockView.wasClearInputCalled());
  }

  @Test
  void testOutOfBoundsTemperature() {
    // set up the test scenario
    mockView.setUserInput("50.0");
    mockModel.configureToThrowException("Temperature must be between 10.0°C and 35.0°C"); // configure model to throw exception
    mockView.simulateButtonClick();

    // check what happened
    // 1. model WAS called (we need to try before we know it's invalid)
    assertTrue(mockModel.wasSetTargetTemperatureCalled());

    // 2. model's error message should be shown to user
    assertEquals("Temperature must be between 10.0°C and 35.0°C", mockView.getLastShownMessage());
    assertTrue(mockView.wasLastMessageAnError());

    // 3. input should NOT be cleared
    assertFalse(mockView.wasClearInputCalled());
  }

  // Edge case tests
  @Test
  void testCommaDecimalSeparator() {
    // set up the test scenario
    mockView.setUserInput("22,5");
    mockView.simulateButtonClick();

    // check what happened
    assertTrue(mockModel.wasSetTargetTemperatureCalled());
    assertEquals(22.5, mockModel.getLastSetTargetTemperature());
    assertEquals("Target set to 22.5°C", mockView.getLastShownMessage());
  }

  @Test
  void testEmptyInput() {
    // set up the test scenario
    mockView.setUserInput("");
    mockView.simulateButtonClick();

    // check what happened
    assertFalse(mockModel.wasSetTargetTemperatureCalled());
    assertEquals("Please enter a valid number", mockView.getLastShownMessage());
    assertTrue(mockView.wasLastMessageAnError());
  }

  @Test
  void testWhitespaceInput() {
    // set up the test scenario
    mockView.setUserInput("   25.0   ");
    mockView.simulateButtonClick();

    // check what happened
    assertTrue(mockModel.wasSetTargetTemperatureCalled());
    assertEquals(25.0, mockModel.getLastSetTargetTemperature());
  }

}
