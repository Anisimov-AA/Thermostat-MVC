package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import view.utils.StyleConfig;
import view.utils.StyleConfig.Fonts;

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
    this.heatingIndicator = createStatusLabel("HEAT", Fonts.INDICATOR_FONT, StyleConfig.Colors.INDICATOR_INACTIVE_COLOR, StyleConfig.Colors.INDICATOR_INACTIVE_TEXT);
    this.coolingIndicator = createStatusLabel("COOL", Fonts.INDICATOR_FONT, StyleConfig.Colors.INDICATOR_INACTIVE_COLOR, StyleConfig.Colors.INDICATOR_INACTIVE_TEXT);

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
