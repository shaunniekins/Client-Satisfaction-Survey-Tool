import com.toedter.calendar.JDateChooser;
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
import net.miginfocom.swing.MigLayout;
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

public class RatingsWindow extends JFrame {

  // Constants for UI elements and data categories
  private static final Font HEADER_FONT =
      new Font(Constants.FONT_FAMILY, Font.BOLD, 40);
  private static final Font SECTION_HEADER_FONT =
      new Font(Constants.FONT_FAMILY, Font.BOLD, Constants.FONT_SIZE_LARGE);
  private static final Font LABEL_FONT =
      new Font(Constants.FONT_FAMILY, Font.PLAIN, 13);
  private static final Font TEXT_FONT =
      new Font(Constants.FONT_FAMILY, Font.PLAIN, 16);
  private static final Font TABLE_HEADER_FONT =
      new Font(Constants.FONT_FAMILY, Font.BOLD, Constants.FONT_SIZE_SMALL);

  private static final String GPTA_OFFICE = "GPTA Office";
  private static final String GUIDANCE_OFFICE = "Guidance Office";
  private static final String PRINCIPALS_OFFICE = "Principal's Office";
  private static final String REGISTRARS_OFFICE = "Registrar's Office";
  private static final String OTHERS_OFFICE = "Others";

  private static final String OFFICES_CATEGORY = "OFFICES";
  private static final String REACTIONS_CATEGORY = "Reactions";

  // UI components
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
  JTextField txtSearch;
  JDateChooser ratingsDate;
  JTable tblAdd_ons;
  JTable tblComments;
  DefaultTableModel model, modelComments;
  JLabel lblTRB;

  // Chart datasets and plots
  CategoryDataset datasetPhysical;
  CategoryDataset datasetClientVisit;
  PieDataset datasetTotalReactions;
  CategoryDataset datasetServices;
  CategoryDataset datasetPersonnel;
  CategoryPlot clientVisitPlot, physicalPlot, servicesPlot, personnelPlot;
  PiePlot totalReactionsPlot;
  JFreeChart chartPhysical;

  // Database connection
  ConnectionWindow db = new ConnectionWindow();

  // Date range variables
  String startDate;
  LocalDate endDate;
  String dateFilterAnd = "";   // For 'AND' clauses in SQL
  String dateFilterWhere = ""; // For 'WHERE' clauses in SQL
  boolean isDateRangeSelected = false;

  // Data counters
  int officeVisitGpta, officeVisitGuidance, officeVisitPrincipal,
      officeVisitRegistrar, officeVisitOthers;
  int totalReactionsCount;
  int totalReactVd, totalReactD, totalReactN, totalReactS, totalReactVs;
  int physicalVdGpta, physicalVdGuidance, physicalVdPrincipal,
      physicalVdRegistrar, physicalVdOthers;
  int physicalDGpta, physicalDGuidance, physicalDPrincipal, physicalDRegistrar,
      physicalDOthers;
  int physicalNGpta, physicalNGuidance, physicalNPrincipal, physicalNRegistrar,
      physicalNOthers;
  int physicalSGpta, physicalSGuidance, physicalSPrincipal, physicalSRegistrar,
      physicalSOthers;
  int physicalVsGpta, physicalVsGuidance, physicalVsPrincipal,
      physicalVsRegistrar, physicalVsOthers;
  int servicesVdGpta, servicesVdGuidance, servicesVdPrincipal,
      servicesVdRegistrar, servicesVdOthers;
  int servicesDGpta, servicesDGuidance, servicesDPrincipal, servicesDRegistrar,
      servicesDOthers;
  int servicesNGpta, servicesNGuidance, servicesNPrincipal, servicesNRegistrar,
      servicesNOthers;
  int servicesSGpta, servicesSGuidance, servicesSPrincipal, servicesSRegistrar,
      servicesSOthers;
  int servicesVsGpta, servicesVsGuidance, servicesVsPrincipal,
      servicesVsRegistrar, servicesVsOthers;
  int personnelVdGpta, personnelVdGuidance, personnelVdPrincipal,
      personnelVdRegistrar, personnelVdOthers;
  int personnelDGpta, personnelDGuidance, personnelDPrincipal,
      personnelDRegistrar, personnelDOthers;
  int personnelNGpta, personnelNGuidance, personnelNPrincipal,
      personnelNRegistrar, personnelNOthers;
  int personnelSGpta, personnelSGuidance, personnelSPrincipal,
      personnelSRegistrar, personnelSOthers;
  int personnelVsGpta, personnelVsGuidance, personnelVsPrincipal,
      personnelVsRegistrar, personnelVsOthers;

  public RatingsWindow() {
    setLayout(new MigLayout("center, fill"));
    deleteNull(); // Ensure no null data affects calculations
    initializeUI();
    setupDateChooser();
    setupSearchField();
    setupDataTables();
    setupButtons();
    layoutComponents();
    refreshData(); // Initial data load
  }

