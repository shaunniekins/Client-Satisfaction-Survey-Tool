import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
  public MainFrame() {
    setTitle("Client Satisfaction Survey");
    setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Example of using font constants
    Font largeFont =
        new Font(Constants.FONT_FAMILY, Font.BOLD, Constants.FONT_SIZE_LARGE);
    Font mediumFont =
        new Font(Constants.FONT_FAMILY, Font.PLAIN, Constants.FONT_SIZE_MEDIUM);

    // Example of using color constants
    JPanel mainPanel = new JPanel();
    mainPanel.setBackground(Constants.BLUE_JEANS);

    // Example of using reaction type constants
    String[] reactions = {Constants.VERY_SATISFIED, Constants.SATISFIED,
                          Constants.NEUTRAL, Constants.DISSATISFIED,
                          Constants.VERY_DISSATISFIED};

    JComboBox<String> officeComboBox = new JComboBox<>(
        new String[] {Constants.GPTA_OFFICE, Constants.GUIDANCE_OFFICE,
                      Constants.PRINCIPALS_OFFICE, Constants.REGISTRARS_OFFICE,
                      Constants.OTHERS_OFFICE});
  }
}
