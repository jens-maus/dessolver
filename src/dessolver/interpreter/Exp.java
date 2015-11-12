package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Sqrt.java
// Use  funktion fuer das ausfuehren der e-funktion im baum
//-----------------------------------------------------------------------------

class Exp implements OpFuncPointer
{
   public double exec (double a, double b)
   {
      return Math.exp(a);
   }
}
