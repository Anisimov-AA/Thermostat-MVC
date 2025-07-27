package view;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class ThermostatView extends JFrame implements IThermostatView {
  // Window configuration
  private static final String WINDOW_TITLE = "Smart Thermostat Control";
  private static final int WINDOW_WIDTH = 400;
  private static final int WINDOW_HEIGHT = 300;
  private static final int PADDING = 10;

  /**
   * Constructs a new ThermostatView
   */
  public ThermostatView() {
    setupFrame();
  }

  /**
   * Sets up the basic frame properties.
   */
  private void setupFrame() {
    setTitle(WINDOW_TITLE);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setResizable(false);
    setLocationRelativeTo(null);

    // use system look and feel for better integration
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      // fall back to default if system L&F fails
    }
  }

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

  public static void main(String[] args) {
    ThermostatView view = new ThermostatView();
    view.setVisible(true);
  }
}
