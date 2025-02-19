package com.surveytool;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main entry point for the Client Satisfaction Survey Tool application.
 */
public class MainWindow {
  private static final Logger logger =
      LoggerFactory.getLogger(MainWindow.class);
  private static final String LOOK_AND_FEEL =
      "javax.swing.plaf.nimbus.NimbusLookAndFeel";

  /**
   * Application entry point.
   * @param args Command line arguments (not used)
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      try {
        UIManager.setLookAndFeel(LOOK_AND_FEEL);
        new StartingWindow().setVisible(true);
      } catch (ClassNotFoundException | InstantiationException |
               IllegalAccessException | UnsupportedLookAndFeelException e) {
        logger.error("Failed to set look and feel", e);
        // Fall back to default look and feel
        new StartingWindow().setVisible(true);
      }
    });
  }
}
