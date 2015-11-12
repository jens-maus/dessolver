package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Ln.java
// Use  funktion fuer das ausfuehren des natuerlichen logarithmus' im baum
//-----------------------------------------------------------------------------

class Ln implements OpFuncPointer
{
   public double exec (double a, double b)
   {
      return Math.log(a);
   }
}
