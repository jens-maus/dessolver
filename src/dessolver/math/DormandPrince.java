package dessolver.math;

import java.util.*;
import java.awt.geom.Point2D;
import javax.swing.JOptionPane;
import dessolver.interpreter.*;

/**
 * <p>Title: Dormand Prince Differential Equation Solver Method</p>
 * <p>Description: class that solves differential Equations with the Dormand Prince method</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: HTW-Dresden</p>
 * @author <A HREF="Jens.Langner@htw-dresden.de">Jens Langner</A>
 * @version 1.0
 */

public class DormandPrince extends MathCalc
{
  public static String IDENTIFIER = "Dormand Prince";

  // lets initialize the help matrixes and variables
  private final double a[][] = { {            0.0,             0.0,            0.0,          0.0,             0.0,       0.0, 0.0 },
                                 {            0.2,             0.0,            0.0,          0.0,             0.0,       0.0, 0.0 },
                                 {       3.0/40.0,        9.0/40.0,            0.0,          0.0,             0.0,       0.0, 0.0 },
                                 {      44.0/45.0,      -56.0/15.0,       32.0/9.0,          0.0,             0.0,       0.0, 0.0 },
                                 { 19372.0/6561.0, -25360.0/2187.0, 64448.0/6561.0, -212.0/729.0,             0.0,       0.0, 0.0 },
                                 {  9017.0/3168.0,     -355.0/33.0, 46732.0/5247.0,   49.0/176.0, -5103.0/18656.0,       0.0, 0.0 },
                                 {     35.0/384.0,             0.0,   500.0/1113.0,  125.0/192.0,  -2187.0/6784.0, 11.0/84.0, 0.0 }
                               };
  private final double b[] = { 71.0/57600.0, 0.0, -71.0/16695.0, 71.0/1920.0, -17253.0/339200.0, 22.0/525.0, -1.0/40.0 };
  private final double c[] = { 0.0, 0.2, 3.0/10.0, 0.8, 8.0/9.0, 1.0, 1.0 };

  public DormandPrince(Vector strings)
  {
    super(strings);
  }

  public Vector calcValue(double x0, double xf, Vector startvector, double h, double eps)
  {
    // if (xf-x0)*h < 0 we cannot reach the result
    // also if h == 0 we have to return
    if((xf-x0)*h < 0 || h <= 0 || eps < 0) return null;

    // we always have to init the ResultVector first
    initResultVector(x0, startvector);

    //System.out.println(IDENTIFIER+": "+x0+"  "+xf+"  "+h+" ysize: "+y.elementAt(0));

    double uround = 1.0E-6;

    // lets initialize the uround variable
    while((1+uround) > 1) uround = uround*0.1;

    boolean fail = false;
    boolean accept = true;
    int maxit = 0;

    do
    {
      Vector y1  = new Vector(empty_Double);
      Vector resPoints = new Vector(empty_Point2D);
      double error = 0.0;

      // now we calculate y1 for every function
      for(int i=0; i < numFuncs; i++)
      {
        Parser parser = (Parser)parsers.elementAt(i);
        double y_tmp = ((Double)y.elementAt(i)).doubleValue();
        double dy_tmp = 0.0;
        double dy0_tmp = 0.0;
        double k[] = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
        Vector yV_tmp = new Vector(y);

        for(int j=0; j < 7; j++)
        {
          // dy0 := 0.0
          dy0_tmp = 0.0;

          for(int m=0; m < j; m++)
          {
            // dy0 := dy0 + a[j,i]*k[i]
            dy0_tmp = dy0_tmp + a[j][m]*k[m];
            //System.out.println(dy0_tmp);
          }

          //System.out.println(dy0_tmp);

          // k[j] := fxy(x + c[j]*h, y + h*dy0)
          yV_tmp.set(i, new Double(y_tmp + h*dy0_tmp));
          //System.out.println("x:"+x+" c:"+c[j]+" y:"+y_tmp+" h:"+h+" dy0:"+dy_tmp);
          k[j] = calcDEQ(parser, x + c[j]*h, yV_tmp);
          //System.out.println("k:"+k[j]+" h:"+h);

          // dy   := dy + b[j]*k[j]
          //System.out.println("k["+j+"]:"+k[j]+" b:"+b[j]+" k*b:"+k[j]*b[j]);
          dy_tmp = dy_tmp + b[j]*k[j];
        }

        // System.out.println("x:"+x+" dy:"+dy_tmp+" dy0:"+dy0_tmp+" h:"+h);

        // y1 := y + h*dy0
        double y1_tmp = y_tmp + h*dy0_tmp;
        y1.set(i, new Double(y1_tmp));

        // now lets calculate the error
        double nenner = 1.0E-5 > Math.abs(y1_tmp) ? 1.0E-5 : Math.abs(y1_tmp);

        if(Math.abs(y_tmp) > nenner) nenner = Math.abs(y_tmp);

        if(2*uround/eps > nenner) nenner = 2*uround/eps;

        // fehler := abs(h*dy/nenner);
        //System.out.println("h:"+h+" nenner:"+nenner);
        error = error + Math.abs(h*dy_tmp/nenner);
      }

      // we have to divide the error, so that we get the overall error
      //System.out.println("error1:"+error);
      error = error/numFuncs;
      //System.out.println("error2:"+error);

      // lets calculate the factor now
      double factor = 0.0;

      if(5.0 < (1.1*Math.exp((1/5)*Math.log(error/eps))))
      {
        factor = 5.0;
      }
      else
      {
        factor = 1.1*Math.exp((1/5)*Math.log(error/eps));
      }

      if(1.0 > factor)
      {
        factor = 1.0;
      }

      // lets now calculate the new increment
      // hneu   := h/faktor;
      double h_new = h/factor;

      //System.out.println("x:"+x+" error: "+error+" eps:"+eps);

      if(error < eps)
      {
        // y := y1
        y = y1;

        // x := x + h
        x = x + h;

        // now lets add this point to the resultPoints so that we can return it
        for(int i=0; i < numFuncs; i++)
        {
          Parser parser = (Parser)parsers.elementAt(i);
          double y_tmp = ((Double)y.elementAt(i)).doubleValue();
          resPoints.set(i, new Point2D.Double(x, y_tmp));
        }

        ResultVector.addElement(resPoints);

        if(accept == false && h < h_new)
        {
          h_new = h;
        }

        accept = true;
        maxit = 0;
      }
      else
      {
        accept = false;
        maxit = maxit + 1;
        fail = ((maxit == 20) || (x + 0.1*h == x));
      }

      h = h_new;

    }while(x < xf && fail == false);

    if(fail == true)
    {
      ResourceBundle res = ResourceBundle.getBundle("dessolver");
      JOptionPane.showMessageDialog(null, res.getString("ERR_DORMAND_FAILED"), res.getString("TXT_ERROR"), JOptionPane.ERROR_MESSAGE);
    }

    return ResultVector;
  }
}