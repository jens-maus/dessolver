package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Coth.java
// Use  funktion fuer das ausfuehren des Cothus  im baum
//-----------------------------------------------------------------------------

class Coth implements OpFuncPointer
{
   public double exec (double a, double b)
   {
      return ( Math.exp(a) + Math.exp(-a) ) / ( Math.exp(a) - Math.exp(-a) );
   }
}
