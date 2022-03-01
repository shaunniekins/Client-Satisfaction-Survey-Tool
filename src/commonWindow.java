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

public class commonWindow extends JFrame{
	
	JPanel pnlL;
	JPanel pnlR;
	int z = 10;
	
	public commonWindow() {
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
		pnlR.setLayout(new MigLayout("center"));
		pnlR.setBackground(Color.WHITE);
		pnlR.setPreferredSize(new Dimension(800, 700));
				
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
				
				startingWindow sw = new startingWindow();
				sw.setVisible(true);
				setVisible(false);
				/*if(cmbOff.getSelectedIndex() == -1 & jcDov.getDate() == null & 
						tfxtTs.getText().equals("##:##") & tfxtTe.getText().equals("##:##")) {
				}else {
					int result = JOptionPane.showConfirmDialog(null, "You will lose your data. Continue?", "Confirm", JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.YES_OPTION) {	
						startingWindow sw = new startingWindow();
						sw.setVisible(true);
						setVisible(false);
					}
				}*/
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
		
		add(pnlL);
		add(pnlR, "cell 1 0");
		
		pnlL.add(lblLogo, "wrap, center, gapy 200");
		pnlL.add(lblSN, "wrap, center");
		pnlL.add(lblSy, "wrap, center, gapy 5");
		pnlL.add(lblMenu, "center, gapy 245");
		
	}
	public void showWindow(int a) {
		z = a;
		display();
		System.out.println(z);
	}
	public void display() {
		
		if(z == 0) {
			removeComponents();
			
			userInfoWindow uiw = new userInfoWindow();
			pnlR.add(uiw.getContentPane());
			pnlR.repaint();
			pnlR.revalidate();
		}
		else if(z == 1) {
			removeComponents();
			
			surveyq1Window sw1 = new surveyq1Window();
			pnlR.add(sw1.getContentPane());
			pnlR.revalidate();
			pnlR.repaint();
		}
	}
	public void removeComponents() {
		Component[] cList = pnlR.getComponents();
		
		for(Component c : cList) {
			pnlR.remove(c);
			
		}
		
	}
}
