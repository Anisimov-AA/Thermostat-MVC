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

    // 3. Check input was cleared
    assertTrue(mockView.wasClearInputCalled());
  }
}
