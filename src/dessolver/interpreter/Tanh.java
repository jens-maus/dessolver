package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Tanh.java
// Use  funktion fuer das ausfuehren des Tanhus  im baum
//-----------------------------------------------------------------------------

class Tanh implements OpFuncPointer
{
   public double exec (double a, double b)
   {
      return ( Math.exp(a) - Math.exp(-a) ) / ( Math.exp(a) + Math.exp(-a) );
   }
}
