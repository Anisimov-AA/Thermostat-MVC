package view.utils;

import java.awt.Font;

public class StyleConfig {

  /** Font Configurations */
  public static final class Fonts {
    private static final String FAMILY = Font.SANS_SERIF;

    public static final Font TEMP_CURRENT_FONT = new Font(FAMILY, Font.BOLD, 24);
    public static final Font TEMP_TARGET_FONT = new Font(FAMILY, Font.PLAIN, 20);

    private Fonts() {} // prevent instantiation
  }

}
