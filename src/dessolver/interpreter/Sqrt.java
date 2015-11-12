package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Sqrt.java
// Use  funktion fuer das ausfuehren der wurzel im baum
//-----------------------------------------------------------------------------

class Sqrt implements OpFuncPointer
{
   public double exec (double a, double b)
   {
      return Math.sqrt(a);
   }
}