  private void initializeUI() {
    pnlScreen.setLayout(new MigLayout("center"));
    pnlScreen.setBackground(Constants.SMOKE_GRAY);
    pnlScreen.setPreferredSize(new Dimension(1050, 1300));

    pnlLeft.setLayout(new MigLayout("center"));
    pnlLeft.setBackground(Constants.SMOKE_GRAY);
    pnlLeft.setPreferredSize(new Dimension(670, 430));

    JLabel lblHead = createHeaderLabel("RATINGS", "bcshs-logo.png");
    JLabel lblTop = new JLabel("Client Visitation Breakdown");
    lblTop.setFont(SECTION_HEADER_FONT);

    pnltcv.setLayout(new MigLayout("center"));
    pnltcv.setBackground(Constants.SMOKE_GRAY);
    pnltcv.setPreferredSize(new Dimension(250, 290));

    JLabel lblTotalClient = new JLabel("TOTAL CLIENT VISITATION");
    lblTotalClient.setForeground(Color.WHITE);
    lblTotalClient.setFont(LABEL_FONT);

    JPanel pnlCoverTc = new JPanel();
    pnlCoverTc.setBackground(Constants.BLUE_JEANS);

    JLabel lblClient =
        new JLabel(createImageIcon("blank-profile.png", 170, 170));

    lblTRB = new JLabel();
    lblTRB.setForeground(Constants.BLUE_JEANS);
    lblTRB.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, 50));

    datasetClientVisit = createClientVisitDataset();
    JFreeChart chartClientVisit =
        createBarChart(datasetClientVisit, PlotOrientation.HORIZONTAL);
    clientVisitPlot = (CategoryPlot)chartClientVisit.getPlot();
    ((BarRenderer)clientVisitPlot.getRenderer())
        .setSeriesPaint(0, Constants.BLUE_JEANS);
    pnlbarGraph = new ChartPanel(chartClientVisit);
    pnlbarGraph.setPreferredSize(new Dimension(430, 290));

    pnlRight.setLayout(new MigLayout("center"));
    pnlRight.setBackground(Constants.SMOKE_GRAY);
    pnlRight.setPreferredSize(new Dimension(430, 430));

    datasetTotalReactions = createTotalReactionsDataset();
    JFreeChart chartTotalReactions = createPieChart(datasetTotalReactions);
    totalReactionsPlot = (PiePlot)chartTotalReactions.getPlot();
    setPiePlotColors(totalReactionsPlot);
    pnlTrb = new ChartPanel(chartTotalReactions);
    pnlTrb.setPreferredSize(new Dimension(430, 350));

    pnlRowSec.setLayout(new MigLayout());
    pnlRowSec.setBackground(Constants.SMOKE_GRAY);
    pnlRowSec.setPreferredSize(new Dimension(670, 870));

    JLabel lblRb = new JLabel("Reactions Breakdown");
    lblRb.setFont(SECTION_HEADER_FONT);

    datasetPhysical = createPhysicalDataset();
    chartPhysical = createBarChart3D("PHYSICAL", datasetPhysical);
    physicalPlot = (CategoryPlot)chartPhysical.getPlot();
    setBarPlotColors(physicalPlot);
    pnlPhysical = new ChartPanel(chartPhysical);
    pnlPhysical.setPreferredSize(new Dimension(580, 250));

    datasetServices = createServicesDataset();
    JFreeChart chartServices = createBarChart3D("SERVICES", datasetServices);
    servicesPlot = (CategoryPlot)chartServices.getPlot();
    setBarPlotColors(servicesPlot);
    pnlServices = new ChartPanel(chartServices);
    pnlServices.setPreferredSize(new Dimension(580, 250));

    datasetPersonnel = createPersonnelDataset();
    JFreeChart chartPersonnel = createBarChart3D("PERSONNEL", datasetPersonnel);
    personnelPlot = (CategoryPlot)chartPersonnel.getPlot();
    setBarPlotColors(personnelPlot);
    pnlPersonnel = new ChartPanel(chartPersonnel);
    pnlPersonnel.setPreferredSize(new Dimension(580, 250));

    pnlAdd_ons.setLayout(new MigLayout());
    pnlAdd_ons.setBackground(Constants.SMOKE_GRAY);
    pnlAdd_ons.setPreferredSize(new Dimension(450, 865));

    JLabel lblSearch = new JLabel("Search: ");
    lblSearch.setFont(SECTION_HEADER_FONT);

    txtSearch = new JTextField(10);
    txtSearch.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    txtSearch.setFont(TEXT_FONT);
  }

  private JLabel createHeaderLabel(String text, String iconPath) {
    JLabel label = new JLabel(text);
    ImageIcon icon = createImageIcon(iconPath, 70, 70);
    label.setIcon(icon);
    label.setFont(HEADER_FONT);
    return label;
  }

  private ImageIcon createImageIcon(String path, int width, int height) {
    ImageIcon icon =
        new ImageIcon(getClass().getClassLoader().getResource(path));
    Image img = icon.getImage();
    Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return new ImageIcon(scaledImg);
  }

  private JFreeChart createBarChart(CategoryDataset dataset,
                                    PlotOrientation orientation) {
    JFreeChart chart = ChartFactory.createBarChart(
        null, null, null, dataset, orientation, false, true, false);
    chart.setBackgroundPaint(Constants.SMOKE_GRAY);
    return chart;
  }

  private JFreeChart createBarChart3D(String title, CategoryDataset dataset) {
    JFreeChart chart = ChartFactory.createBarChart3D(
        title, "Offices", null, dataset, PlotOrientation.VERTICAL, true, true,
        false);
    chart.setBackgroundPaint(Constants.SMOKE_GRAY);
    chart.getTitle().setPaint(Color.BLACK);
    return chart;
  }

  private JFreeChart createPieChart(PieDataset dataset) {
    JFreeChart chart = ChartFactory.createPieChart("TOTAL REACTION BREAKDOWN",
                                                   dataset, false, true, false);
    chart.setBackgroundPaint(Constants.SMOKE_GRAY);
    chart.setBorderVisible(false);
    chart.getTitle().setPaint(Color.BLACK);
    return chart;
  }

  private void setBarPlotColors(CategoryPlot plot) {
    BarRenderer renderer = (BarRenderer)plot.getRenderer();
    renderer.setSeriesPaint(0, Color.red);
    renderer.setSeriesPaint(1, Color.ORANGE);
    renderer.setSeriesPaint(2, Color.YELLOW);
    renderer.setSeriesPaint(3, Color.GREEN.darker());
    renderer.setSeriesPaint(4, Color.green.brighter().brighter());
  }

  private void setPiePlotColors(PiePlot plot) {
    plot.setSectionPaint(0, Color.red);
    plot.setSectionPaint(1, Color.ORANGE);
    plot.setSectionPaint(2, Color.yellow.brighter());
    plot.setSectionPaint(3, Color.green.darker());
    plot.setSectionPaint(4, Color.green.brighter());
  }

  private void setupDateChooser() {
    JLabel lblTimeline = new JLabel("TIMELINE");
    lblTimeline.setFont(
        new Font(Constants.FONT_FAMILY, Font.PLAIN, Constants.FONT_SIZE_SMALL));

    ratingsDate = new JDateChooser();
    ratingsDate.setDateFormatString("yyyy-MM-dd");
    ratingsDate.setFont(new Font(Constants.FONT_FAMILY, Font.PLAIN, 17));
    ratingsDate.getDateEditor().addPropertyChangeListener(
        new PropertyChangeListener() {
          @Override
          public void propertyChange(PropertyChangeEvent arg0) {
            if (ratingsDate.getDate() != null) {
              isDateRangeSelected = true;
              LocalDate chosenDate =
                  LocalDate.ofInstant(ratingsDate.getDate().toInstant(),
                                      java.time.ZoneId.systemDefault());
              LocalDate firstDayOfMonth = chosenDate.withDayOfMonth(1);
              endDate = chosenDate.withDayOfMonth(chosenDate.lengthOfMonth());
              startDate = firstDayOfMonth.toString();

              dateFilterAnd = " AND date_of_visit BETWEEN '" + startDate +
                              "' AND '" + endDate + "'";
              dateFilterWhere = " WHERE date_of_visit BETWEEN '" + startDate +
                                "' AND '" + endDate + "'";
            } else {
              isDateRangeSelected = false;
              dateFilterAnd = "";
              dateFilterWhere = "";
            }
            refreshData();
          }
        });
  }

  private void setupSearchField() {
    txtSearch.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void removeUpdate(DocumentEvent e) {
        updateSearch();
      }
      @Override
      public void insertUpdate(DocumentEvent e) {
        updateSearch();
      }
      @Override
      public void changedUpdate(DocumentEvent e) {
        updateSearch();
      }
      private void updateSearch() {
        String searchText = txtSearch.getText().trim();
        if (searchText.isEmpty()) {
          displayVisitInfo();
        } else {
          searchVisitInfo(searchText);
        }
      }
    });
  }

  private void setupDataTables() {
    String[] visitTableHeader = {"Name",          "Office Visited",
                                 "Date of Visit", "Time Started",
                                 "Time Ended",    "Purpose"};
    tblAdd_ons = createDataTable(visitTableHeader, false);
    model = (DefaultTableModel)tblAdd_ons.getModel();
    tblAdd_ons.getColumnModel().getColumn(1).setPreferredWidth(110);
    tblAdd_ons.getColumnModel().getColumn(2).setPreferredWidth(100);
    tblAdd_ons.getColumnModel().getColumn(3).setPreferredWidth(110);
    tblAdd_ons.getColumnModel().getColumn(4).setPreferredWidth(110);
    tblAdd_ons.getColumnModel().getColumn(5).setPreferredWidth(300);
    tblAdd_ons.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    JScrollPane pane = createScrollPane(tblAdd_ons);

    String[] commentTableHeader = {"Comments/Suggestions"};
    tblComments = createDataTable(commentTableHeader, false);
    modelComments = (DefaultTableModel)tblComments.getModel();
    tblComments.setRowHeight(75);
    JScrollPane paneComment = createScrollPane(tblComments);
  }

  private JTable createDataTable(String[] header, boolean isEditable) {
    JTable table = new JTable();
    table.getTableHeader().setOpaque(false);
    table.setBackground(Constants.SMOKE_GRAY);
    table.getTableHeader().setForeground(Constants.BLUE_JEANS);
    table.getTableHeader().setFont(TABLE_HEADER_FONT);
    DefaultTableModel tableModel = new DefaultTableModel() {
      @Override
      public boolean isCellEditable(int row, int column) {
        return isEditable;
      }
    };
    table.setModel(tableModel);
    tableModel.setColumnIdentifiers(header);
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
    table.setRowSorter(sorter);
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    table.setDefaultRenderer(Object.class, centerRenderer);
    ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer())
        .setHorizontalAlignment(JLabel.CENTER);
    return table;
  }

  private JScrollPane createScrollPane(JTable table) {
    JScrollPane pane =
        new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    pane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
    pane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
    pane.getViewport().setBackground(Constants.SMOKE_GRAY);
    return pane;
  }

  private static class CustomScrollBarUI extends BasicScrollBarUI {
    @Override
    protected void configureScrollBarColors() {
      this.thumbColor = Constants.BLUE_JEANS;
    }
  }

  private void setupButtons() {
    JButton btnMenu = createButton("home.png", this::openStartingWindow);
    JButton btnExt = createButton("logout.png", this::closeWindow);
    JButton btnDeleteData = createButton("Wipe Data", this::showWipeDataDialog);
    styleButton(btnDeleteData, Color.red.brighter());
    JButton btnPrint = createButton("Print Report", this::printReport);
    styleButton(btnPrint, Constants.BLUE_JEANS);
  }

  private JButton createButton(String iconPath, ActionListener action) {
    JButton button = new JButton(createImageIcon(iconPath, 18, 18));
    styleButton(button, Constants.BLUE_JEANS);
    button.addActionListener(action);
    return button;
  }

  private void styleButton(JButton button, Color bgColor) {
    button.setBackground(bgColor);
    button.setBorderPainted(false);
    button.setForeground(Color.WHITE);
    button.setFont(
        new Font(Constants.FONT_FAMILY, Font.PLAIN, Constants.FONT_SIZE_SMALL));
  }

  private void layoutComponents() {
    JScrollPane scrollFrame = new JScrollPane(pnlScreen);
    pnlScreen.setAutoscrolls(true);
    scrollFrame.getVerticalScrollBar().setUI(new CustomScrollBarUI());
    scrollFrame.setPreferredSize(new Dimension(1200, 900));
    this.add(scrollFrame);

    pnlScreen.add(pnlLeft, "");
    pnlLeft.add(createHeaderLabel("RATINGS", "bcshs-logo.png"), "wrap");
    pnlLeft.add(new JLabel("Client Visitation Breakdown") {
      {
        setFont(SECTION_HEADER_FONT);
      }
    }, "wrap, gapy 10");
    pnlLeft.add(pnltcv, "");
    pnltcv.add(new JPanel() {
      {
        setBackground(Constants.BLUE_JEANS);
        add(new JLabel("TOTAL CLIENT VISITATION") {
          {
            setForeground(Color.WHITE);
            setFont(LABEL_FONT);
          }
        });
      }
    }, "cell 0 1, center");
    pnltcv.add(new JLabel(createImageIcon("blank-profile.png", 170, 170)),
               "cell 0 2, center");
    pnltcv.add(lblTRB, "cell 0 3, center");
    pnlLeft.add(pnlbarGraph, "");

    pnlScreen.add(pnlRight, "right, wrap");
    pnlRight.add(new JLabel("TIMELINE") {
      {
        setFont(new Font(Constants.FONT_FAMILY, Font.PLAIN,
                         Constants.FONT_SIZE_SMALL));
      }
    }, "wrap, gapx 249");
    pnlRight.add(ratingsDate, "wrap, right, width 140px");
    pnlRight.add(pnlTrb, "wrap, gapy 10");

    pnlScreen.add(pnlRowSec, "wrap, grow, span");
    pnlRowSec.add(new JLabel("Reactions Breakdown") {
      {
        setFont(SECTION_HEADER_FONT);
      }
    }, "cell 0 0");
    pnlRowSec.add(pnlPhysical, "cell 0 1, gapy 10");
    pnlRowSec.add(pnlServices, "cell 0 2, gapy 10");
    pnlRowSec.add(pnlPersonnel, "cell 0 3, gapy 10");
    pnlRowSec.add(pnlAdd_ons, "cell 1 0, spany 4");
    pnlAdd_ons.add(new JLabel("Search: ") {
      {
        setFont(SECTION_HEADER_FONT);
      }
    }, "cell 0 0, right");
    pnlAdd_ons.add(txtSearch, "cell 0 0, width 150px, right");
    pnlAdd_ons.add(createScrollPane(tblAdd_ons),
                   "cell 0 1, gapy 10, center, growx, spanx, height 550px");
    pnlAdd_ons.add(createScrollPane(tblComments),
                   "cell 0 2, gapy 10, center, growx, spanx, height 400px");
    pnlAdd_ons.add(createButton("Print Report", this::printReport),
                   "cell 0 3, right, gapy 50, width 134px");
    pnlAdd_ons.add(createButton("Wipe Data", this::showWipeDataDialog),
                   "cell 0 4, right, width 134px");
    pnlAdd_ons.add(createButton("home.png", this::openStartingWindow),
                   "cell 0 5, right, width 65px");
    pnlAdd_ons.add(createButton("logout.png", this::closeWindow),
                   "cell 0 5, right, width 65px");

    ImageIcon appIcon =
        createImageIcon("survey.png", 0, 0); // Size doesn't matter for app icon
    this.setIconImage(appIcon.getImage());
    this.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    this.setLocationRelativeTo(null);
    this.getRootPane().setBorder(
        BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK.brighter()));
    this.getContentPane().setBackground(Constants.BLUE_JEANS);
    this.setUndecorated(true);
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  // --- Data Handling Methods ---

  private int executeCountQuery(String sqlQuery) {
    try {
      db.statement = db.conn.createStatement();
      db.rs = db.statement.executeQuery(sqlQuery);
      if (db.rs.next()) {
        return db.rs.getInt(1);
      }
    } catch (SQLException e) {
      e.printStackTrace(); // Basic error logging, consider more robust handling
    }
    return 0; // Return 0 if error or no result
  }

  private void refreshData() {
    updateTotalReactionsCount();
    updateClientVisitBreakdown();
    updateTotalReactionBreakdown();
    updateReactionBreakdowns();
    updateVisitDataTable();
    updateCommentDataTable();
    updateCharts();
  }

  private void updateCharts() {
    updateBarChartDataset(clientVisitPlot, createClientVisitDataset(),
                          pnlbarGraph);
    updatePieChartDataset(totalReactionsPlot, createTotalReactionsDataset(),
                          pnlTrb);
    updateBarChartDataset(physicalPlot, createPhysicalDataset(), pnlPhysical);
    updateBarChartDataset(servicesPlot, createServicesDataset(), pnlServices);
    updateBarChartDataset(personnelPlot, createPersonnelDataset(),
                          pnlPersonnel);
  }

  private void updateBarChartDataset(CategoryPlot plot, CategoryDataset dataset,
                                     ChartPanel chartPanel) {
    plot.setDataset(dataset);
    chartPanel.repaint();
  }

  private void updatePieChartDataset(PiePlot plot, PieDataset dataset,
                                     ChartPanel chartPanel) {
    plot.setDataset(dataset);
    chartPanel.repaint();
  }

  private void updateTotalReactionsCount() {
    String query = "SELECT COUNT(*) FROM survey_table" + dateFilterWhere;
    totalReactionsCount = executeCountQuery(query);
    lblTRB.setText(String.valueOf(totalReactionsCount));
  }

  private void updateClientVisitBreakdown() {
    officeVisitGpta = getOfficeVisitCount(GPTA_OFFICE);
    officeVisitGuidance = getOfficeVisitCount(GUIDANCE_OFFICE);
    officeVisitPrincipal = getOfficeVisitCount(PRINCIPALS_OFFICE);
    officeVisitRegistrar = getOfficeVisitCount(REGISTRARS_OFFICE);
    officeVisitOthers = getOfficeVisitCount(OTHERS_OFFICE);
  }

  private int getOfficeVisitCount(String office) {
    String query = "SELECT COUNT(office_visited) FROM survey_table WHERE "
                   + "office_visited = '" + office + "'" + dateFilterAnd;
    return executeCountQuery(query);
  }

  private CategoryDataset createClientVisitDataset() {
    updateClientVisitBreakdown(); // Ensure data is current
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    dataset.setValue(officeVisitGpta, OFFICES_CATEGORY, GPTA_OFFICE);
    dataset.setValue(officeVisitGuidance, OFFICES_CATEGORY, GUIDANCE_OFFICE);
    dataset.setValue(officeVisitPrincipal, OFFICES_CATEGORY, PRINCIPALS_OFFICE);
    dataset.setValue(officeVisitRegistrar, OFFICES_CATEGORY, REGISTRARS_OFFICE);
    dataset.setValue(officeVisitOthers, OFFICES_CATEGORY, OTHERS_OFFICE);
    return dataset;
  }

  private void updateTotalReactionBreakdown() {
    totalReactVd = getTotalReactionCount(Constants.VERY_DISSATISFIED);
    totalReactD = getTotalReactionCount(Constants.DISSATISFIED);
    totalReactN = getTotalReactionCount(Constants.NEUTRAL);
    totalReactS = getTotalReactionCount(Constants.SATISFIED);
    totalReactVs = getTotalReactionCount(Constants.VERY_SATISFIED);
  }

  private int getTotalReactionCount(String reaction) {
    int count = 0;
    count += getReactionCountForQuestion(1, reaction);
    count += getReactionCountForQuestion(2, reaction);
    count += getReactionCountForQuestion(3, reaction);
    return count;
  }

  private int getReactionCountForQuestion(int questionNumber, String reaction) {
    String query =
        String.format("SELECT COUNT(q%d) FROM survey_table WHERE q%d = '%s'",
                      questionNumber, questionNumber, reaction) +
        dateFilterAnd;
    return executeCountQuery(query);
  }

  private PieDataset createTotalReactionsDataset() {
    updateTotalReactionBreakdown(); // Ensure data is current
    DefaultPieDataset dataset = new DefaultPieDataset();
    dataset.setValue(Constants.VERY_DISSATISFIED, totalReactVd);
    dataset.setValue(Constants.DISSATISFIED, totalReactD);
    dataset.setValue(Constants.NEUTRAL, totalReactN);
    dataset.setValue(Constants.SATISFIED, totalReactS);
    dataset.setValue(Constants.VERY_SATISFIED, totalReactVs);
    return dataset;
  }

  private void updateReactionBreakdowns() {
    // Very Dissatisfied
    physicalVdGpta = getOfficeReactionCount(
        GPTA_OFFICE, Constants.VERY_DISSATISFIED, "physical");
    physicalVdGuidance = getOfficeReactionCount(
        GUIDANCE_OFFICE, Constants.VERY_DISSATISFIED, "physical");
    physicalVdPrincipal = getOfficeReactionCount(
        PRINCIPALS_OFFICE, Constants.VERY_DISSATISFIED, "physical");
    physicalVdRegistrar = getOfficeReactionCount(
        REGISTRARS_OFFICE, Constants.VERY_DISSATISFIED, "physical");
    physicalVdOthers = getOfficeReactionCount(
        OTHERS_OFFICE, Constants.VERY_DISSATISFIED, "physical");

    servicesVdGpta = getOfficeReactionCount(
        GPTA_OFFICE, Constants.VERY_DISSATISFIED, "services");
    servicesVdGuidance = getOfficeReactionCount(
        GUIDANCE_OFFICE, Constants.VERY_DISSATISFIED, "services");
    servicesVdPrincipal = getOfficeReactionCount(
        PRINCIPALS_OFFICE, Constants.VERY_DISSATISFIED, "services");
    servicesVdRegistrar = getOfficeReactionCount(
        REGISTRARS_OFFICE, Constants.VERY_DISSATISFIED, "services");
    servicesVdOthers = getOfficeReactionCount(
        OTHERS_OFFICE, Constants.VERY_DISSATISFIED, "services");

    personnelVdGpta = getOfficeReactionCount(
        GPTA_OFFICE, Constants.VERY_DISSATISFIED, "personnel");
    personnelVdGuidance = getOfficeReactionCount(
        GUIDANCE_OFFICE, Constants.VERY_DISSATISFIED, "personnel");
    personnelVdPrincipal = getOfficeReactionCount(
        PRINCIPALS_OFFICE, Constants.VERY_DISSATISFIED, "personnel");
    personnelVdRegistrar = getOfficeReactionCount(
        REGISTRARS_OFFICE, Constants.VERY_DISSATISFIED, "personnel");
    personnelVdOthers = getOfficeReactionCount(
        OTHERS_OFFICE, Constants.VERY_DISSATISFIED, "personnel");

    // Dissatisfied
    physicalDGpta =
        getOfficeReactionCount(GPTA_OFFICE, Constants.DISSATISFIED, "physical");
    physicalDGuidance = getOfficeReactionCount(
        GUIDANCE_OFFICE, Constants.DISSATISFIED, "physical");
    physicalDPrincipal = getOfficeReactionCount(
        PRINCIPALS_OFFICE, Constants.DISSATISFIED, "physical");
    physicalDRegistrar = getOfficeReactionCount(
        REGISTRARS_OFFICE, Constants.DISSATISFIED, "physical");
    physicalDOthers = getOfficeReactionCount(
        OTHERS_OFFICE, Constants.DISSATISFIED, "physical");

    servicesDGpta =
        getOfficeReactionCount(GPTA_OFFICE, Constants.DISSATISFIED, "services");
    servicesDGuidance = getOfficeReactionCount(
        GUIDANCE_OFFICE, Constants.DISSATISFIED, "services");
    servicesDPrincipal = getOfficeReactionCount(
        PRINCIPALS_OFFICE, Constants.DISSATISFIED, "services");
    servicesDRegistrar = getOfficeReactionCount(
        REGISTRARS_OFFICE, Constants.DISSATISFIED, "services");
    servicesDOthers = getOfficeReactionCount(
        OTHERS_OFFICE, Constants.DISSATISFIED, "services");

    personnelDGpta = getOfficeReactionCount(GPTA_OFFICE, Constants.DISSATISFIED,
                                            "personnel");
    personnelDGuidance = getOfficeReactionCount(
        GUIDANCE_OFFICE, Constants.DISSATISFIED, "personnel");
    personnelDPrincipal = getOfficeReactionCount(
        PRINCIPALS_OFFICE, Constants.DISSATISFIED, "personnel");
    personnelDRegistrar = getOfficeReactionCount(
        REGISTRARS_OFFICE, Constants.DISSATISFIED, "personnel");
    personnelDOthers = getOfficeReactionCount(
        OTHERS_OFFICE, Constants.DISSATISFIED, "personnel");

    // Neutral
    physicalNGpta =
        getOfficeReactionCount(GPTA_OFFICE, Constants.NEUTRAL, "physical");
    physicalNGuidance =
        getOfficeReactionCount(GUIDANCE_OFFICE, Constants.NEUTRAL, "physical");
    physicalNPrincipal = getOfficeReactionCount(PRINCIPALS_OFFICE,
                                                Constants.NEUTRAL, "physical");
    physicalNRegistrar = getOfficeReactionCount(REGISTRARS_OFFICE,
                                                Constants.NEUTRAL, "physical");
    physicalNOthers =
        getOfficeReactionCount(OTHERS_OFFICE, Constants.NEUTRAL, "physical");

    servicesNGpta =
        getOfficeReactionCount(GPTA_OFFICE, Constants.NEUTRAL, "services");
    servicesNGuidance =
        getOfficeReactionCount(GUIDANCE_OFFICE, Constants.NEUTRAL, "services");
    servicesNPrincipal = getOfficeReactionCount(PRINCIPALS_OFFICE,
                                                Constants.NEUTRAL, "services");
    servicesNRegistrar = getOfficeReactionCount(REGISTRARS_OFFICE,
                                                Constants.NEUTRAL, "services");
    servicesNOthers =
        getOfficeReactionCount(OTHERS_OFFICE, Constants.NEUTRAL, "services");

    personnelNGpta =
        getOfficeReactionCount(GPTA_OFFICE, Constants.NEUTRAL, "personnel");
    personnelNGuidance =
        getOfficeReactionCount(GUIDANCE_OFFICE, Constants.NEUTRAL, "personnel");
    personnelNPrincipal = getOfficeReactionCount(
        PRINCIPALS_OFFICE, Constants.NEUTRAL, "personnel");
    personnelNRegistrar = getOfficeReactionCount(
        REGISTRARS_OFFICE, Constants.NEUTRAL, "personnel");
    personnelNOthers =
        getOfficeReactionCount(OTHERS_OFFICE, Constants.NEUTRAL, "personnel");

    // Satisfied
    physicalSGpta =
        getOfficeReactionCount(GPTA_OFFICE, Constants.SATISFIED, "physical");
    physicalSGuidance = getOfficeReactionCount(GUIDANCE_OFFICE,
                                               Constants.SATISFIED, "physical");
    physicalSPrincipal = getOfficeReactionCount(
        PRINCIPALS_OFFICE, Constants.SATISFIED, "physical");
    physicalSRegistrar = getOfficeReactionCount(
        REGISTRARS_OFFICE, Constants.SATISFIED, "physical");
    physicalSOthers =
        getOfficeReactionCount(OTHERS_OFFICE, Constants.SATISFIED, "physical");

    servicesSGpta =
        getOfficeReactionCount(GPTA_OFFICE, Constants.SATISFIED, "services");
    servicesSGuidance = getOfficeReactionCount(GUIDANCE_OFFICE,
                                               Constants.SATISFIED, "services");
    servicesSPrincipal = getOfficeReactionCount(
        PRINCIPALS_OFFICE, Constants.SATISFIED, "services");
    servicesSRegistrar = getOfficeReactionCount(
        REGISTRARS_OFFICE, Constants.SATISFIED, "services");
    servicesSOthers =
        getOfficeReactionCount(OTHERS_OFFICE, Constants.SATISFIED, "services");

    personnelSGpta =
        getOfficeReactionCount(GPTA_OFFICE, Constants.SATISFIED, "personnel");
    personnelSGuidance = getOfficeReactionCount(
        GUIDANCE_OFFICE, Constants.SATISFIED, "personnel");
    personnelSPrincipal = getOfficeReactionCount(
        PRINCIPALS_OFFICE, Constants.SATISFIED, "personnel");
    personnelSRegistrar = getOfficeReactionCount(
        REGISTRARS_OFFICE, Constants.SATISFIED, "personnel");
    personnelSOthers =
        getOfficeReactionCount(OTHERS_OFFICE, Constants.SATISFIED, "personnel");

    // Very Satisfied
    physicalVsGpta = getOfficeReactionCount(
        GPTA_OFFICE, Constants.VERY_SATISFIED, "physical");
    physicalVsGuidance = getOfficeReactionCount(
        GUIDANCE_OFFICE, Constants.VERY_SATISFIED, "physical");
    physicalVsPrincipal = getOfficeReactionCount(
        PRINCIPALS_OFFICE, Constants.VERY_SATISFIED, "physical");
    physicalVsRegistrar = getOfficeReactionCount(
        REGISTRARS_OFFICE, Constants.VERY_SATISFIED, "physical");
    physicalVsOthers = getOfficeReactionCount(
        OTHERS_OFFICE, Constants.VERY_SATISFIED, "physical");

    servicesVsGpta = getOfficeReactionCount(
        GPTA_OFFICE, Constants.VERY_SATISFIED, "services");
    servicesVsGuidance = getOfficeReactionCount(
        GUIDANCE_OFFICE, Constants.VERY_SATISFIED, "services");
    servicesVsPrincipal = getOfficeReactionCount(
        PRINCIPALS_OFFICE, Constants.VERY_SATISFIED, "services");
    servicesVsRegistrar = getOfficeReactionCount(
        REGISTRARS_OFFICE, Constants.VERY_SATISFIED, "services");
    servicesVsOthers = getOfficeReactionCount(
        OTHERS_OFFICE, Constants.VERY_SATISFIED, "services");

    personnelVsGpta = getOfficeReactionCount(
        GPTA_OFFICE, Constants.VERY_SATISFIED, "personnel");
    personnelVsGuidance = getOfficeReactionCount(
        GUIDANCE_OFFICE, Constants.VERY_SATISFIED, "personnel");
    personnelVsPrincipal = getOfficeReactionCount(
        PRINCIPALS_OFFICE, Constants.VERY_SATISFIED, "personnel");
    personnelVsRegistrar = getOfficeReactionCount(
        REGISTRARS_OFFICE, Constants.VERY_SATISFIED, "personnel");
    personnelVsOthers = getOfficeReactionCount(
        OTHERS_OFFICE, Constants.VERY_SATISFIED, "personnel");
  }

  private int getOfficeReactionCount(String office, String reaction,
                                     String category) {
    int count = 0;
    count +=
        getReactionCountForOfficeAndQuestion(office, reaction, 1, category);
    count +=
        getReactionCountForOfficeAndQuestion(office, reaction, 2, category);
    count +=
        getReactionCountForOfficeAndQuestion(office, reaction, 3, category);
    return count;
  }

  private int getReactionCountForOfficeAndQuestion(String office,
                                                   String reaction,
                                                   int questionNumber,
                                                   String category) {
    String query =
        String.format("SELECT COUNT(q%d) FROM survey_table WHERE "
                          + "office_visited = '%s' AND q%d = '%s'",
                      questionNumber, office, questionNumber, reaction) +
        dateFilterAnd;
    if ((category.equals("physical") && questionNumber == 1) ||
        (category.equals("services") && questionNumber == 2) ||
        (category.equals("personnel") && questionNumber == 3)) {
      return executeCountQuery(query);
    }
    return 0;
  }

  private CategoryDataset createPhysicalDataset() {
    updateReactionBreakdowns(); // Ensure data is current
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    addReactionValuesToDataset(dataset, physicalVdGpta, physicalVdGuidance,
                               physicalVdPrincipal, physicalVdRegistrar,
                               physicalVdOthers, Constants.VERY_DISSATISFIED);
    addReactionValuesToDataset(dataset, physicalDGpta, physicalDGuidance,
                               physicalDPrincipal, physicalDRegistrar,
                               physicalDOthers, Constants.DISSATISFIED);
    addReactionValuesToDataset(dataset, physicalNGpta, physicalNGuidance,
                               physicalNPrincipal, physicalNRegistrar,
                               physicalNOthers, Constants.NEUTRAL);
    addReactionValuesToDataset(dataset, physicalSGpta, physicalSGuidance,
                               physicalSPrincipal, physicalSRegistrar,
                               physicalSOthers, Constants.SATISFIED);
    addReactionValuesToDataset(dataset, physicalVsGpta, physicalVsGuidance,
                               physicalVsPrincipal, physicalVsRegistrar,
                               physicalVsOthers, Constants.VERY_SATISFIED);
    return dataset;
  }

  private CategoryDataset createServicesDataset() {
    updateReactionBreakdowns(); // Ensure data is current
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    addReactionValuesToDataset(dataset, servicesVdGpta, servicesVdGuidance,
                               servicesVdPrincipal, servicesVdRegistrar,
                               servicesVdOthers, Constants.VERY_DISSATISFIED);
    addReactionValuesToDataset(dataset, servicesDGpta, servicesDGuidance,
                               servicesDPrincipal, servicesDRegistrar,
                               servicesDOthers, Constants.DISSATISFIED);
    addReactionValuesToDataset(dataset, servicesNGpta, servicesNGuidance,
                               servicesNPrincipal, servicesNRegistrar,
                               servicesNOthers, Constants.NEUTRAL);
    addReactionValuesToDataset(dataset, servicesSGpta, servicesSGuidance,
                               servicesSPrincipal, servicesSRegistrar,
                               servicesSOthers, Constants.SATISFIED);
    addReactionValuesToDataset(dataset, servicesVsGpta, servicesVsGuidance,
                               servicesVsPrincipal, servicesVsRegistrar,
                               servicesVsOthers, Constants.VERY_SATISFIED);
    return dataset;
  }

  private CategoryDataset createPersonnelDataset() {
    updateReactionBreakdowns(); // Ensure data is current
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    addReactionValuesToDataset(dataset, personnelVdGpta, personnelVdGuidance,
                               personnelVdPrincipal, personnelVdRegistrar,
                               personnelVdOthers, Constants.VERY_DISSATISFIED);
    addReactionValuesToDataset(dataset, personnelDGpta, personnelDGuidance,
                               personnelDPrincipal, personnelDRegistrar,
                               personnelDOthers, Constants.DISSATISFIED);
    addReactionValuesToDataset(dataset, personnelNGpta, personnelNGuidance,
                               personnelNPrincipal, personnelNRegistrar,
                               personnelNOthers, Constants.NEUTRAL);
    addReactionValuesToDataset(dataset, personnelSGpta, personnelSGuidance,
                               personnelSPrincipal, personnelSRegistrar,
                               personnelSOthers, Constants.SATISFIED);
    addReactionValuesToDataset(dataset, personnelVsGpta, personnelVsGuidance,
                               personnelVsPrincipal, personnelVsRegistrar,
                               personnelVsOthers, Constants.VERY_SATISFIED);
    return dataset;
  }

  private void addReactionValuesToDataset(DefaultCategoryDataset dataset,
                                          int gpta, int guidance, int principal,
                                          int registrar, int others,
                                          String reactionType) {
    dataset.addValue(gpta, reactionType, GPTA_OFFICE);
    dataset.addValue(guidance, reactionType, GUIDANCE_OFFICE);
    dataset.addValue(principal, reactionType, PRINCIPALS_OFFICE);
    dataset.addValue(registrar, reactionType, REGISTRARS_OFFICE);
    dataset.addValue(others, reactionType, OTHERS_OFFICE);
  }

  private void displayVisitInfo() {
    model.getDataVector().clear();
    tblAdd_ons.revalidate();
    String query = "SELECT name, office_visited, date_of_visit, time_started, "
                   + "time_ended, purpose_of_visit FROM survey_table" +
                   dateFilterWhere;
    populateVisitTable(query);
  }

  private void searchVisitInfo(String searchText) {
    model.getDataVector().clear();
    tblAdd_ons.revalidate();
    String query = "SELECT name, office_visited, date_of_visit, time_started, "
                   + "time_ended, purpose_of_visit FROM survey_table WHERE "
                   + "name LIKE '%" + searchText + "%' OR "
                   + "office_visited LIKE '%" + searchText + "%' OR "
                   + "time_started LIKE '%" + searchText + "%' OR "
                   + "time_ended LIKE '%" + searchText + "%' OR "
                   + "purpose_of_visit LIKE '%" + searchText + "%' " +
                   dateFilterAnd;
    populateVisitTable(query);
  }

  private void populateVisitTable(String query) {
    try {
      db.statement = db.conn.createStatement();
      db.rs = db.statement.executeQuery(query);
      while (db.rs.next()) {
        String[] data = {db.rs.getString("name"),
                         db.rs.getString("office_visited"),
                         db.rs.getString("date_of_visit"),
                         db.rs.getString("time_started"),
                         db.rs.getString("time_ended"),
                         db.rs.getString("purpose_of_visit")};
        model.addRow(data);
      }
    } catch (SQLException e) {
      e.printStackTrace(); // Basic error logging
    }
  }

  private void updateVisitDataTable() {
    if (txtSearch.getText().trim().isEmpty()) {
      displayVisitInfo();
    } else {
      searchVisitInfo(txtSearch.getText().trim());
    }
  }

  private void updateCommentDataTable() {
    modelComments.getDataVector().clear();
    tblComments.revalidate();
    String query = "SELECT comments FROM survey_table WHERE comments IS NOT "
                   + "NULL AND comments != ''" + dateFilterAnd;
    try {
      db.statement = db.conn.createStatement();
      db.rs = db.statement.executeQuery(query);
      while (db.rs.next()) {
        String[] data = {db.rs.getString("comments")};
        modelComments.addRow(data);
      }
    } catch (SQLException e) {
      e.printStackTrace(); // Basic error logging
    }
  }

  private void deleteNull() {
    String query = "DELETE FROM survey_table WHERE office_visited IS NULL OR "
                   + "time_started IS NULL OR "
                   + "date_of_visit IS NULL OR time_ended IS NULL OR q1 IS "
                   + "NULL OR q2 IS NULL OR q3 IS NULL";
    try {
      db.statement = db.conn.createStatement();
      db.statement.executeUpdate(query);
    } catch (SQLException e) {
      e.printStackTrace(); // Basic error logging
    }
  }

  private void deleteAllData() {
    int result = JOptionPane.showConfirmDialog(
        null, "Are you sure to delete all data? This action cannot be undone.",
        "Wiping All Data", JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE);
    if (result == JOptionPane.YES_OPTION) {
      String dropTableQuery = "DROP TABLE survey_table";
      String createTableQuery =
          "CREATE TABLE survey_table(name varchar(Constants.FONT_SIZE_LARGE, "
          + "office_visited "
          + "varchar(20), "
          + "time_started varchar(6), date_of_visit date, time_ended "
          + "varchar(6), purpose_of_visit varchar(100), q1 varchar(20), q2 "
          + "varchar(20), q3 varchar(20), "
          + "comments varchar(200), id int AUTO_INCREMENT PRIMARY KEY)";
      try {
        db.statement = db.conn.createStatement();
        db.statement.executeUpdate(dropTableQuery);
        db.statement.executeUpdate(createTableQuery);
        refreshData(); // Refresh UI after wiping data
        JOptionPane.showMessageDialog(null, "All data wiped successfully.");
      } catch (SQLException e) {
        e.printStackTrace(); // Basic error logging
        JOptionPane.showMessageDialog(null, "Error wiping data.");
      }
    }
  }

  private void deleteCurrentData() {
    String query = "DELETE FROM survey_table" + dateFilterWhere;
    try {
      db.statement = db.conn.createStatement();
      db.statement.executeUpdate(query);
      refreshData(); // Refresh UI after wiping data
      JOptionPane.showMessageDialog(null, "Current data wiped successfully.");
    } catch (SQLException e) {
      e.printStackTrace(); // Basic error logging
      JOptionPane.showMessageDialog(null, "Error wiping current data.");
    }
  }

  private void showWipeDataDialog(ActionEvent arg0) {
    if (isDateRangeSelected) {
      Object[] options = {"Wipe Current Data", "Wipe All Data"};
      Object selectedValue = JOptionPane.showInputDialog(
          null, "Data to Wipe Out", "Wiping Data", JOptionPane.WARNING_MESSAGE,
          null, options, options[0]);
      if (selectedValue == options[0]) {
        deleteCurrentData();
      } else if (selectedValue == options[1]) {
        deleteAllData();
      }
    } else {
      deleteAllData(); // If no date range selected, default to wiping all
    }
  }

  private void printReport(ActionEvent arg0) {
    PrintWindow pw = new PrintWindow();
    pw.values(chartClientVisit, chartTotalReactions, chartPhysical,
              chartServices, chartPersonnel);
    pw.setVisible(true);
    printRatings(pw.pnlPagePrint);
    pw.setVisible(false);
  }

  private void printRatings(JPanel pnl) {
    PrinterJob pj = PrinterJob.getPrinterJob();
    PageFormat preformat = pj.defaultPage();
    preformat.setOrientation(PageFormat.PORTRAIT);
    PageFormat postformat = pj.pageDialog(preformat);
    if (postformat == null)
      return; // User cancelled print dialog

    pj.setJobName("Ratings Report");
    pj.setPrintable(new Printable() {
      @Override
      public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
          throws PrinterException {
        if (pageIndex > 0)
          return Printable.NO_SUCH_PAGE;

        Graphics2D g2d = (Graphics2D)graphics;
        double panelWidth = pnl.getWidth();
        double panelHeight = pnl.getHeight();
        double pageWidth = pageFormat.getImageableWidth();
        double pageHeight = pageFormat.getImageableHeight();

        double scaleX = pageWidth / panelWidth;
        double scaleY = pageHeight / panelHeight;
        double scaleFactor = Math.min(
            scaleX,
            scaleY); // Fit to page width or height, whichever is smaller

        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        g2d.scale(scaleFactor, scaleFactor);
        pnl.paint(g2d);
        return Printable.PAGE_EXISTS;
      }
    });

    if (pj.printDialog()) {
      try {
        pj.print();
      } catch (PrinterException pe) {
        JOptionPane.showMessageDialog(this, "Print Error: " + pe.getMessage(),
                                      "Printing Error",
                                      JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  // --- UI Action Methods ---
  private void openStartingWindow(ActionEvent e) {
    StartingWindow sw = new StartingWindow();
    sw.setVisible(true);
    setVisible(false);
  }

  private void closeWindow(ActionEvent e) { dispose(); }
}