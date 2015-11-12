package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Cosh.java
// Use  funktion fuer das ausfuehren des Coshus  im baum
//-----------------------------------------------------------------------------

class Cosh implements OpFuncPointer
{
   public double exec (double a, double b)
   {
      return ( Math.exp(a) + Math.exp(-a) ) / 2;
   }
}
