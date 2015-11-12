package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Pow.java
// Use  funktion fuer das ausfuehren von x hoch y
//-----------------------------------------------------------------------------

class Pow implements OpFuncPointer
{
   public double exec (double a, double b)
   {
      return Math.pow(a, b);
   }
}
