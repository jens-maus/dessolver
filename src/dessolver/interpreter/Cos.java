package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Cos.java
// Use  funktion fuer das ausfuehren des cosinus im baum
//-----------------------------------------------------------------------------

class Cos implements OpFuncPointer
{
   public double exec (double a, double b)
   {
      return Math.cos(a);
   }
}
