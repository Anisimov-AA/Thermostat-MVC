package main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import main.view.utils.StyleConfig;
import main.view.utils.StyleConfig.Colors;
import main.view.utils.StyleConfig.Fonts;

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
  private JLabel heatingIndicator;
  private JLabel coolingIndicator;

  // Controll components
  private JLabel statusLabel;
  private JTextField tempInputField; // input components
  private JButton setTempButton;

  // Feedback components
  private JLabel messageLabel;

  // Message display duration
  private static final int MESSAGE_TIMEOUT_MS = 3000;
  private Timer messageTimer; // Store timer reference to cancel if needed

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


  /**
   * Creates and initializes all UI components
   * Called once during construction
   */
  private void createComponents() {
    // display components
    this.currentTempLabel = createDisplayLabel("--.-", StyleConfig.Fonts.TEMP_CURRENT_FONT);
    this.targetTempLabel = createDisplayLabel("--.-", StyleConfig.Fonts.TEMP_TARGET_FONT);
    this.heatingIndicator = createStatusLabel("HEAT", Fonts.INDICATOR_FONT, StyleConfig.Colors.INDICATOR_INACTIVE_COLOR, StyleConfig.Colors.INDICATOR_INACTIVE_TEXT);
    this.coolingIndicator = createStatusLabel("COOL", Fonts.INDICATOR_FONT, StyleConfig.Colors.INDICATOR_INACTIVE_COLOR, StyleConfig.Colors.INDICATOR_INACTIVE_TEXT);

    // control components
    this.statusLabel = createDisplayLabel("Idle", Fonts.STATUS_FONT);
    this.tempInputField = createTextField(5, Fonts.INPUT_FONT, "Enter temperature in Celsius");
    this.setTempButton = createButton("Set Temperature", Fonts.BUTTON_FONT,"Click to set the target temperature");

    // feedback components
    this.messageLabel = createMessageLabel(Fonts.MESSAGE_FONT, Colors.MESSAGE_INFO_COLOR);
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
   * Creates a status label with specified text and color
   * @param text the indicator text (e.g., "HEAT", "COOL")
   * @param font the font to use
   * @param bgColor the background color
   * @param textColor the foreground color
   * @return configured JLabel
   */
  private JLabel createStatusLabel(String text, Font font, Color bgColor, Color textColor) {
    JLabel label = new JLabel(text);
    label.setOpaque(true);
    label.setBackground(bgColor);
    label.setForeground(textColor);
    label.setFont(font);
    label.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.GRAY),
        new EmptyBorder(2, 5, 2, 5)
    ));

    return label;
  }

  /**
   * Creates a styled text field
   * @param columns the number of columns for the text field
   * @param font the font to use
   * @param tip the tip text
   * @return a configured JTextField
   */
  private JTextField createTextField(int columns, Font font, String tip) {
    JTextField field = new JTextField(columns);
    field.setFont(font);
    field.setToolTipText(tip);

    return field;
  }

  /**
   * Creates a styled button
   * @param text the button text
   * @param font the font to use
   * @param tip the tip text
   * @return a configured JButton
   */
  private JButton createButton(String text, Font font, String tip) {
    JButton button = new JButton(text);
    button.setFont(font);
    button.setToolTipText(tip);

    return button;
  }

  /**
   * Creates a styled message label
   * @param font the font to use
   * @param textColor
   * @return a configured JLabel for messages
   */
  private JLabel createMessageLabel(Font font, Color textColor) {
    JLabel label = new JLabel(" "); // space to maintain consistent height
    label.setFont(font);
    label.setForeground(textColor);

    return label;
  }


  /**
   * Arranges all UI components in the main frame
   * Uses BorderLayout with three main sections: display, control, and messages
   */
  private void layoutComponents() {
    JPanel mainPanel = new JPanel(new BorderLayout(PADDING, PADDING));
    mainPanel.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));

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

    // current temperature row
    gbc.gridx = 0; gbc.gridy = 0;
    panel.add(new JLabel("Current:"), gbc);

    gbc.gridx = 1;
    panel.add(currentTempLabel, gbc);

    gbc.gridx = 2;
    panel.add(new JLabel(DEGREE_SYMBOL), gbc);

    // target temperature row
    gbc.gridx = 0; gbc.gridy = 1;
    panel.add(new JLabel("Target:"), gbc);

    gbc.gridx = 1;
    panel.add(targetTempLabel, gbc);

    gbc.gridx = 2;
    panel.add(new JLabel(DEGREE_SYMBOL), gbc);

    // status indicators
    gbc.gridx = 3; gbc.gridy = 0;
    gbc.gridheight = 2; // span both rows
    gbc.insets = new Insets(5, 20, 5, 5);  // extra left padding
    panel.add(createStatusIndicatorPanel(), gbc);

    return panel;
  }

  /**
   * Creates the panel containing heating and cooling indicators.
   * @return JPanel with both status indicators
   */
  private JPanel createStatusIndicatorPanel() {
    JPanel panel = new JPanel(new FlowLayout());
    panel.add(heatingIndicator);
    panel.add(coolingIndicator);
    return panel;
  }

  /**
   * Creates the temperature control panel.
   * This panel contains system status, input field and button for setting target temperature
   * @return JPanel configured for temperature control
   */
  private JPanel createControlPanel() {
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBorder(new TitledBorder("Temperature Control"));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);

    // status label
    gbc.gridx = 0; gbc.gridy = 0;
    gbc.gridwidth = 3;
    panel.add(statusLabel, gbc);

    // input controls row
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    panel.add(new JLabel("Set Temperature:"), gbc);

    gbc.gridx = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    panel.add(tempInputField, gbc);

    gbc.gridx = 2;
    gbc.fill = GridBagConstraints.NONE;
    panel.add(setTempButton, gbc);

    return panel;
  }

  /**
   * Creates the message panel for displaying feedback to the user.
   * Messages can be informational or error messages.
   * @return JPanel configured for message display
   */
  private JPanel createMessagePanel() {
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panel.add(messageLabel);

    return panel;
  }


  @Override
  public void addListener(ActionListener listener) {
    setTempButton.addActionListener(listener);
  }

  @Override
  public String getInput() {
    return this.tempInputField.getText().trim();
  }

  @Override
  public void clearInput() {
    this.tempInputField.setText("");
    this.tempInputField.requestFocus();
  }

  @Override
  public void updateDisplay(double currentTemp, double targetTemp, boolean isHeating,
      boolean isCooling) {

    updateTemperature(currentTemp, targetTemp);

    updateStatus(isHeating, isCooling);

    updateIndicator(heatingIndicator, isHeating, Colors.INDICATOR_ACTIVE_COLOR_HEATING);
    updateIndicator(coolingIndicator, isCooling, Colors.INDICATOR_ACTIVE_COLOR_COOLING);
  }

  /**
   * Update temperature displays
   * @param currentTemp the current temperature reading in degrees Celsius
   * @param targetTemp the target temperature setting in degrees Celsius
   */
  private void updateTemperature(double currentTemp, double targetTemp) {
    this.currentTempLabel.setText(String.format(Locale.US, "%.1f", currentTemp));
    this.targetTempLabel.setText(String.format(Locale.US, "%.1f", targetTemp));
  }

  /**
   * // Update status text
   * @param isHeating true if the heating system is currently active, false otherwise
   * @param isCooling true if the cooling system is currently active, false otherwise
   */
  private void updateStatus(boolean isHeating, boolean isCooling) {
    String status;
    if(isHeating) {
      status = "Heating";
    } else if (isCooling) {
      status = "Cooling";
    } else {
      status = "Idle";
    }
    statusLabel.setText("Status: " + status);
  }

  /**
   * Update indicators
   * @param indicator the indicator to update
   * @param active true if indicator should show active state
   * @param activeColor the color to use when active
   */
  private void updateIndicator(JLabel indicator, boolean active, Color activeColor) {
    if(active) {
      indicator.setBackground(activeColor);
      indicator.setForeground(Colors.INDICATOR_ACTIVE_TEXT);
    } else {
      indicator.setBackground(Colors.INDICATOR_INACTIVE_COLOR);
      indicator.setForeground(Colors.INDICATOR_INACTIVE_TEXT);
    }
  }

  @Override
  public void showMessage(String message, boolean isError) {
    // cancel any existing timer
    if (messageTimer != null && messageTimer.isRunning()) {
      messageTimer.stop();
    }

    messageLabel.setText(message);
    messageLabel.setForeground(isError ? Colors.MESSAGE_ERROR_COLOR : Colors.MESSAGE_INFO_COLOR);

    // clear message after 3 seconds
    messageTimer = new Timer(MESSAGE_TIMEOUT_MS, e -> messageLabel.setText(" "));
    messageTimer.setRepeats(false);
    messageTimer.start();
  }

//  public static void main(String[] args) {
//    ThermostatView view = new ThermostatView();
//    view.setVisible(true);
//
//    // Demo data for testing
//    view.updateDisplay(21.9, 21, false, true);
//    view.showMessage("View initialized successfully", false);
//  }
}
