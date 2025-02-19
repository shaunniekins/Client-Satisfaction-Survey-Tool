import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class PartingMsgWindow extends JFrame {

  int id;
  JPanel panel;
  ConnectionWindow db = new ConnectionWindow();

  public PartingMsgWindow() {
    setLayout(new MigLayout());

    ImageIcon icBg =
        new ImageIcon(getClass().getClassLoader().getResource("pic.jpg"));
    Image imgBg = icBg.getImage();
    Image newimgBg = imgBg.getScaledInstance(
        Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, Image.SCALE_DEFAULT);
    icBg = new ImageIcon(newimgBg);

    panel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(newimgBg, 0, 0, this);
      }
    };
    panel.setPreferredSize(
        new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
    panel.setLayout(new MigLayout("center"));

    getLastId();

    JLabel lblSN = new JLabel("BAYUGAN CITY SENIOR HIGH SCHOOL (STAND-ALONE)");
    lblSN.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, 35));
    lblSN.setForeground(Color.WHITE);

    JLabel lblMsg = new JLabel("Thank you for taking the survey!");
    lblMsg.setFont(
        new Font(Constants.FONT_FAMILY, Font.PLAIN, Constants.FONT_SIZE_LARGE));
    lblMsg.setForeground(Color.WHITE);

    Font f =
        new Font(Constants.FONT_FAMILY, Font.PLAIN, Constants.FONT_SIZE_SMALL);

    JLabel lblContact = new JLabel("Contact Us");
    lblContact.setFont(new Font("Arial", Font.BOLD, Constants.FONT_SIZE_LARGE));
    lblContact.setForeground(Color.white);

    JLabel lblEmail = new JLabel("   340297@deped.gov.ph");
    ImageIcon icEmail =
        new ImageIcon(getClass().getClassLoader().getResource("gmail.png"));
    java.awt.Image imgEmail = icEmail.getImage();
    java.awt.Image newimgEmail =
        imgEmail.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    icEmail = new ImageIcon(newimgEmail);
    lblEmail.setIcon(icEmail);
    lblEmail.setFont(f);
    lblEmail.setForeground(Color.WHITE);

    JLabel lblFbPage = new JLabel("   @bayugancityshsofficial");
    ImageIcon icFbPage =
        new ImageIcon(getClass().getClassLoader().getResource("facebook.png"));
    java.awt.Image imgFbPage = icFbPage.getImage();
    java.awt.Image newimgFbPage =
        imgFbPage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    icFbPage = new ImageIcon(newimgFbPage);
    lblFbPage.setIcon(icFbPage);
    lblFbPage.setFont(f);
    lblFbPage.setForeground(Color.WHITE);

    JLabel lblCall = new JLabel("   0231-1858");
    ImageIcon icCall =
        new ImageIcon(getClass().getClassLoader().getResource("phone.png"));
    Image imgCall = icCall.getImage();
    Image newimgCall = imgCall.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    icCall = new ImageIcon(newimgCall);
    lblCall.setIcon(icCall);
    lblCall.setFont(f);
    lblCall.setForeground(Color.WHITE);

    JButton btnMenu = new JButton();
    ImageIcon icMenu =
        new ImageIcon(getClass().getClassLoader().getResource("home.png"));
    Image imgMenu = icMenu.getImage();
    Image newimgMenu = imgMenu.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    icMenu = new ImageIcon(newimgMenu);
    btnMenu.setIcon(icMenu);
    btnMenu.setBackground(Color.BLUE);
    btnMenu.setBorderPainted(false);
    btnMenu.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        StartingWindow sw = new StartingWindow();
        sw.setVisible(true);
        setVisible(false);
      }
    });

    JButton btnExt = new JButton();
    ImageIcon icExt =
        new ImageIcon(getClass().getClassLoader().getResource("logout.png"));
    Image imgExt = icExt.getImage();
    Image newimgExt = imgExt.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    icExt = new ImageIcon(newimgExt);
    btnExt.setIcon(icExt);
    btnExt.setBackground(Color.BLUE);
    btnExt.setBorderPainted(false);
    btnExt.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        dispose();
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

    add(panel);

    panel.add(lblSN, "cell 0 0, center, gapy 150");
    panel.add(lblMsg, "cell 0 1, center");
    panel.add(lblContact, "cell 0 2, left, gapy 150");
    panel.add(lblEmail, "cell 0 3, left, gapy 15");
    panel.add(lblFbPage, "cell 0 4, left, gapy 10");
    panel.add(lblCall, "cell 0 5, left, gapy 10");
    panel.add(btnMenu, "cell 0 6, right, gapy 30, width 110px");
    panel.add(btnExt, "cell 0 7, right, width 110px");
  }
  public void submit(String name, Object office, String start, Date date,
                     String end, String purpose) {
    info(name, office, start, (java.sql.Date)date, end, purpose);
  }
  public void react1(String react1) { reaction1(react1, id); }
  public void react2(String react2) { reaction2(react2, id); }
  public void react3(String react3) { reaction3(react3, id); }
  public void comment(String comment) { comments(comment, id); }
  public void getLastId() {
    String query = "SELECT MAX(id) from survey_table";

    try {
      db.statement = db.conn.createStatement();
      db.rs = db.statement.executeQuery(query);

      if (db.rs.next()) {
        id = db.rs.getInt(1);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  public void info(String name, Object office, String time_started, Date date,
                   String time_ended, String purpose_visit) {
    String query = "INSERT INTO survey_table(name, office_visited, "
                   +
                   "time_started, date_of_visit, time_ended, purpose_of_visit) "
                   + "VALUES(?,?,?,?,?,?)";

    try {
      db.ps = db.conn.prepareStatement(query);
      db.ps.setString(1, name);
      db.ps.setString(2, (String)office);
      db.ps.setString(3, time_started);
      db.ps.setDate(4, (java.sql.Date)date);
      db.ps.setString(5, time_ended);
      db.ps.setString(6, purpose_visit);

      db.ps.execute();

    } catch (Exception e) {
      System.out.println(e);
    }
  }
  public void reaction1(String react1, int id) {
    String query = "UPDATE survey_table SET q1 = ? WHERE id = ?";

    try {
      db.ps = db.conn.prepareStatement(query);
      db.ps.setString(1, react1);
      db.ps.setInt(2, id);

      db.ps.execute();

    } catch (Exception e) {
      System.out.println(e);
    }
  }
  public void reaction2(String react2, int id) {
    String query = "UPDATE survey_table SET q2 = ? WHERE id = ?";

    try {
      db.ps = db.conn.prepareStatement(query);
      db.ps.setString(1, react2);
      db.ps.setInt(2, id);

      db.ps.execute();

    } catch (Exception e) {
      System.out.println(e);
    }
  }
  public void reaction3(String react3, int id) {
    String query = "UPDATE survey_table SET q3 = ? WHERE id = ?";

    try {
      db.ps = db.conn.prepareStatement(query);
      db.ps.setString(1, react3);
      db.ps.setInt(2, id);

      db.ps.execute();

    } catch (Exception e) {
      System.out.println(e);
    }
  }
  public void comments(String comment, int id) {
    String query = "UPDATE survey_table SET comments = ? WHERE id = ?";

    try {
      db.ps = db.conn.prepareStatement(query);
      db.ps.setString(1, comment);
      db.ps.setInt(2, id);

      db.ps.execute();

    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
