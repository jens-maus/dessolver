package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Tan.java
// Use  funktion fuer das ausfuehren des tangens im baum
//-----------------------------------------------------------------------------

class Tan implements OpFuncPointer
{
   public double exec (double a, double b)
   {
      return Math.tan(a);
   }
}
