package dessolver.math;

import java.util.*;
import java.awt.geom.Point2D;
import dessolver.interpreter.*;

/**
 * <p>Title: Euler Differential Equation Solver Method</p>
 * <p>Description: class that solves differential Equations with the standard Euler method</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: HTW-Dresden</p>
 * @author <A HREF="Jens.Langner@htw-dresden.de">Jens Langner</A>
 * @version 1.0
 */

public class Euler extends MathCalc
{
  public static String IDENTIFIER = "Euler";

  public Euler(Vector strings)
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

      for(int i=0; i < numFuncs; i++)
      {
        Parser parser = (Parser)parsers.elementAt(i);
        double y_tmp = ((Double)y.elementAt(i)).doubleValue();

        // y := y + h*fxy(x, y);
        y_tmp = y_tmp + h * calcDEQ(parser, x, y);
        y.set(i, new Double(y_tmp));

        // now lets add this point to the resultPoints so that we can return it
        resPoints.set(i, new Point2D.Double(x+h, y_tmp));
      }

      // x := x + h;
      x = x + h;

      ResultVector.addElement(resPoints);
    }

    return ResultVector;
  }
}