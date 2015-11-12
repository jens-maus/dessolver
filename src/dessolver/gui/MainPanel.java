package dessolver.gui;

import java.io.*;
import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.util.*;
import java.net.*;
import java.awt.geom.Point2D;

import dessolver.math.*;
import dessolver.util.*;

/**
 * <p>Title: DESSolver Main Panel</p>
 * <p>Description: This class is the representation of the GUIs Main Panel</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: HTW-Dresden</p>
 * @author <A HREF="Jens.Langner@htw-dresden.de">Jens Langner</A>
 * @author <A HREF="Felix.Kraemer@htw-dresden.de">Felix Kraemer</A>
 * @version 1.3
 */

public class MainPanel extends JPanel implements ActionListener
{
  static ResourceBundle res = ResourceBundle.getBundle("dessolver");

  // a String array with all possible math Methods in it.
  public final String[] mathMethods = { RungeKutta.IDENTIFIER,
                                        RungeKuttaInc.IDENTIFIER,
                                        Euler.IDENTIFIER,
                                        EulerModified.IDENTIFIER,
                                        Heun.IDENTIFIER,
                                        DormandPrince.IDENTIFIER
                                      };

  // this are our default example settings for the start
  private double xMin = 0.0, xMax = 30.0, yMin = -3.0, yMax = 3.0, incr = 0.1, eps = 0.1;
  private String function = "y2,0.5\n"+
                            "-0.2*y2-sin(y1),1.5";


  private String ftemp;

  // Here we declare all the Labels & TextFields
  private JLabel        xminLabel = new JLabel(res.getString("LB_XMIN")+" ", JLabel.RIGHT);
  private DecimalField  xminTextF = new DecimalField(xMin, 5);
  private JLabel        xmaxLabel = new JLabel(res.getString("LB_XMAX")+" ", JLabel.RIGHT);
  private DecimalField  xmaxTextF = new DecimalField(xMax, 5);
  private JLabel        yminLabel = new JLabel(res.getString("LB_YMIN")+" ", JLabel.RIGHT);
  private DecimalField  yminTextF = new DecimalField(yMin, 5);
  private JLabel        ymaxLabel = new JLabel(res.getString("LB_YMAX")+" ", JLabel.RIGHT);
  private DecimalField  ymaxTextF = new DecimalField(yMax, 5);
  private JLabel        incrLabel = new JLabel(res.getString("LB_INCR")+" ", JLabel.RIGHT);
  private DecimalField  incrTextF = new DecimalField(incr, 5);
  private JLabel        erroLabel = new JLabel(res.getString("LB_ERROR")+" ", JLabel.RIGHT);
  private DecimalField  erroTextF = new DecimalField(eps, 5);
  private JTextArea     funcTextA = new JTextArea(function, 4, 10);
  private JLabel        methodLabel = new JLabel(res.getString("LB_SOLVEMETHOD")+" ", JLabel.RIGHT);
  private JComboBox     methodComboBox = new JComboBox(mathMethods);

  // The tabbed pane that holds the GraphPanel and TextArea for the results
  private JTabbedPane tabbedPane = new JTabbedPane();
  private Vector tabbedVector = new Vector(1,1);
  private TabbedPaneAction tabbedAction = new TabbedPaneAction();

  // And we need a single refresh Button to refresh this thing.
  private JButton refreshButton = new JButton(res.getString("BT_REFRESH"));

  private GraphPanel gPanel;

  private Vector functionstrings;
  private Vector startvector;

  public MainPanel()
  {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // lets build the TextArea
    funcTextA.setLineWrap(false);
    funcTextA.setWrapStyleWord(false);
    JScrollPane areaScrollPane = new JScrollPane(funcTextA);
    areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    areaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    areaScrollPane.setBorder( BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder(res.getString("TXT_EQUATIONS")),
                                areaScrollPane.getBorder())
                            );

    // lets create a toppanel
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
    topPanel.add(areaScrollPane);

