import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;

public class ConfirmationWindow extends JFrame {
  public ConfirmationWindow() {
    setLayout(new MigLayout());

    JPanel panel = new JPanel(new MigLayout("center, insets 100 100 100 100"));
    panel.setPreferredSize(
        new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
    panel.setBackground(Constants.BLUE_JEANS);

    JLabel lblLogo = new JLabel();
    ImageIcon icLogo = new ImageIcon(
        getClass().getClassLoader().getResource("bcshs-logo.png"));
    Image imgLogo = icLogo.getImage();
    Image newimgLogo = imgLogo.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    icLogo = new ImageIcon(newimgLogo);
    lblLogo.setIcon(icLogo);

    Font f1 =
        new Font(Constants.FONT_FAMILY, Font.PLAIN, Constants.FONT_SIZE_SMALL);
    Font f2 =
        new Font(Constants.FONT_FAMILY, Font.PLAIN, Constants.FONT_SIZE_LARGE);

    JLabel lblHeader = new JLabel("Admin Confirmation");
    lblHeader.setForeground(Color.BLACK);
    lblHeader.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, 23));

    JLabel lblName = new JLabel("Username");
    lblName.setForeground(Color.BLACK);
    lblName.setFont(f1);

    JLabel lblPass = new JLabel("Password");
    lblPass.setForeground(Color.BLACK);
    lblPass.setFont(f1);

    JTextField txtName = new JTextField();
    txtName.setFont(f2);

    JPasswordField pfPass = new JPasswordField();
    pfPass.setFont(f2);

    JCheckBox cbShow = new JCheckBox("Show Password");
    cbShow.setHorizontalTextPosition(JCheckBox.LEFT);
    cbShow.setFont(new Font(Constants.FONT_FAMILY, Font.PLAIN, 12));
    cbShow.setForeground(Color.DARK_GRAY.darker());
    cbShow.setBackground(Constants.BLUE_JEANS);
    cbShow.addActionListener(ae -> {
      JCheckBox c = (JCheckBox)ae.getSource();
      if (c.isSelected()) {
        pfPass.setEchoChar((char)0);
      } else {
        pfPass.setEchoChar('*');
      }
    });

    JLabel lblCancel = new JLabel("Cancel");
    lblCancel.setForeground(Color.DARK_GRAY);
    lblCancel.setFont(new Font(Constants.FONT_FAMILY, Font.PLAIN, 12));
    lblCancel.addMouseListener(new MouseListener() {
      @Override
      public void mouseReleased(MouseEvent e) {}

      @Override
      public void mousePressed(MouseEvent e) {}

      @Override
      public void mouseExited(MouseEvent e) {}

      @Override
      public void mouseEntered(MouseEvent e) {}

      @Override
      public void mouseClicked(MouseEvent arg0) {
        StartingWindow sw = new StartingWindow();
        sw.setVisible(true);
        setVisible(false);
      }
    });

    JButton btnConfirm = new JButton("Confirm");
    btnConfirm.setForeground(Color.WHITE);
    btnConfirm.setBackground(Color.BLUE.brighter());
    btnConfirm.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        checkUser(txtName.getText(), pfPass.getText());
      }
    });

    ImageIcon ic =
        new ImageIcon(getClass().getClassLoader().getResource("survey.png"));
    Image img = ic.getImage();

    this.setIconImage(img);
    this.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    this.setLocationRelativeTo(null);
    this.getRootPane().setBorder(
        BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK.brighter()));
    this.getContentPane().setBackground(Color.WHITE);
    this.setUndecorated(true);
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    add(panel);

    panel.add(lblLogo, "cell 0 0, center");
    panel.add(lblHeader, "cell 0 1, center, gapy 20");
    panel.add(lblName, "cell 0 2, gapy 20");
    panel.add(txtName, "cell 0 3, center,  height 30px, width 280px");
    panel.add(lblPass, "cell 0 4");
    panel.add(pfPass, "cell 0 5, center, height 30px, width 280px");
    panel.add(cbShow, "cell 0 6, right");
    panel.add(btnConfirm,
              "cell 0 7, center, gapy 15, width 280px, height 35px");
    panel.add(lblCancel, "cell 0 8, center, gapy 20");
  }
  public void checkUser(String username, String password) {
    String query = "SELECT * FROM confirm WHERE username = ? AND password = ?";
    ConnectionWindow db = new ConnectionWindow();

    try {
      db.ps = db.conn.prepareStatement(query);
      db.ps.setString(1, username);
      db.ps.setString(2, password);
      db.rs = db.ps.executeQuery();

      if (db.rs.next()) {
        RatingsWindow rw = new RatingsWindow();
        rw.setVisible(true);
        setVisible(false);

      } else {
        JOptionPane.showMessageDialog(this, "Incorrect Username/Password");
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
