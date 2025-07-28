package main;

import main.controller.IThermostatController;
import main.controller.ThermostatController;
import main.model.IThermostatModel;
import main.model.ThermostatModel;
import main.view.IThermostatView;
import main.view.ThermostatView;

public class ThermostatApp {
  public static void main(String[] args) {
      IThermostatModel model = new ThermostatModel();
      IThermostatView view = new ThermostatView();
      IThermostatController controller = new ThermostatController(model, view);

      controller.run();
  }
}