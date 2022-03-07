import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import net.miginfocom.swing.MigLayout;


public class printWindow extends JFrame{
	
	JPanel pnlPagePrint;
	JPanel pnlTextArea;
	JPanel pnlTotal;
	JPanel pnlRatings;
	
	ChartPanel pnlbarGraph2;
	ChartPanel pnlTrb2;
	ChartPanel pnlPhy;
	ChartPanel pnlSer;
	ChartPanel pnlPer;
	
	JLabel lblSchoolName;
	JLabel lblTitlePrint;
	
	public printWindow(){
		setLayout(new MigLayout());
		
		pnlPagePrint = new JPanel();
		pnlPagePrint.setLayout(new MigLayout("center, fill, insets 0 20 0 20")); // up, left, down, right
		pnlPagePrint.setPreferredSize(new Dimension(496, 800)); //2480 x 3508
		pnlPagePrint.setBackground(Color.white);
		
		pnlTextArea = new JPanel();
		pnlTextArea.setLayout(new MigLayout("center"));
		//pnlTextArea.setPreferredSize(new Dimension(496, 50));
		pnlTextArea.setBackground(Color.WHITE);
		
		lblSchoolName = new JLabel("Bayugan City Senior High School (Stand-Alone)");
		lblSchoolName.setFont(new Font("Open Sans Condensed", Font.BOLD, 18));
		
		lblTitlePrint = new JLabel("REPORT OVERVIEW");
		lblTitlePrint.setFont(new Font("Open Sans Condensed", Font.BOLD, 25));
		
		pnlTotal = new JPanel();
		pnlTotal.setLayout(new MigLayout("center"));
		pnlTotal.setPreferredSize(new Dimension(496, 200));
		pnlTotal.setBackground(Color.WHITE);
		
		pnlRatings = new JPanel();
		pnlRatings.setLayout(new MigLayout("center"));
		pnlRatings.setPreferredSize(new Dimension(496, 500));
		pnlRatings.setBackground(Color.WHITE);
		
		this.setSize(496, 800);// (int) 701.6
		//this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.white);
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK.brighter()));
		this.setUndecorated(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void values(JFreeChart chart, JFreeChart chartTrb, JFreeChart chartPhy, JFreeChart chartSer, JFreeChart chartPer) {
		pnlbarGraph2 = new ChartPanel(chart);
		//chart.setBackgroundPaint(Color.WHITE);
		pnlTrb2 = new ChartPanel(chartTrb);
		pnlPhy = new ChartPanel(chartPhy);
		pnlSer = new ChartPanel(chartSer);
		pnlPer = new ChartPanel(chartPer);
		
		add(pnlPagePrint);
		
		pnlPagePrint.add(pnlTextArea, "center, cell 0 0, growx, spanx");
			pnlTextArea.add(lblSchoolName, "wrap, center");
			pnlTextArea.add(lblTitlePrint, "wrap, center");
		pnlPagePrint.add(pnlTotal, "cell 0 1, center");	
			pnlTotal.add(pnlbarGraph2, "cell 0 0, center");
			pnlTotal.add(pnlTrb2, "cell 1 0, center");
		pnlPagePrint.add(pnlRatings, "cell 0 2, center");	
			pnlRatings.add(pnlPhy, "cell 0 0, center");
			pnlRatings.add(pnlSer, "cell 0 1, center");
			pnlRatings.add(pnlPer, "cell 0 2, center");		
		
		
	}
}
