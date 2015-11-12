package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Sin.java
// Use  funktion fuer das ausfuehren des sinus  im baum
//-----------------------------------------------------------------------------

class Sin implements OpFuncPointer
{
   public double exec (double a, double b)
   {
      return Math.sin(a);
   }
}
