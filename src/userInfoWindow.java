import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import net.miginfocom.swing.MigLayout;

public class userInfoWindow extends JFrame{
	
	JPanel pnlL;
	JPanel pnlR;
	JFormattedTextField tfxtTs, tfxtTe;
	JTextField txtName,txtPp;
	JComboBox cmbOff;
	JDateChooser jcDov;
	JButton btnNxt;
	
	public userInfoWindow() {
		getContentPane().setLayout(new MigLayout("center", "[][]", "[]"));

		Color blueJeans = new Color(75, 126, 191);
		
		ImageIcon icBg = new ImageIcon(getClass().getClassLoader().getResource("picture background1.jpg")); 
		Image imgBg = icBg.getImage();
		Image newimgBg = imgBg.getScaledInstance(400, 700 , Image.SCALE_SMOOTH);
		icBg = new ImageIcon(newimgBg);
		
		pnlL = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g); 
				g.drawImage(newimgBg, 0, 0, null);
			}};
		pnlL.setPreferredSize(new Dimension(400, 700));
		pnlL.setLayout(new MigLayout("center"));
		
		JLabel lblLogo = new JLabel();
		ImageIcon icLogo = new ImageIcon(getClass().getClassLoader().getResource("bcshs-logo.png")); 
		java.awt.Image imgLogo = icLogo.getImage();
		java.awt.Image newimgLogo = imgLogo.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		icLogo = new ImageIcon(newimgLogo);
		lblLogo.setIcon(icLogo);
		
		JLabel lblSN = new JLabel("<html>Bayugan City Senior High School</br><center>(Stand-Alone)</center></html>");
		lblSN.setForeground(Color.WHITE);
		lblSN.setFont(new Font("Open Sans Condensed", Font.BOLD, 18));
		
		JLabel lblSy = new JLabel("S.Y. 2020 - 2021"); 
		lblSy.setForeground(Color.WHITE);
		lblSy.setFont(new Font("Open Sans Condensed", Font.ITALIC, 14));
		
		pnlR = new JPanel();
		pnlR.setLayout(new MigLayout("center, fill, insets 50 50 50 50"));
		pnlR.setBackground(Color.WHITE);
		pnlR.setPreferredSize(new Dimension(800, 700));
		
		Font f = new Font("Arial", Font.PLAIN, 15);
		Font f1 = new Font("Arial", Font.PLAIN, 20);
		
		JLabel lblHead = new JLabel("Please fill with your details");
		lblHead.setFont(f1);
		
		JLabel lblName = new JLabel("Name (optional): ");
		lblName.setFont(f);
		
		JLabel lblOV = new JLabel("Office Visited: ");
		lblOV.setFont(f);
		
		JLabel lblDoV = new JLabel("Date of Visit: ");
		lblDoV.setFont(f);
		
		JLabel lblTs = new JLabel("Time Started: ");
		lblTs.setFont(f);
		
		JLabel lblTe = new JLabel("Time Ended: ");
		lblTe.setFont(f);
		
		JLabel lblPp = new JLabel("Purpose of Visit (please specify)");
		lblPp.setFont(f);
		
		txtName = new JTextField();
		txtName.setFont(f1);
		
		String offices[] = {"GPTA Office", "Guidance Office", "Principal's Office", "Registrar's Office", "Others"};
		cmbOff = new JComboBox(offices);
		cmbOff.setSelectedIndex(-1);
		cmbOff.setBackground(java.awt.Color.WHITE);
		cmbOff.setFont(f1);

		jcDov = new JDateChooser();
		jcDov.setDateFormatString("yyyy-MM-dd");
		jcDov.setFont(f1);
		
		MaskFormatter mask = null;
        try {
            mask = new MaskFormatter("##:##");
            mask.setPlaceholderCharacter('#');
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
		tfxtTs = new JFormattedTextField(mask);
		tfxtTs.setForeground(Color.GRAY);
		tfxtTs.setFont(f1);
		tfxtTs.setHorizontalAlignment(JTextField.CENTER);
		tfxtTs.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				tfxtTs.setForeground(Color.BLACK);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {	
			}
		});
		
		tfxtTe = new JFormattedTextField(mask);
		tfxtTe.setForeground(Color.GRAY);
		tfxtTe.setFont(f1);
		tfxtTe.setHorizontalAlignment(JTextField.CENTER);
		tfxtTe.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				tfxtTe.setForeground(Color.BLACK);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {	
			}
		});
		
		txtPp = new JTextField();
		txtPp.setFont(f1);
		
		btnNxt = new JButton();
		ImageIcon icNxt = new ImageIcon(getClass().getClassLoader().getResource("next.png")); 
		Image imgNxt = icNxt.getImage();
		Image newimgNxt = imgNxt.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		icNxt = new ImageIcon(newimgNxt);
		btnNxt.setIcon(icNxt);
		btnNxt.setContentAreaFilled(false);
		btnNxt.setBorderPainted(false);
		btnNxt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(cmbOff.getSelectedIndex() == -1 || jcDov.getDate() == null || 
						tfxtTs.getText().equals("##:##") || tfxtTe.getText().equals("##:##")) {
					JOptionPane.showMessageDialog(null, "Empty field/s", "Can't proceed",JOptionPane.WARNING_MESSAGE);
				}else {
					passValue();
					surveyq1Window qw1 = new surveyq1Window();
					qw1.setVisible(true);
					setVisible(false);
				}
			}
		});
		
		JLabel lblMenu = new JLabel("MENU");
		lblMenu.setForeground(Color.white);
		lblMenu.setFont(new Font("Open Sans Condensed", Font.PLAIN, 13));
		lblMenu.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {	
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(cmbOff.getSelectedIndex() == -1 & jcDov.getDate() == null & 
						tfxtTs.getText().equals("##:##") & tfxtTe.getText().equals("##:##")) {
					startingWindow sw = new startingWindow();
					sw.setVisible(true);
					setVisible(false);
				}else {
					int result = JOptionPane.showConfirmDialog(null, "You will lose your data. Continue?", "Confirm", JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.YES_OPTION) {	
						startingWindow sw = new startingWindow();
						sw.setVisible(true);
						setVisible(false);
					}
				}
			}
		});
		ImageIcon ic = new ImageIcon(getClass().getClassLoader().getResource("survey.png")); 
		Image img = ic.getImage();
		
		this.setIconImage(img);
		this.setSize(1100,700);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(blueJeans);
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK.brighter()));
		this.setUndecorated(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(pnlL, "cell 0 0");
		add(pnlR, "cell 1 0");
		
		pnlL.add(lblLogo, "wrap, center, gapy 200");
		pnlL.add(lblSN, "wrap, center");
		pnlL.add(lblSy, "wrap, center, gapy 5");
		pnlL.add(lblMenu, "center, gapy 245");
		
		pnlR.add(lblHead, "cell 0 0, left, gapy 10");
		pnlR.add(lblName, "cell 0 1, gapy 70");
		pnlR.add(lblDoV, "cell 1 1, gapy 70");
		pnlR.add(txtName, "cell 0 2, height 35px, width 250px");
		pnlR.add(jcDov, "cell 1 2, height 35px, width 250px");
		pnlR.add(lblOV, "cell 0 3, gapy 40");
		pnlR.add(lblTs, "cell 1 3, gapy 40, gapx 15");
		pnlR.add(lblTe, "cell 1 3, gapy 40, gapx 48");
		pnlR.add(cmbOff, "cell 0 4, height 35px, width 250px");
		pnlR.add(tfxtTs, "cell 1 4, height 35px, width 80px, gapx 20");
		pnlR.add(tfxtTe, "cell 1 4, height 35px, width 80px, gapx 60");
		pnlR.add(lblPp, "cell 0 5, gapy 50");
		pnlR.add(txtPp, "cell 0 6, height 45px, grow, span");
		pnlR.add(btnNxt, "cell 1 7, right, height 35px, gapy 97");
	}
		public void passValue() {
		
		if (txtName.getText().equals("")) {
			txtName.setText(" ");
		}else if (txtPp.getText().equals("")) {
			txtPp.setText(" ");
		}
		
		partingMsgWindow pmw = new partingMsgWindow();
		pmw.submit(txtName.getText(), cmbOff.getSelectedItem(), tfxtTs.getText(), 
				(new java.sql.Date(jcDov.getDate().getTime())), tfxtTe.getText(), txtPp.getText());
	}
}
