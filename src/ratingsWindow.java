import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.toedter.calendar.JDateChooser;

import javafx.print.PageLayout;
import net.miginfocom.swing.MigLayout;

public class ratingsWindow extends JFrame{
	
	JPanel pnlScreen = new JPanel();
		JPanel pnlLeft = new JPanel();
			JPanel pnltcv = new JPanel();
			ChartPanel pnlbarGraph;
		JPanel pnlRight = new JPanel();
			ChartPanel pnlTrb;
		JPanel pnlRowSec = new JPanel();
			ChartPanel pnlPhysical;
			ChartPanel pnlServices;
			ChartPanel pnlPersonnel;
			JPanel pnlAdd_ons = new JPanel();
	
	JPanel pnlPagePrint;	
			
	CategoryDataset datasetPhysical;
	CategoryDataset dataset;
	PieDataset datasetTrb;
	CategoryDataset datasetServices;
	CategoryDataset datasetPersonnel;
	
	JTextField txtSearch;
	CategoryPlot cplot, cplotPhy, cplotSer, cplotPer;
	PiePlot plotTrb;
	
	JFreeChart chartPhysical;
			
	connectionWindow db = new connectionWindow();		
			
	JDateChooser ratingsDate;	
	//String startDate, endDate;
	String startDate;
	LocalDate endDate;
	String date1 = ""; //and
	String date2 = ""; //where
			
	JTable tblAdd_ons;
	JTable tblComments;
	DefaultTableModel model, modelComments;
	
	JLabel lblTRB;
	String trb;
	
	BufferedImage[] images;
	
	int r;
	
	int officeVisitGpta, officeVisitGuidance, officeVisitPrincipal, officeVisitRegistrar, officeVisitOthers;
	
	int totalReactVd;
		int totalReactVd1, totalReactVd2, totalReactVd3;
	int totalReactD;
		int totalReactD1, totalReactD2, totalReactD3;
	int totalReactN;
		int totalReactN1, totalReactN2, totalReactN3;
	int totalReactS;
		int totalReactS1, totalReactS2, totalReactS3;
	int totalReactVs;
		int totalReactVs1, totalReactVs2, totalReactVs3;
	
	int physicalVdGpta, physicalVdGuidance, physicalVdPrincipal, physicalVdRegistrar, physicalVdOthers;
	int physicalDGpta, physicalDGuidance, physicalDPrincipal, physicalDRegistrar, physicalDOthers;
	int physicalNGpta, physicalNGuidance, physicalNPrincipal, physicalNRegistrar, physicalNOthers;
	int physicalSGpta, physicalSGuidance, physicalSPrincipal, physicalSRegistrar, physicalSOthers;
	int physicalVsGpta, physicalVsGuidance, physicalVsPrincipal, physicalVsRegistrar, physicalVsOthers;
	int servicesVdGpta, servicesVdGuidance, servicesVdPrincipal, servicesVdRegistrar, servicesVdOthers;	
	int servicesDGpta, servicesDGuidance, servicesDPrincipal, servicesDRegistrar, servicesDOthers;	
	int servicesNGpta, servicesNGuidance, servicesNPrincipal, servicesNRegistrar, servicesNOthers;	
	int servicesSGpta, servicesSGuidance, servicesSPrincipal, servicesSRegistrar,servicesSOthers;	
	int servicesVsGpta, servicesVsGuidance, servicesVsPrincipal, servicesVsRegistrar, servicesVsOthers;	
	int personnelVdGpta, personnelVdGuidance, personnelVdPrincipal, personnelVdRegistrar, personnelVdOthers;	
	int personnelDGpta, personnelDGuidance, personnelDPrincipal, personnelDRegistrar, personnelDOthers;	
	int personnelNGpta, personnelNGuidance, personnelNPrincipal, personnelNRegistrar, personnelNOthers;	
	int personnelSGpta, personnelSGuidance, personnelSPrincipal, personnelSRegistrar, personnelSOthers;	
	int personnelVsGpta, personnelVsGuidance, personnelVsPrincipal, personnelVsRegistrar, personnelVsOthers;
	
