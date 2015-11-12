package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Mul.java
// Use  funktion fuer das ausfuehren der multiplikation im baum
//-----------------------------------------------------------------------------

class Mul implements OpFuncPointer
{
   public double exec (double a, double b)
   {
      return a*b;
   }
}
