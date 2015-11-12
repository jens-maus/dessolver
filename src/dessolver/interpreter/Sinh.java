package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Sinh.java
// Use  funktion fuer das ausfuehren des Sinhus  im baum
//-----------------------------------------------------------------------------

class Sinh implements OpFuncPointer
{
   public double exec (double a, double b)
   {
      return ( Math.exp(a) - Math.exp(-a) ) / 2;
   }
}
