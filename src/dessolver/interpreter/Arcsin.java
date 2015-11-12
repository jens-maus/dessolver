package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Arcsin.java
// Use  funktion fuer das ausfuehren des arcus sinus im baum
//-----------------------------------------------------------------------------

class Arcsin implements OpFuncPointer
{
   public double exec (double a, double b)
   {
      return Math.asin(a);
   }
}
