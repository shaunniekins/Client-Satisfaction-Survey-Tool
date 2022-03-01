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

public class startingWindow extends JFrame{
	
	JButton btnTake;
	JPanel panel, pnlTitleBar;
	
	public startingWindow() {
		setLayout(new MigLayout("center"));//insets 200 50 50 50
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		Color blueJeans = new Color(75, 126, 191);
		Color smokeGray = new Color(117, 123, 130);
		
		//ImageIcon icBg = new ImageIcon(".//pics//picture bg.jpg"); 
		ImageIcon icBg = new ImageIcon(getClass().getClassLoader().getResource("picture bg.jpg")); 
		Image imgBg = icBg.getImage();
		Image newimgBg = imgBg.getScaledInstance(1100, 700 , Image.SCALE_SMOOTH); 
		icBg = new ImageIcon(newimgBg);
		
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g); 
				g.drawImage(newimgBg, 0, 0, null);
			}};
		panel.setPreferredSize(new Dimension(1100, 700));
			
		panel.setLayout(new MigLayout("center"));
		
		pnlTitleBar = new JPanel();
		pnlTitleBar.setLayout(new MigLayout("filly"));
		pnlTitleBar.setPreferredSize(new Dimension(1100, 15));
		pnlTitleBar.setBackground(blueJeans);
		
		JLabel lblTitleBarIcon = new JLabel("Client Satisfaction Survey");
		ImageIcon icTitleBarIcon = new ImageIcon("icons/survey.png");
		Image imgTitleBarIcon = icTitleBarIcon.getImage();
		Image newimgTitleBarIcon = imgTitleBarIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		icTitleBarIcon = new ImageIcon(newimgTitleBarIcon);
		lblTitleBarIcon.setIcon(icTitleBarIcon);
		
		JLabel lblX = new JLabel("X");
		lblX.setForeground(Color.WHITE);
		lblX.setFont(new Font("Arial Black", Font.BOLD, 15));
		
		JLabel lblMin = new JLabel("--");
		lblMin.setForeground(Color.WHITE);
		lblMin.setFont(new Font("Arial Black", Font.BOLD, 18));
		
		JLabel lblSN = new JLabel("BAYUGAN CITY SENIOR HIGH SCHOOL (STAND-ALONE)");
		lblSN.setForeground(Color.WHITE);
		lblSN.setFont(new Font("Open Sans Condensed",Font.PLAIN,25));
		
		JLabel lblTitle = new JLabel("CLIENT SATISFACTION SURVEY");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Open Sans Condensed",Font.BOLD,50));
		
		JLabel lblTL = new JLabel("Your Experience Matters to Us!");
		lblTL.setForeground(Color.WHITE);
		lblTL.setFont(new Font("Open Sans Condensed",Font.ITALIC,20));
		
		JButton btnView = new JButton("VIEW RATINGS (ADMIN)");
		btnView.setFont(new Font("Open Sans Condensed", Font.PLAIN, 15));
		btnView.setForeground(Color.WHITE);
		btnView.setBackground(Color.BLUE.brighter());
		btnView.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmationWindow cw = new confirmationWindow();
				cw.setVisible(true);
				setVisible(false);
				
			}
		});
		
		btnTake = new JButton("TAKE A SURVEY");
		btnTake.setFont(new Font("Open Sans Condensed", Font.PLAIN, 15));
		btnTake.setForeground(Color.WHITE);
		btnTake.setBackground(Color.BLUE.brighter());
		btnTake.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				userInfoWindow uiw = new userInfoWindow();
				uiw.setVisible(true);
				setVisible(false);
				
			}
		});
		JLabel lblExt = new JLabel("EXIT");
		lblExt.setFont(new Font("Open Sans Condensed",Font.CENTER_BASELINE,15));
		lblExt.setForeground(Color.WHITE);
		lblExt.addMouseListener(new MouseListener() {
			
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
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
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
		
		//add(pnlTitleBar, "wrap");
		add(panel, "wrap");
		
		/*pnlTitleBar.add(lblTitleBarIcon);
		pnlTitleBar.add(lblMin, "gapx 843");
		pnlTitleBar.add(lblX, "gapx 26");*/
		
		panel.add(lblSN, "cell 0 0, center, gapy 200");
		panel.add(lblTitle," cell 0 1, center, gapy 50");
		panel.add(lblTL, "cell 0 2, center, gapy 20");
		panel.add(btnView, "cell 0 3, center, gapy 50, width 200px, height 50px");
		panel.add(btnTake, "cell 0 3, gapx 50, height 50px, width 200px");
		panel.add(lblExt, "cell 0 4, center, gapy 40, bottom");
	}
	
}
