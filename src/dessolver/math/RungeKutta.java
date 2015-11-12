package dessolver.math;

import java.util.*;
import java.awt.geom.Point2D;
import dessolver.interpreter.*;

/**
 *
 * <p>Title: RungeKutta calculation</p>
 * <p>Description: Calculation of a integration with the RungeKutta variant</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: HTW-Dresden</p>
 * @author <A HREF="Juergen.Arndt@htw-dresden.de">Jürgen Arndt</A>
 * @author <A HREF="Felix.Kraemer@htw-dresden.de">Felix Krämer</A>
 * @author <A HREF="Jens.Langner@htw-dresden.de">Jens Langner</A>
 * @version 1.1
 */

public class RungeKutta extends MathCalc
{
  public static String IDENTIFIER = "RungeKutta4";

  public RungeKutta(Vector strings)
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

      while ( x < xf )
      {
        Vector y1 = new Vector(empty_Double);
        Vector y2 = new Vector(empty_Double);
        Vector y3 = new Vector(empty_Double);
        Vector k1 = new Vector(empty_Double);
        Vector k2 = new Vector(empty_Double);
        Vector k3 = new Vector(empty_Double);
        Vector k4 = new Vector(empty_Double);
        Vector resPoints = new Vector(empty_Point2D);

        // now we calculate k1 for every function
        for(int i=0; i < numFuncs; i++)
        {
          Parser parser = (Parser)parsers.elementAt(i);

          // k1 := fxy(x, y)
          double k1_tmp = calcDEQ(parser, x, y);
          k1.set(i, new Double(k1_tmp));
        }

        // x1 := x + h/2.0
        double x1 = x + h/2.0;

        // now we calculate y1 for every function
        for(int i=0; i < numFuncs; i++)
        {
          Parser parser = (Parser)parsers.elementAt(i);
          double y_tmp = ((Double)y.elementAt(i)).doubleValue();
          double k1_tmp = ((Double)k1.elementAt(i)).doubleValue();

          // y1 := y + k1*h/2.0
          double y1_tmp = y_tmp+k1_tmp*h/2.0;
          y1.set(i, new Double(y1_tmp));
        }

        // now we calculate k2 for every function
        for(int i=0; i < numFuncs; i++)
        {
          Parser parser = (Parser)parsers.elementAt(i);

          // k2 := fxy(x1, y1)
          double k2_tmp = calcDEQ(parser, x1, y1);
          k2.set(i, new Double(k2_tmp));
        }

        // x2 := x1
        double x2 = x1;

        // now we calculate y2 for every function
        for(int i=0; i < numFuncs; i++)
        {
          Parser parser = (Parser)parsers.elementAt(i);
          double y_tmp = ((Double)y.elementAt(i)).doubleValue();
          double k2_tmp = ((Double)k2.elementAt(i)).doubleValue();

          // y2 := y + k2*h/2.0;
          double y2_tmp = y_tmp+k2_tmp*h/2.0;
          y2.set(i, new Double(y2_tmp));
        }

        // now we calculate k3 for every function
        for(int i=0; i < numFuncs; i++)
        {
          Parser parser = (Parser)parsers.elementAt(i);

          // k3 := fxy(x2, y2);
          double k3_tmp = calcDEQ(parser, x2, y2);
          k3.set(i, new Double(k3_tmp));
        }

        // x3 := x + h
        double x3 = x + h;

        // now we calculate y3 for every function
        for(int i=0; i < numFuncs; i++)
        {
          Parser parser = (Parser)parsers.elementAt(i);
          double y_tmp = ((Double)y.elementAt(i)).doubleValue();
          double k3_tmp = ((Double)k3.elementAt(i)).doubleValue();

          // y3 := y + k3*h
          double y3_tmp = y_tmp+k3_tmp*h;
          y3.set(i, new Double(y3_tmp));
        }

        // now we calculate k4 for every function
        for(int i=0; i < numFuncs; i++)
        {
          Parser parser = (Parser)parsers.elementAt(i);

          // k4 := fxy(x3, y3)
          double k4_tmp = calcDEQ(parser, x3, y3);
          k4.set(i, new Double(k4_tmp));
        }

        // now we calculate the new y for every function
        for(int i=0; i < numFuncs; i++)
        {
          Parser parser = (Parser)parsers.elementAt(i);
          double y_tmp = ((Double)y.elementAt(i)).doubleValue();
          double k1_tmp = ((Double)k1.elementAt(i)).doubleValue();
          double k2_tmp = ((Double)k2.elementAt(i)).doubleValue();
          double k3_tmp = ((Double)k3.elementAt(i)).doubleValue();
          double k4_tmp = ((Double)k4.elementAt(i)).doubleValue();

          // y := y + (1.0/6.0)*(k1 + 2.0*k2 + 2.0*k3 + k4)*h
          y_tmp = y_tmp + (1.0/6.0)*(k1_tmp + 2.0*k2_tmp + 2.0*k3_tmp + k4_tmp)*h;
          y.set(i, new Double(y_tmp));

          // now lets add this point to the resultPoints so that we can return it
          resPoints.set(i, new Point2D.Double(x+h, y_tmp));

          //System.out.println("i:"+i+" x:"+(x+h)+" y:"+y_tmp);
        }

        // x := x + h
        x = x + h;

        ResultVector.addElement(resPoints);
      }

      return ResultVector;
   }
}