package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import view.utils.StyleConfig;

public class ThermostatView extends JFrame implements IThermostatView {
  // Window configuration
  private static final String WINDOW_TITLE = "Smart Thermostat Control";
  private static final int WINDOW_WIDTH = 400;
  private static final int WINDOW_HEIGHT = 300;
  private static final int PADDING = 10;

  // Temperature unit
  private static final String DEGREE_SYMBOL = "°C";

  // Display components
  private JLabel currentTempLabel;
  private JLabel targetTempLabel;

  /**
   * Constructs a new ThermostatView
   */
  public ThermostatView() {
    setupFrame();
    createComponents();
    layoutComponents();
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


  private void createComponents() {
    // display components
    this.currentTempLabel = createDisplayLabel("--.-", StyleConfig.Fonts.TEMP_CURRENT_FONT);
    this.targetTempLabel = createDisplayLabel("--.-", StyleConfig.Fonts.TEMP_TARGET_FONT);
  }

  /**
   * Creates a label to display something
   * @param text the initial text
   * @param font the font to use
   * @return configured JLabel
   */
  private JLabel createDisplayLabel(String text, Font font) {
    JLabel label = new JLabel(text);
    label.setFont(font);

    return label;
  }


  /**
   * Arranges all UI components in the main frame
   * Uses BorderLayout with three main sections: display, control, and messages
   */
  private void layoutComponents() {
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());

    // top panel - temperature display
    mainPanel.add(createDisplayPanel(), BorderLayout.NORTH);

    // center panel - controls
    mainPanel.add(createControlPanel(), BorderLayout.CENTER);

    // bottom panel - message
    mainPanel.add(createMessagePanel(), BorderLayout.SOUTH);

    add(mainPanel);
  }

  /**
   * Creates the temperature display panel.
   * This panel shows current temperature, target temperature, and heating and cooling indicators
   * @return JPanel configured for temperature display
   */
  private JPanel createDisplayPanel() {
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBorder(new TitledBorder("Temperature Display"));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);

    gbc.gridx = 0; gbc.gridy = 0;
    panel.add(new JLabel("Current:"), gbc);

    gbc.gridx = 1;
    panel.add(currentTempLabel, gbc);

    gbc.gridx = 2;
    panel.add(new JLabel(DEGREE_SYMBOL), gbc);

    gbc.gridx = 0; gbc.gridy = 1;
    panel.add(new JLabel("Target:"), gbc);

    gbc.gridx = 1;
    panel.add(targetTempLabel, gbc);

    gbc.gridx = 2;
    panel.add(new JLabel(DEGREE_SYMBOL), gbc);


    return panel;
  }

  /**
   * Creates the temperature control panel.
   * This panel contains system status, input field and button for setting target temperature
   * @return JPanel configured for temperature control
   */
  private JPanel createControlPanel() {
    JPanel panel = new JPanel();
    panel.setBorder(new TitledBorder("Temperature Control"));

    return panel;
  }

  /**
   * Creates the message panel for displaying feedback to the user.
   * Messages can be informational or error messages.
   * @return JPanel configured for message display
   */
  private JPanel createMessagePanel() {
    JPanel panel = new JPanel();

    return panel;
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