    // now we create a single panel which is included in the Main one.
    JPanel varPanel = new JPanel();
    varPanel.setLayout(new BoxLayout(varPanel, BoxLayout.Y_AXIS));
    varPanel.setBorder(BorderFactory.createTitledBorder(res.getString("TXT_VARIABLES")));

    JPanel textPanel = new JPanel(new GridLayout(0, 6));

    // now lets add all the Labels & TextFields to it.
    textPanel.add(xminLabel);
    xminLabel.setLabelFor(xminTextF);
    textPanel.add(xminTextF);
    xminTextF.addActionListener(this);

    textPanel.add(xmaxLabel);
    xmaxLabel.setLabelFor(xmaxTextF);
    textPanel.add(xmaxTextF);
    xmaxTextF.addActionListener(this);

    textPanel.add(incrLabel);
    incrLabel.setLabelFor(incrTextF);
    textPanel.add(incrTextF);
    incrTextF.addActionListener(this);

    textPanel.add(yminLabel);
    yminLabel.setLabelFor(yminTextF);
    textPanel.add(yminTextF);
    yminTextF.addActionListener(this);

    textPanel.add(ymaxLabel);
    ymaxLabel.setLabelFor(ymaxTextF);
    textPanel.add(ymaxTextF);
    ymaxTextF.addActionListener(this);

    textPanel.add(erroLabel);
    erroLabel.setLabelFor(erroTextF);
    textPanel.add(erroTextF);
    erroTextF.addActionListener(this);

    erroTextF.setDisabledTextColor(Color.white);
    erroTextF.setEnabled(false);

    // create the buttonPanel with controls
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    buttonPanel.add(methodLabel);
    buttonPanel.add(methodComboBox);
    methodComboBox.addActionListener(this);
    buttonPanel.add(Box.createHorizontalGlue());
    buttonPanel.add(refreshButton);
    refreshButton.addActionListener(this);

    varPanel.add(textPanel);
    varPanel.add(buttonPanel);

    topPanel.add(varPanel);

    ftemp = new String(function);
    Vector tempstrings = SplitString(function);
    tempstrings = SplitFunction(tempstrings);
    functionstrings = (Vector)tempstrings.elementAt(0);
    startvector = (Vector)tempstrings.elementAt(1);

    gPanel = new GraphPanel(xMin, xMax, yMin, yMax, incr, eps, functionstrings, startvector);

    // lets pack this outerGraphPanel into a tabbed Pane
    tabbedPane.addTab(res.getString("TXT_SOLVEDGRAPH"), gPanel);
    tabbedPane.setSelectedIndex(0);
    tabbedPane.addChangeListener(tabbedAction);

    // we have to set the topPanel to its maximum height to get the
    // tabbed Pane as large as possible.
    int maxwidth = (int)topPanel.getMaximumSize().getWidth();
    int minheight = (int)topPanel.getMinimumSize().getHeight();
    topPanel.setMaximumSize(new Dimension(maxwidth, minheight));

    add(topPanel);
    add(tabbedPane);

