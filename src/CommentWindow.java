import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import net.miginfocom.swing.MigLayout;

public class CommentWindow extends JFrame {

  JPanel pnlL;
  JPanel pnlR;
  JTextArea txaCom;
  int id;

  public CommentWindow() {
    setLayout(new MigLayout("center"));

    ImageIcon icBg = new ImageIcon(
        getClass().getClassLoader().getResource("picture background1.jpg"));
    Image imgBg = icBg.getImage();
    Image newimgBg = imgBg.getScaledInstance(400, Constants.WINDOW_HEIGHT,
                                             Image.SCALE_SMOOTH);
    icBg = new ImageIcon(newimgBg);

    pnlL = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(newimgBg, 0, 0, null);
      }
    };
    pnlL.setPreferredSize(new Dimension(400, Constants.WINDOW_HEIGHT));
    pnlL.setLayout(new MigLayout("center"));

    JLabel lblLogo = new JLabel();
    ImageIcon icLogo = new ImageIcon(
        getClass().getClassLoader().getResource("bcshs-logo.png"));
    java.awt.Image imgLogo = icLogo.getImage();
    java.awt.Image newimgLogo =
        imgLogo.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    icLogo = new ImageIcon(newimgLogo);
    lblLogo.setIcon(icLogo);

    JLabel lblSN =
        new JLabel("<html>Bayugan City Senior High "
                   + "School</br><center>(Stand-Alone)</center></html>");
    lblSN.setForeground(Color.WHITE);
    lblSN.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, 18));

    JLabel lblSy = new JLabel("S.Y. 2020 - 2021");
    lblSy.setForeground(Color.WHITE);
    lblSy.setFont(new Font(Constants.FONT_FAMILY, Font.ITALIC, 14));

    pnlR = new JPanel();
    pnlR.setLayout(new MigLayout("center, fill, insets 50 50 50 50"));
    pnlR.setBackground(Color.WHITE);
    pnlR.setPreferredSize(new Dimension(800, Constants.WINDOW_HEIGHT));
    pnlR.setVisible(true);

    JLabel lblHead = new JLabel("Compliments/Comments/Suggestions:	");
    lblHead.setFont(
        new Font(Constants.FONT_FAMILY, Font.BOLD, Constants.FONT_SIZE_LARGE));

    txaCom = new JTextArea();
    txaCom.setFont(
        new Font(Constants.FONT_FAMILY, Font.PLAIN, Constants.FONT_SIZE_LARGE));

    JButton btnSub = new JButton("Submit");
    btnSub.setFont(new Font("Arial", Font.PLAIN, Constants.FONT_SIZE_LARGE));
    btnSub.setForeground(Color.white);
    btnSub.setBackground(Constants.BLUE_JEANS);
    btnSub.setBorderPainted(false);
    btnSub.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int result = JOptionPane.showConfirmDialog(null, "Submit?", "Confirm",
                                                   JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {

          passValue();
          PartingMsgWindow pmw = new PartingMsgWindow();
          pmw.setVisible(true);
          setVisible(false);
        }
      }
    });

    JLabel lblMenu = new JLabel("MENU");
    lblMenu.setForeground(Color.white);
    lblMenu.setFont(new Font(Constants.FONT_FAMILY, Font.PLAIN, 13));
    lblMenu.addMouseListener(new MouseListener() {
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
        int result = JOptionPane.showConfirmDialog(
            null, "You will lose your input data. Continue?", "Confirm",
            JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
          StartingWindow sw = new StartingWindow();
          sw.setVisible(true);
          setVisible(false);
          getLastId();
          delDate();
        }
      }
    });

    ImageIcon ic = new ImageIcon("icons/survey.png");
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

    add(pnlL, "cell 0 0");
    add(pnlR, "cell 1 0");

    pnlL.add(lblLogo, "wrap, center, gapy 200");
    pnlL.add(lblSN, "wrap, center");
    pnlL.add(lblSy, "wrap, center, gapy 5");
    pnlL.add(lblMenu, "center, gapy 245");

    pnlR.add(lblHead, "cell 0 0, left");
    pnlR.add(new JScrollPane(txaCom), "cell 0 1, gapy 50, push, grow");
    pnlR.add(btnSub, "cell 0 2, gapy 50, right");
  }
  public void passValue() {
    PartingMsgWindow pmw = new PartingMsgWindow();
    pmw.comment(txaCom.getText());
  }
  public void getLastId() {
    String query = "SELECT MAX(id) from survey_table";
    ConnectionWindow db = new ConnectionWindow();

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
  public void delDate() {
    String query = "DELETE FROM survey_table WHERE id = ? ";
    ConnectionWindow db = new ConnectionWindow();

    try {
      db.ps = db.conn.prepareStatement(query);
      db.ps.setInt(1, id);

      db.ps.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
