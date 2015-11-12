package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Cot.java
// Use  funktion fuer das ausfuehren des Cotgens im baum
//-----------------------------------------------------------------------------

class Cot implements OpFuncPointer
{
   public double exec (double a, double b)
   {
      return 1/Math.tan(a);
   }
}
