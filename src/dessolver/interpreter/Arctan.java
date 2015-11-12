package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Arctan.java
// Use  funktion fuer das ausfuehren des arcus tangens im baum
//-----------------------------------------------------------------------------

class Arctan implements OpFuncPointer
{
   public double exec (double a, double b)
   {
      return Math.atan(a);
   }
}
