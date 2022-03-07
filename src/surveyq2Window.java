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
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class surveyq2Window extends JFrame{
	
	JPanel pnlL = new JPanel();
	JPanel pnlR = new JPanel();
	JButton btnVD, btnD, btnN, btnS, btnVS;
	ImageIcon icVD, icD, icN, icS, icVS;
	Image imgVD, newimgVD, imgD, newimgD, imgN, newimgN, imgS, newimgS, imgVS, newimgVS;
	JTextField txtReactq2;
	int id;
	
	public surveyq2Window(){
		setLayout(new MigLayout("center"));
		
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
		pnlR.setBackground(java.awt.Color.WHITE);
		pnlR.setPreferredSize(new Dimension(800, 700));
		
		JPanel pnlEmojis = new JPanel();
		pnlEmojis.setLayout(new MigLayout("center, fill"));
		pnlEmojis.setBackground(Color.WHITE);
		pnlEmojis.setPreferredSize(new Dimension(800, 130));
		
		JLabel lblHead = new JLabel("SERVICES");
		lblHead.setFont(new Font("Open Sans Condensed", Font.BOLD, 30));
		
		String question2 = ("<html>Was your concern addressed promptly </br><center>and appropriately?</center></html>");
		JLabel lblQtn = new JLabel(question2);
		lblQtn.setFont(new Font("Open Sans Condensed", Font.BOLD, 25));
		
		JLabel lblQtnBis = new JLabel("Ang imo bang tuyo na istoryahag diha-diha og angay?");
		lblQtnBis.setFont(new Font("Open Sans Condensed", Font.ITALIC, 17));
		
		txtReactq2 = new JTextField();
		
		btnVD = new JButton();
		icVD = new ImageIcon(getClass().getClassLoader().getResource("very-dissatisfied.png")); 
		imgVD = icVD.getImage();
		newimgVD = imgVD.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
		icVD = new ImageIcon(newimgVD);
		btnVD.setIcon(icVD);
		btnVD.setContentAreaFilled(false);
		btnVD.setBorderPainted(false);
		btnVD.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				newimgVD = imgVD.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				icVD = new ImageIcon(newimgVD);
				btnVD.setIcon(icVD);
				
				newimgD = imgD.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
				icD = new ImageIcon(newimgD);
				btnD.setIcon(icD);
				
				newimgN = imgN.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
				icN = new ImageIcon(newimgN);
				btnN.setIcon(icN);
				
				newimgS = imgS.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
				icS = new ImageIcon(newimgS);
				btnS.setIcon(icS);
				
				newimgVS = imgVS.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
				icVS = new ImageIcon(newimgVS);
				btnVS.setIcon(icVS);
				
				txtReactq2.setText("Very Dissatisfied");
				passValue();
			}
		});
		
		btnD = new JButton();
		icD = new ImageIcon(getClass().getClassLoader().getResource("dissatisfied.png")); 
		imgD = icD.getImage();
		newimgD = imgD.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
		icD = new ImageIcon(newimgD);
		btnD.setIcon(icD);
		btnD.setContentAreaFilled(false);
		btnD.setBorderPainted(false);
		btnD.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				newimgD = imgD.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				icD = new ImageIcon(newimgD);
				btnD.setIcon(icD);
				
				newimgVD = imgVD.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
				icVD = new ImageIcon(newimgVD);
				btnVD.setIcon(icVD);
				
				newimgN = imgN.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
				icN = new ImageIcon(newimgN);
				btnN.setIcon(icN);
				
				newimgS = imgS.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
				icS = new ImageIcon(newimgS);
				btnS.setIcon(icS);
				
				newimgVS = imgVS.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
				icVS = new ImageIcon(newimgVS);
				btnVS.setIcon(icVS);

				txtReactq2.setText("Dissatisfied");
				passValue();
			}
		});
		
		btnN = new JButton();
		icN = new ImageIcon(getClass().getClassLoader().getResource("neutral.png")); 
		imgN = icN.getImage();
		newimgN = imgN.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
		icN = new ImageIcon(newimgN);
		btnN.setIcon(icN);
		btnN.setContentAreaFilled(false);
		btnN.setBorderPainted(false);
		btnN.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				newimgN = imgN.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				icN = new ImageIcon(newimgN);
				btnN.setIcon(icN);
				
				newimgVD = imgVD.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
				icVD = new ImageIcon(newimgVD);
				btnVD.setIcon(icVD);
				
				newimgD = imgD.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
				icD = new ImageIcon(newimgD);
				btnD.setIcon(icD);
				
				newimgS = imgS.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
				icS = new ImageIcon(newimgS);
				btnS.setIcon(icS);
				
				newimgVS = imgVS.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
				icVS = new ImageIcon(newimgVS);
				btnVS.setIcon(icVS);

				txtReactq2.setText("Neutral");
				passValue();
			}
		});
		
		btnS = new JButton();
		icS = new ImageIcon(getClass().getClassLoader().getResource("satisfied.png")); 
		imgS = icS.getImage();
		newimgS = imgS.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
		icS = new ImageIcon(newimgS);
		btnS.setIcon(icS);
		btnS.setContentAreaFilled(false);
		btnS.setBorderPainted(false);
		btnS.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				newimgS = imgS.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				icS = new ImageIcon(newimgS);
				btnS.setIcon(icS);
				
				newimgVD = imgVD.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
				icVD = new ImageIcon(newimgVD);
				btnVD.setIcon(icVD);
				
				newimgD = imgD.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
				icD = new ImageIcon(newimgD);
				btnD.setIcon(icD);
				
				newimgN = imgN.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
				icN = new ImageIcon(newimgN);
				btnN.setIcon(icN);
				
				newimgVS = imgVS.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
				icVS = new ImageIcon(newimgVS);
				btnVS.setIcon(icVS);

				txtReactq2.setText("Satisfied");
				passValue();
			}
		});
		
		
		btnVS = new JButton();
		icVS = new ImageIcon(getClass().getClassLoader().getResource("very-satisfied.png")); 
		imgVS = icVS.getImage();
		newimgVS = imgVS.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
		icVS = new ImageIcon(newimgVS);
		btnVS.setIcon(icVS);
		btnVS.setContentAreaFilled(false);
		btnVS.setBorderPainted(false);
		btnVS.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				newimgVS = imgVS.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				icVS = new ImageIcon(newimgVS);
				btnVS.setIcon(icVS);
				
				newimgVD = imgVD.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
				icVD = new ImageIcon(newimgVD);
				btnVD.setIcon(icVD);
				
				newimgD = imgD.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
				icD = new ImageIcon(newimgD);
				btnD.setIcon(icD);
				
				newimgN = imgN.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
				icN = new ImageIcon(newimgN);
				btnN.setIcon(icN);
				
				newimgS = imgS.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
				icS = new ImageIcon(newimgS);
				btnS.setIcon(icS);

				txtReactq2.setText("Very Satisfied");
				passValue();
			}
		});
		JButton btnNxt = new JButton();
		ImageIcon icNxt = new ImageIcon(getClass().getClassLoader().getResource("next.png")); 
		Image imgNxt = icNxt.getImage();
		Image newimgNxt = imgNxt.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		icNxt = new ImageIcon(newimgNxt);
		btnNxt.setIcon(icNxt);
		btnNxt.setContentAreaFilled(false);
		btnNxt.setBorderPainted(false);
		btnNxt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				if(txtReactq2.getText().equals("")){
					JOptionPane.showMessageDialog(null, "No reaction selected", "Can't proceed",JOptionPane.WARNING_MESSAGE);
				}else {
					next();
					
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
				int result = JOptionPane.showConfirmDialog(null, "You will lose your input data. Continue?", "Confirm", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {	
					startingWindow sw = new startingWindow();
					sw.setVisible(true);
					setVisible(false);
					getLastId();
					delDate();
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
		
		pnlR.add(lblHead, "cell 0 0, left");
		pnlR.add(lblQtn, "cell 0 1, gapy 50, height 30px, center");
		pnlR.add(lblQtnBis, "cell 0 2, height 30px, center");
		pnlR.add(pnlEmojis, "cell 0 3, gapy 36, center,  growx, spanx");
			pnlEmojis.add(btnVD, "center");
			pnlEmojis.add(btnD, "center");
			pnlEmojis.add(btnN, "center");
			pnlEmojis.add(btnS, "center");
			pnlEmojis.add(btnVS, "center");
		pnlR.add(btnNxt, "cell 0 4, gapy 50, right");
	}
	private void next() {
		surveyq3Window qw3 = new surveyq3Window();
		qw3.setVisible(true);
		setVisible(false);
	}
	public void passValue() {
		partingMsgWindow pmw = new partingMsgWindow();
		pmw.react2(txtReactq2.getText());
	}
	public void getLastId() {		
		String query = "SELECT MAX(id) from survey_table";
		connectionWindow db = new connectionWindow();
		
		try {
			db.statement = db.conn.createStatement();
			db.rs = db.statement.executeQuery(query);
			
			if(db.rs.next()) {
				id = db.rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void delDate() {
		String query = "DELETE FROM survey_table WHERE id = ? ";
		connectionWindow db = new connectionWindow();
		
		try {
			db.ps = db.conn.prepareStatement(query);
			db.ps.setInt(1,  id);
			
			db.ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}