	public ratingsWindow() {
		setLayout(new MigLayout("center, fill"));
		
		deleteNull();
		
		Font f = new Font("Open Sans Condensed", Font.BOLD, 20);
		Color blueJeans = new Color(75, 126, 191);
		Color smokeGray = new Color(192, 192, 192);
		//Color blueJeans = Color.BLUE;
		
		pnlScreen.setLayout(new MigLayout("center"));
		pnlScreen.setBackground(smokeGray);
		pnlScreen.setPreferredSize(new Dimension(1050, 1300));
		pnlScreen.setVisible(true);
		
			pnlLeft.setLayout(new MigLayout("center"));
			pnlLeft.setBackground(smokeGray);
			pnlLeft.setPreferredSize(new Dimension(670, 430));
			pnlLeft.setVisible(true);
				
					JLabel lblHead = new JLabel("RATINGS");
					ImageIcon icHead = new ImageIcon(getClass().getClassLoader().getResource("bcshs-logo.png")); 
					java.awt.Image imgHead = icHead.getImage();
					java.awt.Image newimgHead = imgHead.getScaledInstance(70, 70,  Image.SCALE_SMOOTH);
					icHead = new ImageIcon(newimgHead);
					lblHead.setIcon(icHead);
					lblHead.setFont(new Font("Open Sans Condensed", Font.BOLD, 40));
					
					JLabel lblTop = new JLabel("Client Visitation Breakdown");
					lblTop.setFont(f);
					
					pnltcv.setLayout(new MigLayout("center"));
					pnltcv.setBackground(smokeGray);
					pnltcv.setPreferredSize(new Dimension(250, 290));
					pnltcv.setVisible(true);
					
						JLabel lblTotalClient = new JLabel("TOTAL CLIENT VISITATION");
						lblTotalClient.setForeground(Color.white);
						lblTotalClient.setFont(new Font("Open Sans Condensed", Font.PLAIN, 13));
						
							JPanel pnlCoverTc = new JPanel();
							pnlCoverTc.setBackground(blueJeans);
						
						JLabel lblClient = new JLabel();
						ImageIcon icClient = new ImageIcon(getClass().getClassLoader().getResource("blank-profile.png")); 
						Image imgClient = icClient.getImage();
						Image newimgClient = imgClient.getScaledInstance(170, 170,  Image.SCALE_SMOOTH);
						icClient = new ImageIcon(newimgClient);
						lblClient.setIcon(icClient);
						
						
						lblTRB = new JLabel();
						lblTRB.setForeground(blueJeans);
						lblTRB.setFont(new Font("Open Sans Condensed", Font.BOLD, 50));
						
					dataset = createDataset();	    
					JFreeChart chart = ChartFactory.createBarChart(
							null, //Chart Title
							null, // Category axis
						    null, // Value axis
						    dataset,
						    PlotOrientation.HORIZONTAL,
						    false, true, false); //legends, idk, idk
					chart.setBackgroundPaint(smokeGray);
					cplot = (CategoryPlot)chart.getPlot();
					BarRenderer renderer = (BarRenderer) cplot.getRenderer();
					renderer.setSeriesPaint(0, blueJeans);

					pnlbarGraph = new ChartPanel(chart);
					pnlbarGraph.setLayout(new MigLayout("center"));
					//pnlbarGraph.setBackground(Color.GRAY.brighter());
					pnlbarGraph.setPreferredSize(new Dimension(430, 290));
					pnlbarGraph.setVisible(true);
		
			pnlRight.setLayout(new MigLayout("center"));
			pnlRight.setBackground(smokeGray);
			pnlRight.setPreferredSize(new Dimension(430, 430));
			pnlRight.setVisible(true);
				
				datasetTrb = createDatasetTrb();
				//Create chart
				JFreeChart chartTrb = ChartFactory.createPieChart(
					"TOTAL REACTION BREAKDOWN", //Chart Title
					datasetTrb,
					false, true, false); //legends, idk, idk
				chartTrb.setBackgroundPaint(smokeGray);
				chartTrb.setBorderVisible(false);
				chartTrb.getTitle().setPaint(Color.BLACK);
				plotTrb = (PiePlot) chartTrb.getPlot();
				plotTrb.setSectionPaint(0, Color.red);
				plotTrb.setSectionPaint(1, Color.ORANGE);
				plotTrb.setSectionPaint(2, Color.yellow.brighter());
				plotTrb.setSectionPaint(3, Color.green.darker());
				plotTrb.setSectionPaint(4, Color.green.brighter());
				
				pnlTrb = new ChartPanel(chartTrb);
				pnlTrb.setLayout(new MigLayout("center"));
				pnlTrb.setBackground(Color.WHITE);
				pnlTrb.setPreferredSize(new Dimension(430, 350));
				pnlTrb.setVisible(true);
					    
			pnlRowSec.setLayout(new MigLayout());
			pnlRowSec.setBackground(smokeGray);
			pnlRowSec.setPreferredSize(new Dimension(670, 870));
			pnlRowSec.setVisible(true);		 
			
				JLabel lblRb = new JLabel("Reactions Breakdown");
				lblRb.setFont(f);
				
				datasetPhysical = createDatasetPhysical();	    ///////////////////////////////////////
				//Create chart
				chartPhysical = ChartFactory.createBarChart3D(
						"PHYSICAL", //Chart Title
						"Offices", // Category axis
					    null, // Value axis
					    datasetPhysical,
					    PlotOrientation.VERTICAL,
					    true, true, false); //legends, idk, idk
				chartPhysical.setBackgroundPaint(smokeGray);
				chartPhysical.getTitle().setPaint(Color.BLACK);
				cplotPhy = (CategoryPlot)chartPhysical.getPlot();
				BarRenderer rendererPhy = (BarRenderer) cplotPhy.getRenderer();
				rendererPhy.setSeriesPaint(0, Color.red);
				rendererPhy.setSeriesPaint(1, Color.ORANGE);
				rendererPhy.setSeriesPaint(2, Color.YELLOW);
				rendererPhy.setSeriesPaint(3, Color.GREEN.darker());
				rendererPhy.setSeriesPaint(4, Color.green.brighter().brighter());
				
				pnlPhysical= new ChartPanel(chartPhysical);
				pnlPhysical.setLayout(new MigLayout("center"));
				pnlPhysical.setPreferredSize(new Dimension(580, 250));
				
				datasetServices = createDatasetServices();	    
				//Create chart
				JFreeChart chartServices = ChartFactory.createBarChart3D(
						"SERVICES", //Chart Title
						"Offices", // Category axis
					    null, // Value axis
					    datasetServices,
					    PlotOrientation.VERTICAL,
					    true, true, false); //legends, idk, idk
				chartServices.setBackgroundPaint(smokeGray);
				chartServices.getTitle().setPaint(Color.BLACK);
				cplotSer = (CategoryPlot)chartServices.getPlot();
				BarRenderer rendererSer = (BarRenderer) cplotSer.getRenderer();
				rendererSer.setSeriesPaint(0, Color.red);
				rendererSer.setSeriesPaint(1, Color.ORANGE);
				rendererSer.setSeriesPaint(2, Color.YELLOW);
				rendererSer.setSeriesPaint(3, Color.GREEN.darker());
				rendererSer.setSeriesPaint(4, Color.green.brighter().brighter());
				
				pnlServices= new ChartPanel(chartServices);
				pnlServices.setLayout(new MigLayout("center"));
				pnlServices.setBackground(Color.BLACK);
				pnlServices.setPreferredSize(new Dimension(580, 250));
				pnlServices.setVisible(true);
				
				datasetPersonnel = createDatasetPersonnel();	    
				//Create chart
				JFreeChart chartPersonnel = ChartFactory.createBarChart3D(
						"PERSONNEL",
						"Offices",
					    null,
					    datasetPersonnel,
					    PlotOrientation.VERTICAL,
					    true, true, false);
				chartPersonnel.setBackgroundPaint(smokeGray);
				chartPersonnel.getTitle().setPaint(Color.BLACK);
				cplotPer = (CategoryPlot)chartPersonnel.getPlot();
				BarRenderer rendererPer = (BarRenderer) cplotPer.getRenderer();
				rendererPer.setSeriesPaint(0, Color.red);
				rendererPer.setSeriesPaint(1, Color.ORANGE);
				rendererPer.setSeriesPaint(2, Color.YELLOW);
				rendererPer.setSeriesPaint(3, Color.GREEN.darker());
				rendererPer.setSeriesPaint(4, Color.green.brighter().brighter());
				
				pnlPersonnel= new ChartPanel(chartPersonnel);
				pnlPersonnel.setLayout(new MigLayout("center"));
				pnlPersonnel.setPreferredSize(new Dimension(580, 250));
				pnlPersonnel.setVisible(true);
				
				pnlAdd_ons.setLayout(new MigLayout());
				pnlAdd_ons.setBackground(smokeGray);
				pnlAdd_ons.setPreferredSize(new Dimension(450, 865));
				pnlAdd_ons.setVisible(true);		 
				
					JLabel lblSearch = new JLabel("Search: ");
					lblSearch.setFont(new Font("Open Sans Condensed", Font.BOLD, 18));
				
					txtSearch = new JTextField(10);
					txtSearch.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
					txtSearch.setFont(new Font("Open Sans Condensed", Font.PLAIN, 16));
					
					
					txtSearch.getDocument().addDocumentListener(new DocumentListener() {
						
						@Override
						public void removeUpdate(DocumentEvent e) {
							if(txtSearch.getText().equals("")) {
								displayInfo();
							}else {	
								search(txtSearch.getText());
							} 
						}
						
						@Override
						public void insertUpdate(DocumentEvent e) {
							if(txtSearch.getText().equals("")) {
								displayInfo();
							}else {
								search(txtSearch.getText());
							}
						}
						
						@Override
						public void changedUpdate(DocumentEvent e) {
							if(txtSearch.getText().equals("")) {
								displayInfo();
							}else {
								search(txtSearch.getText());
							}
						}
					});
					
					String[] header = {"Name", "Office Visited", "Date of Visit", "Time Started", "Time Ended", "Purpose"};
					
					tblAdd_ons = new JTable();
					tblAdd_ons.getTableHeader().setOpaque(false);
					tblAdd_ons.setBackground(smokeGray);
					tblAdd_ons.getTableHeader().setForeground(blueJeans);
					tblAdd_ons.getTableHeader().setFont(new Font("Open Sans Condensed", Font.BOLD, 15));
					tblAdd_ons.setRowHeight(35);
					model = new DefaultTableModel(){
					    @Override
					    public boolean isCellEditable(int row, int column) {
					       return false;
					    }
					};
					tblAdd_ons.setModel(model);
					model.setColumnIdentifiers(header);
					TableRowSorter sorter = new TableRowSorter<>(model);
					tblAdd_ons.setRowSorter(sorter);
					displayInfo();
										
					tblAdd_ons.getColumnModel().getColumn(1).setPreferredWidth(110);
					tblAdd_ons.getColumnModel().getColumn(2).setPreferredWidth(100);
					tblAdd_ons.getColumnModel().getColumn(3).setPreferredWidth(110);
					tblAdd_ons.getColumnModel().getColumn(4).setPreferredWidth(110);
					tblAdd_ons.getColumnModel().getColumn(5).setPreferredWidth(300);
					
					tblAdd_ons.setAutoResizeMode(tblAdd_ons.AUTO_RESIZE_OFF);
					
					JScrollPane pane = new JScrollPane(tblAdd_ons, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
					pane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
					    @Override
					    protected void configureScrollBarColors() {
					        this.thumbColor = blueJeans;
					    }
					});
					pane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
					    @Override
					    protected void configureScrollBarColors() {
					        this.thumbColor = blueJeans;
					    }
					});
					pane.getViewport().setBackground(smokeGray);
					
					DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
					dtcr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
					tblAdd_ons.setDefaultRenderer(Object.class, dtcr);
					((DefaultTableCellRenderer) tblAdd_ons.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
					
					String[] headerComment = {"Comments/Suggestions"};
					
					tblComments = new JTable();
					tblComments.getTableHeader().setOpaque(false);
					tblComments.setBackground(smokeGray);
					tblComments.getTableHeader().setForeground(blueJeans);
					tblComments.getTableHeader().setFont(new Font("Open Sans Condensed", Font.BOLD, 15));
					tblComments.setRowHeight(75);
					modelComments = new DefaultTableModel(){
					    @Override
					    public boolean isCellEditable(int row, int column) {
					       return false;
					    }
					};
					tblComments.setModel(modelComments);
					modelComments.setColumnIdentifiers(headerComment);
					displayComment();
					
					JScrollPane paneComment = new JScrollPane(tblComments);
					paneComment.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
					    @Override
					    protected void configureScrollBarColors() {
					        this.thumbColor = blueJeans;
					    }
					});
					paneComment.getViewport().isOpaque();
					paneComment.getViewport().setBackground(smokeGray);
					
					JButton btnMenu = new JButton();
					ImageIcon icMenu = new ImageIcon(getClass().getClassLoader().getResource("home.png")); 
					Image imgMenu = icMenu.getImage();
					Image newimgMenu = imgMenu.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
					icMenu = new ImageIcon(newimgMenu);
					btnMenu.setIcon(icMenu);
					btnMenu.setBackground(blueJeans);
					btnMenu.setBorderPainted(false);	
					btnMenu.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							startingWindow sw = new startingWindow();
							sw.setVisible(true);
							setVisible(false);
						}
					});
					((DefaultTableCellRenderer) tblComments.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
					
					JButton btnExt = new JButton();
					ImageIcon icExt = new ImageIcon(getClass().getClassLoader().getResource("logout.png")); 
					Image imgExt = icExt.getImage();
					Image newimgExt = imgExt.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
					icExt = new ImageIcon(newimgExt);
					btnExt.setIcon(icExt);
					btnExt.setBackground(blueJeans);
					btnExt.setBorderPainted(false);	
					btnExt.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});
					
					JLabel lblTimeline = new JLabel("TIMELINE");
					lblTimeline.setFont(new Font("Open Sans Condensed", Font.PLAIN, 15));
					
					ratingsDate = new JDateChooser();
					ratingsDate.setDateFormatString("yyyy-MM-dd");
					ratingsDate.setFont(new Font("Open Sans Condensed", Font.PLAIN, 17));
					ratingsDate.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
						@Override
						public void propertyChange(PropertyChangeEvent arg0) {
							if(ratingsDate.getDate() != null) {	
								r = 1;
								
								int chosenYear = ratingsDate.getCalendar().getWeekYear();
								int chosenMonth = ratingsDate.getCalendar().getTime().getMonth() + 1;
								int firstDayOfWeek = ratingsDate.getCalendar().getFirstDayOfWeek();
								
								String dateValue = "" + chosenMonth + "/" + firstDayOfWeek +"/" + chosenYear;
								LocalDate convertDate = LocalDate.parse(dateValue, DateTimeFormatter.ofPattern("M/d/yyyy"));
								convertDate = convertDate.withDayOfMonth(convertDate.getMonth().length(convertDate.isLeapYear()));
								
								LocalDate lastDayOfMonth = convertDate.withDayOfMonth(convertDate.getMonth().length(convertDate.isLeapYear()));
								
								startDate = chosenYear + "-" + chosenMonth + "-" + firstDayOfWeek;
								endDate = lastDayOfMonth;
								
								date1 = " AND date_of_visit BETWEEN '" + startDate + "' AND '" + endDate + "'";
								date2 = " WHERE date_of_visit BETWEEN '" + startDate + "' AND '" + endDate + "'";
								
								refresh();
								
							} else {
								date1 = "";
								date2 = "";
								
								refresh();
							}
						}
					});
					
					JButton btnDeleteData = new JButton("Wipe Data");
					btnDeleteData.setFont(new Font("Open Sans Condensed", Font.PLAIN, 15));
					btnDeleteData.setForeground(Color.white);
					btnDeleteData.setBackground(Color.red.brighter());
					btnDeleteData.setBorderPainted(false);	
					btnDeleteData.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							
							if(r == 1) {
								Object[] options = {"Wipe Current Data", "Wipe All Data"};
								Object selectedValue= JOptionPane.showInputDialog(null, "Data to Wipe Out", "Wiping Data", JOptionPane.WARNING_MESSAGE, null, options, options[0]);
								if(selectedValue == options[0]) {
									deleteCurrentData();
									refresh();

									pnlScreen.repaint();
								}else {
									deleteAllData();
									refresh();
								}									
							}else {
								int result2 = JOptionPane.showConfirmDialog(null, "Are you sure to delete all data?", "Wiping Data", JOptionPane.YES_NO_OPTION);
								if(result2 == JOptionPane.YES_OPTION) {
									deleteAllData();
									refresh();
								}
							}
						}
					});
						
					JButton btnPrint = new JButton("Print Report");
					btnPrint.setFont(new Font("Open Sans Condensed", Font.PLAIN, 15));
					btnPrint.setForeground(Color.white);
					btnPrint.setBackground(blueJeans);
					btnPrint.setBorderPainted(false);	
					btnPrint.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							
							printWindow pw = new printWindow();
							pw.values(chart, chartTrb, chartPhysical, chartServices, chartPersonnel);
							pw.setVisible(true);
							printRatings(pw.pnlPagePrint);
							pw.setVisible(false);
						}
					});
		
		ImageIcon ic = new ImageIcon(getClass().getClassLoader().getResource("survey.png")); 
		Image img = ic.getImage();
					
		this.setIconImage(img);			
		this.setSize(1100,700);
		this.setLocationRelativeTo(null);
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK.brighter()));
		this.getContentPane().setBackground(blueJeans);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				
		
		totalRb();
		
		add(pnlScreen);
		
		JScrollPane scrollFrame = new JScrollPane(pnlScreen);
		
		pnlScreen.setAutoscrolls(true);
		scrollFrame.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = blueJeans;
			}
		});
		scrollFrame.setPreferredSize(new Dimension(1200,900));
		this.add(scrollFrame);

		pnlScreen.add(pnlLeft, "");
			pnlLeft.add(lblHead, "wrap");
			pnlLeft.add(lblTop, "wrap, gapy 10");
			pnlLeft.add(pnltcv, "");
				pnltcv.add(pnlCoverTc, "cell 0 1, center");
					pnlCoverTc.add(lblTotalClient);
				pnltcv.add(lblClient, "cell 0 2, center");
				pnltcv.add(lblTRB, "cell 0 3, center");
			pnlLeft.add(pnlbarGraph, "");
	
		pnlScreen.add(pnlRight, "right, wrap");
			pnlRight.add(lblTimeline, "wrap, gapx 249");
			pnlRight.add(ratingsDate, "wrap, right, width 140px");
			pnlRight.add(pnlTrb, "wrap, gapy 10");
			
		pnlScreen.add(pnlRowSec, "wrap, grow, span");
			pnlRowSec.add(lblRb, "cell 0 0");
			pnlRowSec.add(pnlPhysical, "cell 0 1, gapy 10");
			pnlRowSec.add(pnlServices, "cell 0 2, gapy 10");
			pnlRowSec.add(pnlPersonnel, "cell 0 3, gapy 10");
			pnlRowSec.add(pnlAdd_ons, "cell 1 0, spany 4");
				pnlAdd_ons.add(lblSearch, "cell 0 0, right");
				pnlAdd_ons.add(txtSearch, "cell 0 0, width 150px, right");
				pnlAdd_ons.add(pane, "cell 0 1, gapy 10, center, growx, spanx, height 550px");
				pnlAdd_ons.add(paneComment, "cell 0 2, gapy 10, center, growx, spanx, height 400px");
				pnlAdd_ons.add(btnPrint, "cell 0 3, right, gapy 50, width 134px");
				pnlAdd_ons.add(btnDeleteData, "cell 0 4, right, width 134px");
				pnlAdd_ons.add(btnMenu, "cell 0 5, right, width 65px");
				pnlAdd_ons.add(btnExt, "cell 0 5, right, width 65px");

	 }
	private void totalRb() {
		String query = "SELECT COUNT(*) FROM survey_table" + date2;
		
		try {
			db.statement = db.conn.createStatement();
			db.rs = db.statement.executeQuery(query);
			
			if(db.rs.next()) {
				trb = db.rs.getString(1);
				
				lblTRB.setText(trb);
			}
			
			db.ps.execute();
			
		} catch(Exception e) {
		}
	}
	private void clientVisitB() {
		 String query1 = "SELECT COUNT(office_visited) FROM survey_table WHERE office_visited = 'GPTA Office'" + date1;
		 String query2 = "SELECT COUNT(office_visited) FROM survey_table WHERE office_visited = 'Guidance Office'" + date1;
		 String query3 = "SELECT COUNT(office_visited) FROM survey_table WHERE office_visited = 'Principal''s Office'" + date1;
		 String query4 = "SELECT COUNT(office_visited) FROM survey_table WHERE office_visited = 'Registrar''s Office'" + date1;
		 String query5 = "SELECT COUNT(office_visited) FROM survey_table WHERE office_visited = 'Others'" + date1;
		 
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(query1);
			 
			 if(db.rs.next()) {
				 officeVisitGpta = db.rs.getInt(1);
			 }
			 
			 db.ps.execute();
			 
		 } catch(Exception e) {
		 }
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(query2);
			 
			 if(db.rs.next()) {
				 officeVisitGuidance = db.rs.getInt(1);
			 }
			 
			 db.ps.execute();
			 
		 } catch(Exception e) {
		 }
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(query3);
			 
			 if(db.rs.next()) {
				 officeVisitPrincipal = db.rs.getInt(1);
			 }
			 
			 db.ps.execute();
			 
		 } catch(Exception e) {
		 }
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(query4);
			 
			 if(db.rs.next()) {
				 officeVisitRegistrar = db.rs.getInt(1);
			 }
			 
			 db.ps.execute();
			 
		 } catch(Exception e) {
		 }
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(query5);
			 
			 if(db.rs.next()) {
				 officeVisitOthers = db.rs.getInt(1);
			 }
			 
			 db.ps.execute();
			 
		 } catch(Exception e) {
		 }
	 }
	private CategoryDataset createDataset() {
		 clientVisitB();
		
	      final String offices = "OFFICES";  
	     
	      final String gpta = "GPTA Office";        
	      final String guidance = "Guidance Office";        
	      final String principal = "Principal's Office";        
	      final String registrar = "Registrar's Office"; 
	      final String others = "Others"; 
	      
	      final DefaultCategoryDataset dataset = new DefaultCategoryDataset();  

	      dataset.setValue(officeVisitGpta , offices , gpta );        
	      dataset.addValue(officeVisitGuidance , offices , guidance );        
	      dataset.addValue(officeVisitPrincipal , offices , principal ); 
	      dataset.addValue(officeVisitRegistrar , offices , registrar ); 
	      dataset.addValue(officeVisitOthers , offices , others ); 

	      return dataset; 
	}
	private void totalReactB() {
		 String query1 = "SELECT COUNT(q1) FROM survey_table WHERE q1 = 'Very Dissatisfied'" + date1;
		 String query2 = "SELECT COUNT(q2) FROM survey_table WHERE q2 = 'Very Dissatisfied'" + date1;
		 String query3 = "SELECT COUNT(q3) FROM survey_table WHERE q3 = 'Very Dissatisfied'" + date1;
		 
		 String query4 = "SELECT COUNT(q1) FROM survey_table WHERE q1 = 'Dissatisfied'" + date1;
		 String query5 = "SELECT COUNT(q2) FROM survey_table WHERE q2 = 'Dissatisfied'" + date1;
		 String query6 = "SELECT COUNT(q3) FROM survey_table WHERE q3 = 'Dissatisfied'" + date1;
		 
		 String query7 = "SELECT COUNT(q1) FROM survey_table WHERE q1 = 'Neutral'" + date1;
		 String query8 = "SELECT COUNT(q2) FROM survey_table WHERE q2 = 'Neutral'" + date1;
		 String query9 = "SELECT COUNT(q3) FROM survey_table WHERE q3 = 'Neutral'" + date1;
		 
		 String query10 = "SELECT COUNT(q1) FROM survey_table WHERE q1 = 'Satisfied'" + date1;
		 String query11 = "SELECT COUNT(q2) FROM survey_table WHERE q2 = 'Satisfied'" + date1;
		 String query12 = "SELECT COUNT(q3) FROM survey_table WHERE q3 = 'Satisfied'" + date1;
		 
		 String query13 = "SELECT COUNT(q1) FROM survey_table WHERE q1 = 'Very Satisfied'" + date1;
		 String query14 = "SELECT COUNT(q2) FROM survey_table WHERE q2 = 'Very Satisfied'" + date1;
		 String query15 = "SELECT COUNT(q3) FROM survey_table WHERE q3 = 'Very Satisfied'" + date1;
		 
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(query1);
			 
			 if(db.rs.next()) {
				 totalReactVd1 = db.rs.getInt(1);
			 }
			 
			 db.ps.execute();
			 
		 } catch(Exception e) {
		 }
		 
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(query2);
			 
			 if(db.rs.next()) {
				 totalReactVd2 = db.rs.getInt(1);
			 }
			 
			 db.ps.execute();
			 
		 } catch(Exception e) {
		 }
		 
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(query3);
			 
			 if(db.rs.next()) {
				 totalReactVd3 = db.rs.getInt(1);
				 
				 totalReactVd = totalReactVd1 + totalReactVd2 + totalReactVd3;
			 }
			 
			 db.ps.execute();
			 
		 } catch(Exception e) {
		 }
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(query4);
			 
			 if(db.rs.next()) {
				 totalReactD1 = db.rs.getInt(1);
			 }
			 
			 db.ps.execute();
			 
		 } catch(Exception e) {
		 }
		 
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(query5);
			 
			 if(db.rs.next()) {
				 totalReactD2 = db.rs.getInt(1);
			 }
			 
			 db.ps.execute();
			 
		 } catch(Exception e) {
		 }
		 
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(query6);
			 
			 if(db.rs.next()) {
				 totalReactD3 = db.rs.getInt(1);
				 
				 totalReactD = totalReactD1 + totalReactD2 + totalReactD3;
			 }
			 
			 db.ps.execute();
			 
		 } catch(Exception e) {
		 }
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(query7);
			 
			 if(db.rs.next()) {
				 totalReactN1 = db.rs.getInt(1);
			 }
			 
			 db.ps.execute();
			 
		 } catch(Exception e) {
		 }
		 
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(query8);
			 
			 if(db.rs.next()) {
				 totalReactN2 = db.rs.getInt(1);
			 }
			 
			 db.ps.execute();
			 
		 } catch(Exception e) {
		 }
		 
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(query9);
			 
			 if(db.rs.next()) {
				 totalReactN3 = db.rs.getInt(1);
				 
				 totalReactN = totalReactN1 + totalReactN2 + totalReactN3;
			 }
			 
			 db.ps.execute();
			 
		 } catch(Exception e) {
		 }
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(query10);
			 
			 if(db.rs.next()) {
				 totalReactS1 = db.rs.getInt(1);
			 }
			 
			 db.ps.execute();
			 
		 } catch(Exception e) {
		 }
		 
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(query11);
			 
			 if(db.rs.next()) {
				 totalReactS2 = db.rs.getInt(1);
			 }
			 
			 db.ps.execute();
			 
		 } catch(Exception e) {
		 }
		 
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(query12);
			 
			 if(db.rs.next()) {
				 totalReactS3 = db.rs.getInt(1);
				 
				 totalReactS = totalReactS1 + totalReactS2 + totalReactS3;
			 }
			 
			 db.ps.execute();
			 
		 } catch(Exception e) {
		 }
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(query13);
			 
			 if(db.rs.next()) {
				 totalReactVs1 = db.rs.getInt(1);
			 }
			 
			 db.ps.execute();
			 
		 } catch(Exception e) {
		 }
		 
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(query14);
			 
			 if(db.rs.next()) {
				 totalReactVs2 = db.rs.getInt(1);
			 }
			 
			 db.ps.execute();
			 
		 } catch(Exception e) {
		 }
		 
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(query15);
			 
			 if(db.rs.next()) {
				 totalReactVs3 = db.rs.getInt(1);
				 
				 totalReactVs = totalReactVs1 + totalReactVs2 + totalReactVs3;
			 }
			 
			 db.ps.execute();
			 
		 } catch(Exception e) {
		 }
	 }   
	private PieDataset createDatasetTrb() {
		 DefaultPieDataset datasetTrb = new DefaultPieDataset( );
		 
		 totalReactB();
		 
	      datasetTrb.setValue( "Very Dissatisfied" , totalReactVd);  
	      datasetTrb.setValue( "Dissatisfied" , totalReactD);   
	      datasetTrb.setValue( "Neutral" , totalReactN);    
	      datasetTrb.setValue( "Satisfied" , totalReactS);  
	      datasetTrb.setValue( "Very Satisfied" , totalReactVs);
	      
	      return datasetTrb;     
	}
	private void reactions() {
		String queryVd1 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
		 		+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Very Dissatisfied' AND office_visited = 'GPTA Office'" + date1 + ") AS q1, "
		 		+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Very Dissatisfied' AND office_visited = 'GPTA Office'" + date1 + ") AS q2, "
		 		+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Very Dissatisfied' AND office_visited = 'GPTA Office'" + date1 + ") AS q3";
		String queryVd2 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
			 	+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Very Dissatisfied' AND office_visited = 'Guidance Office'" + date1 + ") AS q1, "
			 	+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Very Dissatisfied' AND office_visited = 'Guidance Office'" + date1 + ") AS q2, "
			 	+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Very Dissatisfied' AND office_visited = 'Guidance Office'" + date1 + ") AS q3";
		String queryVd3 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
				+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Very Dissatisfied' AND office_visited = 'Principal''s Office'" + date1 + ") AS q1, "
				+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Very Dissatisfied' AND office_visited = 'Principal''s Office'" + date1 + ") AS q2, "
				+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Very Dissatisfied' AND office_visited = 'Principal''s Office'" + date1 + ") AS q3";
		String queryVd4 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
				+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Very Dissatisfied' AND office_visited = 'Registrar''s Office'" + date1 + ") AS q1, "
				+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Very Dissatisfied' AND office_visited = 'Registrar''s Office'" + date1 + ") AS q2, "
				+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Very Dissatisfied' AND office_visited = 'Registrar''s Office'" + date1 + ") AS q3";
		String queryVd5 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
				+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Very Dissatisfied' AND office_visited = 'Others'" + date1 + ") AS q1, "
				+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Very Dissatisfied' AND office_visited = 'Others'" + date1 + ") AS q2, "
				+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Very Dissatisfied' AND office_visited = 'Others'" + date1 + ") AS q3";
		 
		String queryD1 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
				+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Dissatisfied' AND office_visited = 'GPTA Office'" + date1 + ") AS q1, "
			 	+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Dissatisfied' AND office_visited = 'GPTA Office'" + date1 + ") AS q2, "
			 	+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Dissatisfied' AND office_visited = 'GPTA Office'" + date1 + ") AS q3";
		String queryD2 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
				+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Dissatisfied' AND office_visited = 'Guidance Office'" + date1 + ") AS q1, "
				+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Dissatisfied' AND office_visited = 'Guidance Office'" + date1 + ") AS q2, "
				+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Dissatisfied' AND office_visited = 'Guidance Office'" + date1 + ") AS q3";
		String queryD3 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
				+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Dissatisfied' AND office_visited = 'Principal''s Office'" + date1 + ") AS q1, "
				+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Dissatisfied' AND office_visited = 'Principal''s Office'" + date1 + ") AS q2, "
				+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Dissatisfied' AND office_visited = 'Principal''s Office'" + date1 + ") AS q3";
		String queryD4 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
				+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Dissatisfied' AND office_visited = 'Registrar''s Office'" + date1 + ") AS q1, "
				+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Dissatisfied' AND office_visited = 'Registrar''s Office'" + date1 + ") AS q2, "
				+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Dissatisfied' AND office_visited = 'Registrar''s Office'" + date1 + ") AS q3";
		String queryD5 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
				+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Dissatisfied' AND office_visited = 'Others'" + date1 + ") AS q1, "
				+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Dissatisfied' AND office_visited = 'Others'" + date1 + ") AS q2, "
				+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Dissatisfied' AND office_visited = 'Others'" + date1 + ") AS q3";
		
		String queryN1 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
				+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Neutral' AND office_visited = 'GPTA Office'" + date1 + ") AS q1, "
			 	+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Neutral' AND office_visited = 'GPTA Office'" + date1 + ") AS q2, "
			 	+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Neutral' AND office_visited = 'GPTA Office'" + date1 + ") AS q3";
		String queryN2 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
				+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Neutral' AND office_visited = 'Guidance Office'" + date1 + ") AS q1, "
				+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Neutral' AND office_visited = 'Guidance Office'" + date1 + ") AS q2, "
				+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Neutral' AND office_visited = 'Guidance Office'" + date1 + ") AS q3";
		String queryN3 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
				+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Neutral' AND office_visited = 'Principal''s Office'" + date1 + ") AS q1, "
				+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Neutral' AND office_visited = 'Principal''s Office'" + date1 + ") AS q2, "
				+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Neutral' AND office_visited = 'Principal''s Office'" + date1 + ") AS q3";
		String queryN4 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
				+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Neutral' AND office_visited = 'Registrar''s Office'" + date1 + ") AS q1, "
				+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Neutral' AND office_visited = 'Registrar''s Office'" + date1 + ") AS q2, "
				+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Neutral' AND office_visited = 'Registrar''s Office'" + date1 + ") AS q3";
		String queryN5 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
				+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Neutral' AND office_visited = 'Others'" + date1 + ") AS q1, "
				+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Neutral' AND office_visited = 'Others'" + date1 + ") AS q2, "
				+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Neutral' AND office_visited = 'Others'" + date1 + ") AS q3";
		
		String queryS1 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
				+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Satisfied' AND office_visited = 'GPTA Office'" + date1 + ") AS q1, "
			 	+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Satisfied' AND office_visited = 'GPTA Office'" + date1 + ") AS q2, "
			 	+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Satisfied' AND office_visited = 'GPTA Office'" + date1 + ") AS q3";
		String queryS2 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
				+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Satisfied' AND office_visited = 'Guidance Office'" + date1 + ") AS q1, "
				+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Satisfied' AND office_visited = 'Guidance Office'" + date1 + ") AS q2, "
				+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Satisfied' AND office_visited = 'Guidance Office'" + date1 + ") AS q3";
		String queryS3 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
				+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Satisfied' AND office_visited = 'Principal''s Office'" + date1 + ") AS q1, "
				+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Satisfied' AND office_visited = 'Principal''s Office'" + date1 + ") AS q2, "
				+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Satisfied' AND office_visited = 'Principal''s Office'" + date1 + ") AS q3";
		String queryS4 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
				+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Satisfied' AND office_visited = 'Registrar''s Office'" + date1 + ") AS q1, "
				+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Satisfied' AND office_visited = 'Registrar''s Office'" + date1 + ") AS q2, "
				+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Satisfied' AND office_visited = 'Registrar''s Office'" + date1 + ") AS q3";
		String queryS5 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
				+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Satisfied' AND office_visited = 'Others'" + date1 + ") AS q1, "
				+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Satisfied' AND office_visited = 'Others'" + date1 + ") AS q2, "
				+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Satisfied' AND office_visited = 'Others'" + date1 + ") AS q3";
		
		String queryVs1 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
		 		+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Very Satisfied' AND office_visited = 'GPTA Office'" + date1 + ") AS q1, "
		 		+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Very Satisfied' AND office_visited = 'GPTA Office'" + date1 + ") AS q2, "
		 		+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Very Satisfied' AND office_visited = 'GPTA Office'" + date1 + ") AS q3";
		String queryVs2 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
			 	+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Very Satisfied' AND office_visited = 'Guidance Office'" + date1 + ") AS q1, "
			 	+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Very Satisfied' AND office_visited = 'Guidance Office'" + date1 + ") AS q2, "
			 	+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Very Satisfied' AND office_visited = 'Guidance Office'" + date1 + ") AS q3";
		String queryVs3 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
				+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Very Satisfied' AND office_visited = 'Principal''s Office'" + date1 + ") AS q1, "
				+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Very Satisfied' AND office_visited = 'Principal''s Office'" + date1 + ") AS q2, "
				+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Very Satisfied' AND office_visited = 'Principal''s Office'" + date1 + ") AS q3";
		String queryVs4 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
				+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Very Satisfied' AND office_visited = 'Registrar''s Office'" + date1 + ") AS q1, "
				+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Very Satisfied' AND office_visited = 'Registrar''s Office'" + date1 + ") AS q2, "
				+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Very Satisfied' AND office_visited = 'Registrar''s Office'" + date1 + ") AS q3";
		String queryVs5 = "SELECT q1.qq1, q2.qq2, q3.qq3 FROM "
				+ "(SELECT COUNT(q1) AS qq1 FROM survey_table WHERE q1 = 'Very Satisfied' AND office_visited = 'Others'" + date1 + ") AS q1, "
				+ "(SELECT COUNT(q2) AS qq2 FROM survey_table WHERE q2 = 'Very Satisfied' AND office_visited = 'Others'" + date1 + ") AS q2, "
				+ "(SELECT COUNT(q3) AS qq3 FROM survey_table WHERE q3 = 'Very Satisfied' AND office_visited = 'Others'" + date1 + ") AS q3";
		 
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryVd1);
			 
			 if(db.rs.next()) {
				 physicalVdGpta = db.rs.getInt(1);
				 servicesVdGpta = db.rs.getInt(2);
				 personnelVdGpta = db.rs.getInt(3);
				 
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 } 
		 
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryVd2);
			 
			 if(db.rs.next()) {
				 physicalVdGuidance = db.rs.getInt(1);
				 servicesVdGuidance = db.rs.getInt(2);
				 personnelVdGuidance = db.rs.getInt(3);
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 }
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryVd3);
			 
			 if(db.rs.next()) {
				 physicalVdPrincipal = db.rs.getInt(1);
				 servicesVdPrincipal = db.rs.getInt(2);
				 personnelVdPrincipal = db.rs.getInt(3);
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 } 
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryVd4);
			 
			 if(db.rs.next()) {
				 physicalVdRegistrar = db.rs.getInt(1);
				 servicesVdRegistrar = db.rs.getInt(2);
				 personnelVdRegistrar = db.rs.getInt(3);
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 }
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryVd5);
			 
			 if(db.rs.next()) {
				 physicalVdOthers = db.rs.getInt(1);
				 servicesVdOthers = db.rs.getInt(2);
				 personnelVdOthers = db.rs.getInt(3);
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 }
		 
		 //-----------------------------------------------------
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryD1);
			 
			 if(db.rs.next()) {
				 physicalDGpta = db.rs.getInt(1);
				 servicesDGpta = db.rs.getInt(2);
				 personnelDGpta = db.rs.getInt(3);
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 } 
		 
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryD2);
			 
			 if(db.rs.next()) {
				 physicalDGuidance = db.rs.getInt(1);
				 servicesDGuidance = db.rs.getInt(2);
				 personnelDGuidance = db.rs.getInt(3);
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 }
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryD3);
			 
			 if(db.rs.next()) {
				 physicalDPrincipal = db.rs.getInt(1);
				 servicesDPrincipal = db.rs.getInt(2);
				 personnelDPrincipal = db.rs.getInt(3);
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 } 
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryD4);
			 
			 if(db.rs.next()) {
				 physicalDRegistrar = db.rs.getInt(1);
				 servicesDRegistrar = db.rs.getInt(2);
				 personnelDRegistrar = db.rs.getInt(3);
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 }
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryD5);
			 
			 if(db.rs.next()) {
				 physicalDOthers = db.rs.getInt(1);
				 servicesDOthers = db.rs.getInt(2);
				 personnelDOthers = db.rs.getInt(3);
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 }
		 
		//-----------------------------------------------------
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryN1);
			 
			 if(db.rs.next()) {
				 physicalNGpta = db.rs.getInt(1);
				 servicesNGpta = db.rs.getInt(2);
				 personnelNGpta = db.rs.getInt(3);
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 } 
		 
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryN2);
			 
			 if(db.rs.next()) {
				 physicalNGuidance = db.rs.getInt(1);
				 servicesNGuidance = db.rs.getInt(2);
				 personnelNGuidance = db.rs.getInt(3);
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 }
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryN3);
			 
			 if(db.rs.next()) {
				 physicalNPrincipal = db.rs.getInt(1);
				 servicesNPrincipal = db.rs.getInt(2);
				 personnelNPrincipal = db.rs.getInt(3);
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 } 
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryN4);
			 
			 if(db.rs.next()) {
				 physicalNRegistrar = db.rs.getInt(1);
				 servicesNRegistrar = db.rs.getInt(2);
				 personnelNRegistrar = db.rs.getInt(3);
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 }
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryN5);
			 
			 if(db.rs.next()) {
				 physicalNOthers = db.rs.getInt(1);
				 servicesNOthers = db.rs.getInt(2);
				 personnelNOthers = db.rs.getInt(3);
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 }
		 
		//-----------------------------------------------------
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryS1);
			 
			 if(db.rs.next()) {
				 physicalSGpta = db.rs.getInt(1);
				 servicesSGpta = db.rs.getInt(2);
				 personnelSGpta = db.rs.getInt(3);
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 } 
		 
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryS2);
			 
			 if(db.rs.next()) {
				 physicalSGuidance = db.rs.getInt(1);
				 servicesSGuidance = db.rs.getInt(2);
				 personnelSGuidance = db.rs.getInt(3);
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 }
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryS3);
			 
			 if(db.rs.next()) {
				 physicalSPrincipal = db.rs.getInt(1);
				 servicesSPrincipal = db.rs.getInt(2);
				 personnelSPrincipal = db.rs.getInt(3);
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 } 
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryS4);
			 
			 if(db.rs.next()) {
				 physicalSRegistrar = db.rs.getInt(1);
				 servicesSRegistrar = db.rs.getInt(2);
				 personnelSRegistrar = db.rs.getInt(3);
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 }
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryS5);
			 
			 if(db.rs.next()) {
				 physicalSOthers = db.rs.getInt(1);
				 servicesSOthers = db.rs.getInt(2);
				 personnelSOthers = db.rs.getInt(3);
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 }
		 
		//-----------------------------------------------------
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryVs1);
			 
			 if(db.rs.next()) {
				 physicalVsGpta = db.rs.getInt(1);
				 servicesVsGpta = db.rs.getInt(2);
				 personnelVsGpta = db.rs.getInt(3);
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 } 
		 
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryVs2);
			 
			 if(db.rs.next()) {
				 physicalVsGuidance = db.rs.getInt(1);
				 servicesVsGuidance = db.rs.getInt(2);
				 personnelVsGuidance = db.rs.getInt(3);
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 }
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryVs3);
			 
			 if(db.rs.next()) {
				 physicalVsPrincipal = db.rs.getInt(1);
				 servicesVsPrincipal = db.rs.getInt(2);
				 personnelVsPrincipal = db.rs.getInt(3);
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 } 
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryVs4);
			 
			 if(db.rs.next()) {
				 physicalVsRegistrar = db.rs.getInt(1);
				 servicesVsRegistrar = db.rs.getInt(2);
				 personnelVsRegistrar = db.rs.getInt(3);
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 }
		 try {
			 db.statement = db.conn.createStatement();
			 db.rs = db.statement.executeQuery(queryVs5);
			 
			 if(db.rs.next()) {
				 physicalVsOthers = db.rs.getInt(1);
				 servicesVsOthers = db.rs.getInt(2);
				 personnelVsOthers = db.rs.getInt(3);
			 }
			 db.ps.execute();
		 }catch(Exception e) {
		 }
	}
	private CategoryDataset createDatasetPhysical() {
		
		reactions();
		
	      final String vd = "Very Dissatisfied";        
	      final String d = "Dissatisfied";        
	      final String n = "Neutral";  
	      final String s = "Satisfied"; 
	      final String vs = "Very Satisfied"; 
	      
	      final String gpta = "GPTA Office";        
	      final String guidance = "Guidance Office";        
	      final String principal = "Principal's Office";        
	      final String registrar = "Registrar's Office"; 
	      final String others = "Others"; 
	      
	      DefaultCategoryDataset datasetPhysical = new DefaultCategoryDataset();  

	      datasetPhysical.addValue(physicalVdGpta, vd, gpta );        
	      datasetPhysical.addValue(physicalVdGuidance, vd, guidance );        
	      datasetPhysical.addValue(physicalVdPrincipal, vd, principal ); 
	      datasetPhysical.addValue(physicalVdRegistrar, vd, registrar ); 
	      datasetPhysical.addValue(physicalVdOthers, vd, others ); 

	      datasetPhysical.addValue(physicalDGpta, d, gpta );        
	      datasetPhysical.addValue(physicalDGuidance, d, guidance );       
	      datasetPhysical.addValue(physicalDPrincipal, d, principal );        
	      datasetPhysical.addValue(physicalDRegistrar, d, registrar );
	      datasetPhysical.addValue(physicalDOthers, d, others ); 

	      datasetPhysical.addValue(physicalNGpta, n, gpta );        
	      datasetPhysical.addValue(physicalNGuidance, n, guidance );        
	      datasetPhysical.addValue(physicalNPrincipal, n, principal );        
	      datasetPhysical.addValue(physicalNRegistrar, n, registrar );  
	      datasetPhysical.addValue(physicalNOthers, n, others ); 
	      
	      datasetPhysical.addValue(physicalSGpta, s, gpta );        
	      datasetPhysical.addValue(physicalSGuidance, s, guidance );        
	      datasetPhysical.addValue(physicalSPrincipal, s, principal );        
	      datasetPhysical.addValue(physicalSRegistrar, s, registrar );  
	      datasetPhysical.addValue(physicalSOthers, s, others ); 
	      
	      datasetPhysical.addValue(physicalVsGpta, vs, gpta );        
	      datasetPhysical.addValue(physicalVsGuidance, vs, guidance );        
	      datasetPhysical.addValue(physicalVsPrincipal, vs, principal );        
	      datasetPhysical.addValue(physicalVsRegistrar, vs, registrar );  
	      datasetPhysical.addValue(physicalVsOthers, vs, others ); 
	      
	      return datasetPhysical; 
	}
	private CategoryDataset createDatasetServices() {
		
		reactions();
		
	      final String vd = "Very Dissatisfied";        
	      final String d = "Dissatisfied";        
	      final String n = "Neutral";  
	      final String s = "Satisfied"; 
	      final String vs = "Very Satisfied"; 
	      
	      final String gpta = "GPTA Office";        
	      final String guidance = "Guidance Office";        
	      final String principal = "Principal's Office";        
	      final String registrar = "Registrar's Office"; 
	      final String others = "Others"; 
	      
	      final DefaultCategoryDataset datasetServices = new DefaultCategoryDataset();  

	      datasetServices.addValue(servicesVdGpta, vd, gpta );        
	      datasetServices.addValue(servicesVdGuidance, vd, guidance );        
	      datasetServices.addValue(servicesVdPrincipal, vd, principal ); 
	      datasetServices.addValue(servicesVdRegistrar, vd, registrar ); 
	      datasetServices.addValue(servicesVdOthers, vd, others ); 

	      datasetServices.addValue(servicesDGpta, d, gpta );        
	      datasetServices.addValue(servicesDGuidance, d, guidance );       
	      datasetServices.addValue(servicesDPrincipal, d, principal );        
	      datasetServices.addValue(servicesDRegistrar, d, registrar );
	      datasetServices.addValue(servicesDOthers, d, others ); 

	      datasetServices.addValue(servicesNGpta, n, gpta );        
	      datasetServices.addValue(servicesNGuidance, n, guidance );        
	      datasetServices.addValue(servicesNPrincipal, n, principal );        
	      datasetServices.addValue(servicesNRegistrar, n, registrar );  
	      datasetServices.addValue(servicesNOthers, n, others ); 
	      
	      datasetServices.addValue(servicesSGpta, s, gpta );        
	      datasetServices.addValue(servicesSGuidance, s, guidance );        
	      datasetServices.addValue(servicesSPrincipal, s, principal );        
	      datasetServices.addValue(servicesSRegistrar, s, registrar );  
	      datasetServices.addValue(servicesSOthers, s, others ); 
	      
	      datasetServices.addValue(servicesVsGpta, vs, gpta );        
	      datasetServices.addValue(servicesVsGuidance, vs, guidance );        
	      datasetServices.addValue(servicesVsPrincipal, vs, principal );        
	      datasetServices.addValue(servicesVsRegistrar, vs, registrar );  
	      datasetServices.addValue(servicesVsOthers, vs, others ); 
	      
	      return datasetServices; 
	 }
	private CategoryDataset createDatasetPersonnel() {
		
		reactions();
		
		 final String vd = "Very Dissatisfied";        
		 final String d = "Dissatisfied";        
		 final String n = "Neutral";  
		 final String s = "Satisfied"; 
		 final String vs = "Very Satisfied"; 
		 
		 final String gpta = "GPTA Office";        
		 final String guidance = "Guidance Office";        
		 final String principal = "Principal's Office";        
		 final String registrar = "Registrar's Office"; 
		 final String others = "Others"; 
		 
		 final DefaultCategoryDataset datasetPersonnel = new DefaultCategoryDataset();  
		 
		  datasetPersonnel.addValue(personnelVdGpta, vd, gpta );        
	      datasetPersonnel.addValue(personnelVdGuidance, vd, guidance );        
	      datasetPersonnel.addValue(personnelVdPrincipal, vd, principal ); 
	      datasetPersonnel.addValue(personnelVdRegistrar, vd, registrar ); 
	      datasetPersonnel.addValue(personnelVdOthers, vd, others ); 

	      datasetPersonnel.addValue(personnelDGpta, d, gpta );        
	      datasetPersonnel.addValue(personnelDGuidance, d, guidance );       
	      datasetPersonnel.addValue(personnelDPrincipal, d, principal );        
	      datasetPersonnel.addValue(personnelDRegistrar, d, registrar );
	      datasetPersonnel.addValue(personnelDOthers, d, others ); 

	      datasetPersonnel.addValue(personnelNGpta, n, gpta );        
	      datasetPersonnel.addValue(personnelNGuidance, n, guidance );        
	      datasetPersonnel.addValue(personnelNPrincipal, n, principal );        
	      datasetPersonnel.addValue(personnelNRegistrar, n, registrar );  
	      datasetPersonnel.addValue(personnelNOthers, n, others ); 
	      
	      datasetPersonnel.addValue(personnelSGpta, s, gpta );        
	      datasetPersonnel.addValue(personnelSGuidance, s, guidance );        
	      datasetPersonnel.addValue(personnelSPrincipal, s, principal );        
	      datasetPersonnel.addValue(personnelSRegistrar, s, registrar );  
	      datasetPersonnel.addValue(personnelSOthers, s, others ); 
	      
	      datasetPersonnel.addValue(personnelVsGpta, vs, gpta );        
	      datasetPersonnel.addValue(personnelVsGuidance, vs, guidance );        
	      datasetPersonnel.addValue(personnelVsPrincipal, vs, principal );        
	      datasetPersonnel.addValue(personnelVsRegistrar, vs, registrar );  
	      datasetPersonnel.addValue(personnelVsOthers, vs, others ); 
	      
		 
		 return datasetPersonnel; 
	 }
	private void displayInfo() {
		model.getDataVector().clear();
		tblAdd_ons.revalidate();
		String query = "SELECT name, office_visited, date_of_visit, time_started, time_ended, purpose_of_visit FROM survey_table" + date2;
		
		try {
			db.statement = db.conn.createStatement();
			db.rs = db.statement.executeQuery(query);
			
			while(db.rs.next()) {
				String[] data = {db.rs.getString("name"),db.rs.getString("office_visited"),
						db.rs.getString("date_of_visit"),db.rs.getString("time_started"),
						db.rs.getString("time_ended"),db.rs.getString("purpose_of_visit")};
				model.addRow(data);
				}
			
		} catch(Exception e) {
		}
	}
	private void search(String search) {
		model.getDataVector().clear();
		tblAdd_ons.revalidate();
		tblAdd_ons.repaint();
			
			String query = "Select * from survey_table WHERE name = '" + search + "' " + 
			"OR office_visited = '" + search + "' " + 
			"OR time_started = '" + search + "' " + 
			"OR time_ended = '" + search + "' " + 
			"OR purpose_of_visit = '" + search + "' " + date1;
			
			try {
		
				db.rs = db.statement.executeQuery(query);
				
				
				while(db.rs.next()) {
					String[] data = {db.rs.getString("name"),db.rs.getString("office_visited"),
							db.rs.getString("date_of_visit"),db.rs.getString("time_started"),
							db.rs.getString("time_ended"),db.rs.getString("purpose_of_visit")};
					model.addRow(data);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	private void displayComment() {
		modelComments.getDataVector().clear();
		tblComments.revalidate();
		String query = "SELECT comments FROM survey_table WHERE comments IS NOT NULL AND comments != ' ' AND comments != ''" + date1;
		
		try {
			db.statement = db.conn.createStatement();
			db.rs = db.statement.executeQuery(query);
			
			while(db.rs.next()) {
				String[] data = {db.rs.getString("comments")};
				modelComments.addRow(data);
				}
			
		} catch(Exception e) {
		}
	}
	private void deleteNull() {
		String query = "DELETE FROM survey_table WHERE office_visited IS NULL OR time_started IS NULL "
				+ "OR date_of_visit IS NULL OR time_ended IS NULL OR q1 IS NULL OR q2 IS NULL OR q3 IS NULL";
		
		try {
			
			db.statement = db.conn.createStatement();
			db.statement.executeUpdate(query);
			
		} catch(Exception e) {
		}
	}
	private void deleteAllData() {
		String query1 = "DROP TABLE survey_table";
		String query2 = "CREATE TABLE survey_table(name varchar(30), office_visited varchar(20), "
				+ "time_started varchar(6), date_of_visit date, time_ended varchar(6), "
				+ "purpose_of_visit varchar(100), q1 varchar(20), q2 varchar(20), q3 varchar(20), "
				+ "comments varchar(200), id int AUTO_INCREMENT PRIMARY KEY)";
		
		try {
			db.statement = db.conn.createStatement();
			db.statement.executeUpdate(query1);			
		} catch(Exception e) {
		}
		
		try {
			db.statement = db.conn.createStatement();
			db.statement.executeUpdate(query2);			
		} catch(Exception e) {
		}
	}
	private void deleteCurrentData() {
		String query = "DELETE FROM survey_table" + date2;
		
		try {
			db.statement = db.conn.createStatement();
			db.statement.executeUpdate(query);			
		} catch(Exception e) {
		}
	}
	public void removeComponents() {
		Component[] cList = pnlScreen.getComponents();
		
		for(Component c : cList) {
			pnlScreen.remove(c);
			
		}
		
	}
private void printRatings(JPanel pnl) {
		
		PrinterJob pj = PrinterJob.getPrinterJob();
		PageFormat preformat = pj.defaultPage();
		preformat.setOrientation(PageFormat.PORTRAIT);
		PageFormat postformat = pj.pageDialog(preformat);
		
		pj.setJobName("Print Ratings");
		pj.setPrintable(new Printable() {
			
			@Override
			public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
				
				 if (pageIndex > 0) {
			            return Printable.NO_SUCH_PAGE;
			        }

			        // get the bounds of the component
			        Dimension dim = pnl.getSize();
			        double cHeight = dim.getHeight();
			        double cWidth = dim.getWidth();

			        // get the bounds of the printable area
			        double pHeight = pageFormat.getImageableHeight();
			        double pWidth = pageFormat.getImageableWidth();

			        double pXStart = pageFormat.getImageableX();
			        double pYStart = pageFormat.getImageableY();

			        double xRatio = pWidth / cWidth;
			        double yRatio = pHeight / cHeight;


			        Graphics2D g2 = (Graphics2D) graphics;
			        g2.translate(pXStart, pYStart);
			        g2.scale(xRatio, yRatio);
			        pnl.paint(g2);

			        return Printable.PAGE_EXISTS;
			}
		});
		boolean returningResult = pj.printDialog();
		
		if(returningResult) {
			try {
				pj.print();
			}catch(PrinterException pe) {
				JOptionPane.showMessageDialog(this, "Print Error: " + pe.getMessage());
			}	
		}
	}
	public void refresh() {

		totalRb();
		clientVisitB();
		totalReactB();
		reactions();
		search(txtSearch.getText());
		displayInfo();
		displayComment();
		
		createDataset();
		createDatasetTrb();
		createDatasetPhysical();
		createDatasetServices();
		createDatasetPersonnel();
		
		dataset = createDataset();
		cplot.setDataset(dataset);
		pnlbarGraph.repaint();
		
		datasetTrb = createDatasetTrb();
		plotTrb.setDataset(datasetTrb);
		pnlTrb.repaint();
		
		datasetPhysical = createDatasetPhysical();
		cplotPhy.setDataset(datasetPhysical);
		pnlPhysical.repaint();
		
		datasetServices = createDatasetServices();
		cplotSer.setDataset(datasetServices);
		pnlServices.repaint();
		
		datasetPersonnel = createDatasetPersonnel();
		cplotPer.setDataset(datasetPersonnel);
		pnlPersonnel.repaint();
	}
}
