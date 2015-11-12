package dessolver.math;

import java.util.*;
import java.awt.geom.Point2D;
import dessolver.interpreter.*;

/**
 * <p>Title: Mathematical calculations</p>
 * <p>Description: Abstract class for the different mathematical equations</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: HTW-Dresden</p>
 * @author <A HREF="Jens.Langner@htw-dresden.de">Jens Langner</A>
 * @version 1.1
 */

public abstract class MathCalc
{
   public static String IDENTIFIER;

   protected Vector parsers = new Vector(1,1);
   protected int numFuncs;
   protected Vector ResultVector;
   protected double x;
   protected Vector y;
   protected Vector empty_Double = new Vector(1,1);
   protected Vector empty_Point2D = new Vector(1,1);

   /**
    * Constructor to initialize the Lexer and Parser with all functions
    * in a vector.
    *
    * @param strings a Vector of functions
    */
   public MathCalc(Vector strings)
   {
      // we have to clear this first.
      Parser.vIdent.clear();

      Enumeration e=strings.elements();
      while(e.hasMoreElements())
      {
        Graph g = new Graph();
        Parser p = new Parser();
        p.init();
        String str = ((String)e.nextElement()).concat("\0");
        //System.out.println("MathCalc(): "+str);
        Lex.Init(str);
        p.parse(g.getGraph(0));
        parsers.addElement(p);

        empty_Double.add(new Double(0.0));
        empty_Point2D.add(new Point2D.Double());

        numFuncs++;
      }
   }

   /**
    * Method that return the identifing string of the calculation
    * method.
    *
    * @return a reference to a String
    */
   public String toString()
   {
      return IDENTIFIER;
   }

   /**
    * Abstract method to calculate all the values of the initialized functions
    * in a vector and return it
    *
    * @param x0 the starting point at the function
    * @param xf the end point of the function
    * @param startvector a vector full of function we have to calculate the values for
    * @param h the starting offset we step forward in the calculation
    * @return a Vector which includes vectors of the calculation results.
   */
  public abstract Vector calcValue(double x0, double xf, Vector startvector, double h, double eps);


   /**
    * Method that starts a differential equation where we first create
    * a vector with all variables and pass them to the parsers calculation method
    *
    * @param parser reference to a Parser object
    * @param x a double value of x for which we want to get the result
    * @param yVector a vector with all y[n] values from y1....yN
    * @return the value of that position
    */
   protected double calcDEQ(Parser parser, double x, Vector yVector)
   {
      // now we have to put all variables in one Vector
      // with the identifier and the value
      Vector calcVector = new Vector();

      // first we add x to the vector
      calcVector.add(new Ident("x", x));

      // and now lets all the y values
      for(int i=0; i < yVector.size(); i++)
      {
        calcVector.add(new Ident("y"+(i+1), ((Double)yVector.elementAt(i)).doubleValue()));
      }

      // now lets calculate the value
      return parser.calc(calcVector);
   }

   /**
    * Method to initialize the ResultVector by adding x0 and y0 at the
    * start of the Vector, because here all equations begin.
    *
    * @param x the x0 value
    * @param y a vector with all y0
    */
   protected void initResultVector(double x, Vector y)
   {
      this.x = x;
      this.y = new Vector(y);

      // we have to insert the first x0,y0 into the resultVector
      ResultVector = new Vector(2,1);
      Vector firstPoints = new Vector(empty_Point2D);
      for(int i=0; i < y.size(); i++)
      {
        double y_tmp = ((Double)y.elementAt(i)).doubleValue();
        firstPoints.set(i, new Point2D.Double(x, y_tmp));
      }
      ResultVector.addElement(firstPoints);
   }
}