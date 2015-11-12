package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Add.java
// Use  funktion fuer das ausfuehren der addition im baum
//-----------------------------------------------------------------------------

class Add implements OpFuncPointer
{
   public double exec (double a, double b)
   {
      return a+b;
   }
}
