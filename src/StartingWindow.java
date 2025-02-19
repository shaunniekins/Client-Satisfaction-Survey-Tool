import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class StartingWindow extends JFrame {

  JButton btnTake;
  JPanel panel, pnlTitleBar;

  public StartingWindow() {
    getContentPane().setLayout(
        new MigLayout("center", "[]", "[][]")); // insets 200 50 50 50
    // this.setExtendedState(JFrame.MAXIMIZED_BOTH);

    // ImageIcon icBg = new ImageIcon(".//pics//picture bg.jpg");
    ImageIcon icBg = new ImageIcon(
        getClass().getClassLoader().getResource("picture bg.jpg"));
    Image imgBg = icBg.getImage();
    Image newimgBg = imgBg.getScaledInstance(
        Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, Image.SCALE_SMOOTH);
    icBg = new ImageIcon(newimgBg);

    panel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(newimgBg, 0, 0, null);
      }
    };
    panel.setPreferredSize(
        new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));

    panel.setLayout(new MigLayout("center"));

    pnlTitleBar = new JPanel();
    pnlTitleBar.setLayout(new MigLayout("filly"));
    pnlTitleBar.setPreferredSize(
        new Dimension(Constants.WINDOW_WIDTH, Constants.FONT_SIZE_SMALL));
    pnlTitleBar.setBackground(Constants.BLUE_JEANS);

    JLabel lblTitleBarIcon = new JLabel("Client Satisfaction Survey");
    ImageIcon icTitleBarIcon = new ImageIcon("icons/survey.png");
    Image imgTitleBarIcon = icTitleBarIcon.getImage();
    Image newimgTitleBarIcon =
        imgTitleBarIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    icTitleBarIcon = new ImageIcon(newimgTitleBarIcon);
    lblTitleBarIcon.setIcon(icTitleBarIcon);

    JLabel lblX = new JLabel("X");
    lblX.setForeground(Color.WHITE);
    lblX.setFont(new Font("Arial Black", Font.BOLD, Constants.FONT_SIZE_SMALL));

    JLabel lblMin = new JLabel("--");
    lblMin.setForeground(Color.WHITE);
    lblMin.setFont(new Font("Arial Black", Font.BOLD, 18));

    JLabel lblSN = new JLabel("BAYUGAN CITY SENIOR HIGH SCHOOL (STAND-ALONE)");
    lblSN.setForeground(Color.WHITE);
    lblSN.setFont(new Font(Constants.FONT_FAMILY, Font.PLAIN, 25));

    JLabel lblTitle = new JLabel("CLIENT SATISFACTION SURVEY");
    lblTitle.setForeground(Color.WHITE);
    lblTitle.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, 50));

    JLabel lblTL = new JLabel("Your Experience Matters to Us!");
    lblTL.setForeground(Color.WHITE);
    lblTL.setFont(new Font("Arial", Font.ITALIC, Constants.FONT_SIZE_LARGE));

    JButton btnView = new JButton("VIEW RATINGS (ADMIN)");
    btnView.setFont(new Font("Arial", Font.PLAIN, Constants.FONT_SIZE_SMALL));
    btnView.setForeground(Color.WHITE);
    btnView.setBackground(Color.BLUE.brighter());
    btnView.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ConfirmationWindow cw = new ConfirmationWindow();
        cw.setVisible(true);
        setVisible(false);
      }
    });

    btnTake = new JButton("TAKE A SURVEY");
    btnTake.setFont(new Font("Arial", Font.PLAIN, Constants.FONT_SIZE_SMALL));
    btnTake.setForeground(Color.WHITE);
    btnTake.setBackground(Color.BLUE.brighter());
    btnTake.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        UserInfoWindow uiw = new UserInfoWindow();
        uiw.setVisible(true);
        setVisible(false);
      }
    });
    JLabel lblExt = new JLabel("EXIT");
    lblExt.setFont(
        new Font("Arial", Font.CENTER_BASELINE, Constants.FONT_SIZE_SMALL));
    lblExt.setForeground(Color.WHITE);
    lblExt.addMouseListener(new MouseListener() {
      @Override
      public void mouseReleased(MouseEvent e) {}

      @Override
      public void mousePressed(MouseEvent e) {}

      @Override
      public void mouseExited(MouseEvent e) {}

      @Override
      public void mouseEntered(MouseEvent e) {}

      @Override
      public void mouseClicked(MouseEvent e) {
        System.exit(0);
      }
    });
    ImageIcon ic =
        new ImageIcon(getClass().getClassLoader().getResource("survey.png"));
    Image img = ic.getImage();

    this.setIconImage(img);
    this.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    this.setLocationRelativeTo(null);
    this.getContentPane().setBackground(Constants.BLUE_JEANS);
    this.getRootPane().setBorder(
        BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK.brighter()));
    this.setUndecorated(true);
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // add(pnlTitleBar, "wrap");
    getContentPane().add(panel, "cell 0 0 1 2");

    /*pnlTitleBar.add(lblTitleBarIcon);
    pnlTitleBar.add(lblMin, "gapx 843");
    pnlTitleBar.add(lblX, "gapx 26");*/

    panel.add(lblSN, "cell 0 0, center, gapy 200");
    panel.add(lblTitle, " cell 0 1, center, gapy 50");
    panel.add(lblTL, "cell 0 2, center, gapy 20");
    panel.add(btnView, "cell 0 3, center, gapy 50, width 200px, height 50px");
    panel.add(btnTake, "cell 0 3, gapx 50, height 50px, width 200px");
    panel.add(lblExt, "cell 0 4, center, gapy 40, bottom");
  }
}
