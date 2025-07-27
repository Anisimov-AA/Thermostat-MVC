package view.utils;

import java.awt.Color;
import java.awt.Font;

public class StyleConfig {

  /** Font Configurations */
  public static final class Fonts {
    private static final String FAMILY = Font.SANS_SERIF;

    public static final Font TEMP_CURRENT_FONT = new Font(FAMILY, Font.BOLD, 24);
    public static final Font TEMP_TARGET_FONT = new Font(FAMILY, Font.PLAIN, 20);
    public static final Font INDICATOR_FONT = new Font(FAMILY, Font.PLAIN, 10);

    public static final Font STATUS_FONT = new Font(FAMILY, Font.PLAIN, 16);
    public static final Font INPUT_FONT = new Font(FAMILY, Font.PLAIN, 14);
    public static final Font BUTTON_FONT = new Font(FAMILY, Font.PLAIN, 12);

    public static final Font MESSAGE_FONT = new Font(FAMILY, Font.ITALIC, 12);

    private Fonts() {} // prevent instantiation
  }

  /** Color Configurations */
  public static final class Colors {

    public static final Color INDICATOR_INACTIVE_COLOR = Color.LIGHT_GRAY;
    public static final Color INDICATOR_INACTIVE_TEXT = Color.DARK_GRAY;
    public static final Color INDICATOR_ACTIVE_COLOR_HEATING = Color.RED;
    public static final Color INDICATOR_ACTIVE_COLOR_COOLING = Color.BLUE;
    public static final Color INDICATOR_ACTIVE_TEXT = Color.WHITE;

    public static final Color MESSAGE_INFO_COLOR = Color.BLUE;
    public static final Color MESSAGE_ERROR_COLOR = Color.RED;

    private Colors() {}
  }

}
