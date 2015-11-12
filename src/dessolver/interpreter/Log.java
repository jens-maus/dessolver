package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Log.java
// Use  funktion fuer das ausfuehren des dekadischen logarithmus' im baum
//-----------------------------------------------------------------------------

class Log implements OpFuncPointer
{
   public double exec (double a, double b)
   {
      return Math.log(a)/Math.log(10);
   }
}
