package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Abs.java
// Use  funktion fuer das ausfuehren des abs im baum
//-----------------------------------------------------------------------------

class Abs implements OpFuncPointer
{
   public double exec (double a, double b)
   {
      return Math.abs(a);
   }
}
