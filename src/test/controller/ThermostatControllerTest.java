package test.controller;

import main.controller.IThermostatController;
import main.controller.ThermostatController;
import main.model.IThermostatModel;
import main.view.IThermostatView;
import org.junit.jupiter.api.BeforeEach;

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
  private MockThermostatModel model;
  private MockThermostatView view;

  @BeforeEach
  void setUp(){
    model = new MockThermostatModel();
    view = new MockThermostatView();
    controller = new ThermostatController(model, view);
    controller.run();
  }
}
