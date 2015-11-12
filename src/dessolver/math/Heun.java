package dessolver.math;

import java.util.*;
import java.awt.geom.Point2D;
import dessolver.interpreter.*;

/**
 * <p>Title: Heun Differential Equation Solver Method</p>
 * <p>Description: class that solves differential Equations with the standard Heun method</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: HTW-Dresden</p>
 * @author <A HREF="Jens.Langner@htw-dresden.de">Jens Langner</A>
 * @version 1.0
 */

public class Heun extends MathCalc
{
  public static String IDENTIFIER = "Heun";

  private final int imax = 100;

  public Heun(Vector strings)
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
      Vector resPoints = new Vector(empty_Point2D);
      Vector y1 = new Vector(empty_Double);
      Vector y2 = new Vector(empty_Double);
      Vector s = new Vector(empty_Double);
      Vector s1 = new Vector(empty_Double);

      // lets calculate s for every function
      for(int i=0; i < numFuncs; i++)
      {
        Parser parser = (Parser)parsers.elementAt(i);

        // s := fxy(x, y);
        double s_tmp = calcDEQ(parser, x, y);
        s.set(i, new Double(s_tmp));
      }

      // x1 := x + h;
      double x1 = x + h;

      // lets calculate y1 for every function
      for(int i=0; i < numFuncs; i++)
      {
        double y_tmp = ((Double)y.elementAt(i)).doubleValue();
        double s_tmp = ((Double)s.elementAt(i)).doubleValue();

        // y1 := y + s*h;
        double y1_tmp = y_tmp + s_tmp * h;
        y1.set(i, new Double(y1_tmp));
      }

      // now we process the correct step
      for(int j=0; j < numFuncs; j++)
      {
        Parser parser = (Parser)parsers.elementAt(j);
        double y_tmp = ((Double)y.elementAt(j)).doubleValue();
        double s_tmp = ((Double)s.elementAt(j)).doubleValue();
        double y2_tmp = 0;

        // i := 0
        int i=0;

        // del := 2.0*eps
        double del = 2.0*eps;

        while(del > eps && i < imax)
        {
          // i := i+1
          i = i + 1;

          // s1 := fxy(x1, y1);
          double s1_tmp = calcDEQ(parser, x1, y1);
          s1.set(j, new Double(s1_tmp));

          // y2 := y + h*(s + s1)/2.0;
          y2_tmp = y_tmp + h*(s_tmp + s1_tmp)/2.0;
          y2.set(j, new Double(y2_tmp));

          // del := abs((y2 - y)/y2);
          del = Math.abs((y2_tmp - y_tmp)/y2_tmp);

          // y1 := ynew
          y1.set(j, new Double(y2_tmp));
        }

        // now lets add this point to the resultPoints so that we can return it
        resPoints.set(j, new Point2D.Double(x1, y2_tmp));

        if(del > eps)
        {
          System.err.println("The error corrections for the Heun Method didn't work! (del:"+del+" > eps:"+eps+")");
        }
      }

      // x := x1;
      x = x1;

      // y := y2
      y = y2;

      ResultVector.addElement(resPoints);
    }

    return ResultVector;
  }
}