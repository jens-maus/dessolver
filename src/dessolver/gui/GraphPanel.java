package dessolver.gui;

import java.io.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.applet.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.net.*;

import dessolver.math.*;

/**
 * <p>Title: Graph Panel Component of the DESSolver Applet</p>
 * <p>Description: This panel represents the graph</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: HTW-Dresden</p>
 * @author <A HREF="Jens.Langner@htw-dresden.de">Jens Langner</A>
 * @author <A HREF="Felix.Kraemer@htw-dresden.de">Felix Kraemer</A>
 * @version 1.2
 */

public class GraphPanel extends JPanel
{
  public Color [] colors={ Color.yellow, Color.blue,
                           Color.cyan, Color.green,
                           Color.magenta, Color.orange,
                           Color.pink, Color.red,
                           Color.lightGray, Color.white,
                           Color.gray, Color.darkGray };

  private int fontSize  = 10;
  private int width     = 0;
  private int height    = 0;
  private boolean recalc = false;

  // The default values for the graph.
  private double xMin;
  private double xMax;
  private double yMin;
  private double yMax;
  private double incr;
  private double eps;
  private Vector functionVector;
  private Vector startVector;
  private double y0;
  private double x0;

  private boolean x_visible=true;
  private boolean y_visible=true;

  private double y_stretchfactor;
  private double x_stretchfactor;

  // get a reference of the calculation
  private MathCalc calcMethod;
  private String calcMethodID;
  private Vector resultVector = null;

  public GraphPanel(double xMin, double xMax, double yMin, double yMax, double incr, double eps, Vector functionVector, Vector startVector)
  {
    this.xMin = xMin;
    this.xMax = xMax;
    this.yMin = yMin;
    this.yMax = yMax;
    this.incr = incr;
    this.eps = eps;
    this.functionVector = functionVector;
    this.startVector = startVector;

    setLayout(new GridLayout(1,0));

    // now lets instanticate the RungeKutta derivation
    // and calculate the first stuff as the default calculation method
    calcMethodID = RungeKutta.IDENTIFIER;
    calcMethod = (MathCalc)new RungeKutta(functionVector);
    resultVector = calcMethod.calcValue(xMin, xMax, new Vector(startVector), incr, eps);
  }

  public Vector getResultVector()
  {
    return resultVector;
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);

    // synchronize the width & height
    width = getWidth();
    height = getHeight();

    y_stretchfactor = height/(yMax-yMin); // Stretchfactor towards y
    x_stretchfactor = width/(xMax-xMin);  // Stretchfactor towards x

    // create an image to avoid flicks
    Image image = createImage(width, height);
    Graphics g2 = image.getGraphics();

    // lets draw the entire area black
    g2.setColor(Color.black);
    g2.fillRect(0, 0, width, height);

    // Lets draw the Axes and Tics of them now
    drawAxes(g2);
    drawTics(g2);

    // now we draw the graph of the equation
    drawGraph(g2);