    checkTabbedPane();
  }

  // lets define the ActionListener Method
  public void actionPerformed(ActionEvent e)
  {
    xMin  = xminTextF.getValue();
    xMax  = xmaxTextF.getValue();
    yMin  = yminTextF.getValue();
    yMax  = ymaxTextF.getValue();
    incr  = incrTextF.getValue();
    eps   = erroTextF.getValue();

    // lets check if the user has entered some nonsense ;)
    if(incr <= 0)
    {
      JOptionPane.showMessageDialog(null, res.getString("ERR_INCR"), res.getString("TXT_ERROR"), JOptionPane.ERROR_MESSAGE);

      incr = 0.1;
      incrTextF.setValue(incr);
    }

    if((xMax-xMin)*incr < 0 || yMin > yMax)
    {
      JOptionPane.showMessageDialog(null, res.getString("ERR_VALUES"), res.getString("TXT_ERROR"), JOptionPane.ERROR_MESSAGE);
      return;
    }

    // the error tolerance can not be any value smaller than 0
    if(eps < 0)
    {
      JOptionPane.showMessageDialog(null, res.getString("ERR_EPS"), res.getString("TXT_ERROR"), JOptionPane.ERROR_MESSAGE);

      eps = 0.1;
      erroTextF.setValue(eps);
    }

    // lets check if the user has touched the ComboBox CalcMethod
    if(e.getSource().equals(methodComboBox))
    {
      if(gPanel.setCalcMethod((String)methodComboBox.getSelectedItem()))
      {
        // lets clear ftemp to that the next step will reset the functions
        ftemp = null;

        // check if the selected method support error correction
        erroTextF.setEnabled(gPanel.hasMethodErrorCorr());
      }
    }

    // lets check if the user has changed the functiosn in the TextArea
    if(ftemp == null || ftemp.compareToIgnoreCase(funcTextA.getText()) != 0)
    {
      function = funcTextA.getText();
      Vector tempstrings = SplitString(function);

      tempstrings = SplitFunction(tempstrings);
      if(tempstrings != null)
      {
        ftemp = new String(function);
        functionstrings = (Vector)tempstrings.elementAt(0);
        startvector = (Vector)tempstrings.elementAt(1);
        gPanel.setFunction(functionstrings, startvector);
      }
      else
      {
        JOptionPane.showMessageDialog(null, res.getString("ERR_EQUATIONS"), res.getString("TXT_ERROR"), JOptionPane.ERROR_MESSAGE);

        funcTextA.setText(ftemp);
        return;
      }
    }

    gPanel.setView(xMin, xMax, yMin, yMax, incr, eps);

    checkTabbedPane();
    tabbedAction.stateChanged(new ChangeEvent(tabbedPane));
  }

  /**
   * Method that will check how many tabbed Panes we have
   * and adjusts it to the value of results in the ResultVector
   */
  private void checkTabbedPane()
  {
    Vector resultVector = gPanel.getResultVector();

    if(resultVector == null || resultVector.size() == 0) return;

    // lets check if we have enough tabbed panes and if we need
    // more we create some and if not we remove some of them.
    int idxRes = ((Vector)resultVector.get(0)).size();

    // now we have check if we have enough tabs
    for(int i=0; tabbedPane.getTabCount()-1 != idxRes;)
    {
      // if we need more tabs, lets create a empty scrollpane and add it
      if(tabbedPane.getTabCount()-1 < idxRes)
      {
        JPanel emptyPanel = new JPanel(new BorderLayout());
        JScrollPane areaScrollPane = new JScrollPane(emptyPanel);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        areaScrollPane.setBorder( BorderFactory.createCompoundBorder(
                                  BorderFactory.createTitledBorder(res.getString("TXT_RESULTS")),
                                  areaScrollPane.getBorder())
                                 );

        tabbedVector.add(tabbedPane.getTabCount()-1, emptyPanel);
        tabbedPane.addTab("y"+tabbedPane.getTabCount(), areaScrollPane);
        tabbedPane.setForegroundAt(tabbedPane.getTabCount()-1, gPanel.colors[(tabbedPane.getTabCount()-2)%11]);
      }
      else if(tabbedPane.getTabCount()-1 > idxRes)
      {
        tabbedVector.remove(tabbedPane.getTabCount()-2);
        tabbedPane.removeTabAt(tabbedPane.getTabCount()-1);
      }
    }
  }

  private Vector SplitString(String split)
  {
    StringTokenizer str = new StringTokenizer(split, "\n");
    Vector v = new Vector(1,1);

    while(str.hasMoreTokens())
    {
      v.addElement(str.nextToken());
    }

    return v;
  }

  private Vector SplitFunction(Vector functions)
  {
    Vector ret = new Vector(1,1);
    Vector f = new Vector(1,1);
    Vector a = new Vector(1,1);
    Enumeration e = functions.elements();

    while(e.hasMoreElements())
    {
      String split = (String)e.nextElement();
      StringTokenizer str = new StringTokenizer(split, ",");
      if(str.countTokens() != 2) return null;
      f.addElement(str.nextToken());
      Double dummy = new Double(str.nextToken());
      a.addElement(dummy);
    }

    ret.addElement(f);
    ret.addElement(a);

    return ret;
  }

  /**
   * Method that sets the whole enviroment of the MainPanel including
   * all GUI elements and reseting the graphPanel
   *
   * @param xMin
   * @param xMax
   * @param yMin
   * @param yMax
   * @param function
   */
  public void setEnvironment(double xMin, double xMax, double yMin, double yMax, double incr, String function)
  {
    // first we set all the GUI elements to this values
    xminTextF.setValue(xMin);
    xmaxTextF.setValue(xMax);
    yminTextF.setValue(yMin);
    ymaxTextF.setValue(yMax);
    incrTextF.setValue(incr);
    funcTextA.setText(function);

    // and now lets calculate the new graph
    ftemp = new String(function);
    Vector tempstrings = SplitString(function);

    tempstrings = SplitFunction(tempstrings);
    functionstrings = (Vector)tempstrings.elementAt(0);
    startvector = (Vector)tempstrings.elementAt(1);

    //System.out.println("Functions:  "+functionstrings);
    //System.out.println("startvector:"+startvector);

    gPanel.setCalcMethod((String)methodComboBox.getSelectedItem());
    gPanel.setFunction(functionstrings, startvector);
    gPanel.setView(xMin, xMax, yMin, yMax, incr, eps);

    checkTabbedPane();
    tabbedAction.stateChanged(new ChangeEvent(tabbedPane));
  }

  /**
   * <p>Title: Action subclass of the tabbed Pane</p>
   * <p>Description: This is a special inner class for Action handling of the tabbed Pane</p>
   * <p>Copyright: Copyright (c) 2002</p>
   * <p>Company: HTW-Dresden</p>
   * @author <A HREF="Jens.Langner@htw-dresden.de">Jens Langner</A>
   * @version 1.0
   */
  class TabbedPaneAction implements ChangeListener
  {
    public void stateChanged(ChangeEvent e)
    {
      int i = tabbedPane.getSelectedIndex()-1;
      if(i < 0) return;

      Vector resultVector = gPanel.getResultVector();

      if(resultVector == null || resultVector.size() == 0) return;

      int idxRes = ((Vector)resultVector.get(0)).size();

      // we get an instance of the PrintfFormat class so that
      // we can format the output to the Tabs
      PrintfFormat stepFormat = new PrintfFormat("[%4d] - ");
      PrintfFormat xFormat = new PrintfFormat("x: %-9.5g ");
      PrintfFormat yFormat = new PrintfFormat("y: %-9.5g ");

      // ok, we have to create new TextAreas for every tab
      JTextArea newArea = new JTextArea();
      newArea.setEditable(false);
      newArea.setForeground(Color.lightGray);
      newArea.setBackground(Color.black);
      newArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

      // lets add a Header to this TextArea
      newArea.append(res.getString("TXT_RESULTS_EQUATION")+"\n");
      newArea.append("y"+(i+1)+"="+functionstrings.get(i)+"\n");
      newArea.append(res.getString("TXT_RESULTS_WITH")+" y"+(i+1)+"[0]="+startvector.get(i)+"\n\n");
      newArea.append(" Step  - X-value      Y-value\n");
      newArea.append("-------------------------------------\n");

      // we fill the TextArea first
      for(int j=0; j < resultVector.size(); j++)
      {
        Point2D.Double currentPoint = (Point2D.Double)((Vector)resultVector.get(j)).get(i);

        newArea.append(stepFormat.sprintf(j)+xFormat.sprintf(currentPoint.getX())+yFormat.sprintf(currentPoint.getY())+"\n");
      }

      // lets get the scrollpane we need to add this area to
      JPanel currentScrollPane = (JPanel)tabbedVector.get(i);
      if(currentScrollPane.getComponentCount() > 0) currentScrollPane.remove(0);

      currentScrollPane.add(newArea, BorderLayout.CENTER);

      // we have to repaint the tabbedPane so that it will be refreshed.
      tabbedPane.repaint();
    }
  }
}