    g.drawImage(image, 0, 0, this);
  }

  /**
   * Method that draws the X and Y Axis of the Graph if they are
   * in the visible area depending on the graph values.
   *
   * @param g reference to the graphics area we can paint at.
   */
  private void drawAxes(Graphics g)
  {
    g.setColor(Color.white);

    // only if we want to display this function within
    // a visible area we display the X-Axis
    if(yMin <= 0 && yMax >= 0)
    {
      x_visible = true;
      y0 = (Math.abs(yMax)*height)/(yMax-yMin);
      g.drawLine(0, (int)y0, width, (int)y0); // draw the X-Axis
    }
    else
    {
      x_visible = false;
      y0 = height+(yMin*y_stretchfactor);
    }

    // only if we want to display this function within
    // a visible area we display the Y-Axis
    if(xMin <= 0 && xMax >= 0)
    {
      y_visible = true;
      x0 = (Math.abs(xMin)*width)/(xMax-xMin);
      g.drawLine((int)x0, 0, (int)x0, height);  // draw the Y-Axis
    }
    else
    {
      y_visible = false;
      x0 = 0-(xMin*x_stretchfactor);
    }
  }

  /**
   * Method that draws the Tics within a passed area of Graphics
   *
   * @param g reference to a Graphics Area where we can paint at
   */
  private void drawTics(Graphics g)
  {
    g.setColor(Color.lightGray);
    g.setFont(new Font("Helvetica", Font.PLAIN, fontSize));

    // lets draw the Tics for the X-Axis
    int xpos = (int)xMin;

    for(double x=0, last_pos=0, last_pos_size=0; x <= width; x+=x_stretchfactor, xpos++)
    {
      // check if we have enough room for drawing the line & string
      if(x-last_pos > (fontSize*last_pos_size)+1 || x==0)
      {
        if(x_visible)
        {
          g.drawLine((int)x, (int)y0-2, (int)x, (int)y0+2);
          g.drawString(String.valueOf(xpos), (int)x+1, y0!=height ? (int)y0+fontSize : height);
        }
        else
        {
          if(yMin > 0) g.drawString(String.valueOf(xpos), (int)x+1, height);
          if(yMin < 0) g.drawString(String.valueOf(xpos), (int)x+1, 0+fontSize);
        }
        last_pos = x;
        last_pos_size = String.valueOf(xpos).length();
      }
    }

    // lets draw the Tics for the Y-Axis
    int ypos = (int)yMax;

    for(double y=0, last_pos=0; y <= height; y+=y_stretchfactor, ypos--)
    {
      // check if we have enough room for drawing the line & string
      if(y-last_pos > fontSize+1 || y==0)
      {
        if(y_visible)
        {
          g.drawLine((int)x0-2, (int)y, (int)x0+2, (int)y);
          g.drawString(String.valueOf(ypos), x0!=0 ? (int)x0-fontSize+1 : 1, (int)y);
        }
        else
        {
          if(xMin > 0) g.drawString(String.valueOf(ypos), 0+1, (int)y);
          if(xMin < 0) g.drawString(String.valueOf(ypos), width-fontSize+1, (int)y);
        }
        last_pos = y;
      }
    }
  }

  /**
   * Method that draws the graph out of the resultVector
   *
   * @param g the graphics area we can draw the graph at
   */
  private void drawGraph(Graphics g)
  {
    // if the width/height of the window has changed
    // we have to recalculate our graph, otherwise just use the old data.
    if(recalc)
    {
      resultVector = calcMethod.calcValue(xMin, xMax, new Vector(startVector), incr, eps);
      recalc = false;
    }

    // if the resultVector is empty or null we do nothing here
    if(resultVector == null || resultVector.size() == 0)
    {
      return;
    }

    // now lets draw the Graphs
    int s=((Vector)resultVector.elementAt(0)).size();

    for(int j=0, k=0; j < s; j++, k=0)
    {
      g.setColor(colors[j%11]);

      // now lets get the first value so that we can draw a whole line
      double x1 = x0+(((Point2D.Double)((Vector)resultVector.elementAt(0)).elementAt(j)).getX())*x_stretchfactor;
      double y1 = y0-(((Point2D.Double)((Vector)resultVector.elementAt(0)).elementAt(j)).getY())*y_stretchfactor;

      // lets draw the graph we actually have
      for(int x=1; x < resultVector.size(); x++)
      {
        // System.out.println("x: "+(((Point2D.Double)((Vector)resultVector.elementAt(x)).elementAt(j)).getX())+" y:"+(((Point2D.Double)((Vector)resultVector.elementAt(x)).elementAt(j)).getY()));

        double x2 = x0+(((Point2D.Double)((Vector)resultVector.elementAt(x)).elementAt(j)).getX())*x_stretchfactor;
        double y2 = y0-(((Point2D.Double)((Vector)resultVector.elementAt(x)).elementAt(j)).getY())*y_stretchfactor;

        // lets draw the line regardless if we draw outside because
        // java will care anyway.
        g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);

        if(k == 0 && x2 > width/8)
        {
          g.drawString("y"+(j+1)+"` = "+((String)functionVector.get(j)), (int)x2+10, (int)y2+10);
          k=1;
        }

        y1 = y2;
        x1 = x2;
      }

    }
  }

  /**
   * Method that sets the environment of the Graph and repaints it
   * accordingly
   *
   * @param xMin X-minumum of the graph
   * @param xMax X-maximum of the graph
   * @param yMin Y-minimum of the graph
   * @param yMax Y-maximum of the graph
   */
  public void setView(double xMin, double xMax, double yMin, double yMax, double incr, double eps)
  {
    this.xMin = xMin;
    this.xMax = xMax;
    this.yMin = yMin;
    this.yMax = yMax;
    this.incr = incr;
    this.eps = eps;

    resultVector = calcMethod.calcValue(xMin, xMax, new Vector(startVector), incr, eps);

    recalc = false;

    repaint();
  }

  /**
   * Method to set a new function that should be printed at
   * this graph Panel
   *
   * @param function a String containing the full Differentional Equation like "4*exp(0.8*x)-0.5*y"
   */
  public void setFunction(Vector functionVector, Vector startVector)
  {
    this.functionVector = functionVector;
    this.startVector = startVector;

    // lets check if we have to change the Calculation Method and do it
    if(calcMethodID.compareTo(RungeKutta.IDENTIFIER) == 0)
    {
      calcMethod = (MathCalc)new RungeKutta(functionVector);
    }
    else if(calcMethodID.compareTo(RungeKuttaInc.IDENTIFIER) == 0)
    {
      calcMethod = (MathCalc)new RungeKuttaInc(functionVector);
    }
    else if(calcMethodID.compareTo(Euler.IDENTIFIER) == 0)
    {
      calcMethod = (MathCalc)new Euler(functionVector);
    }
    else if(calcMethodID.compareTo(EulerModified.IDENTIFIER) == 0)
    {
      calcMethod = (MathCalc)new EulerModified(functionVector);
    }
    else if(calcMethodID.compareTo(Heun.IDENTIFIER) == 0)
    {
      calcMethod = (MathCalc)new Heun(functionVector);
    }
    else if(calcMethodID.compareTo(DormandPrince.IDENTIFIER) == 0)
    {
      calcMethod = (MathCalc)new DormandPrince(functionVector);
    }

    recalc = true;
  }

  /**
   * Method to set the calcMethod Identifier to a specific value.
   *
   * @param calcMethodString
   * @return true of we set it and false if it already was the same
   */
  public boolean setCalcMethod(String calcMethodString)
  {
    if(calcMethodID == null || calcMethodID.compareTo(calcMethodString) != 0)
    {
      calcMethodID = calcMethodString;
      return true;
    }

    return false;
  }

  /**
   * Method that returns true if the selected calculation method
   * support error tolerance
   *
   * @return true/false depending on the selected method
   */
  public boolean hasMethodErrorCorr()
  {
    if(calcMethodID == Heun.IDENTIFIER ||
       calcMethodID == DormandPrince.IDENTIFIER)
    {
      return true;
    }

    return false;
  }
}