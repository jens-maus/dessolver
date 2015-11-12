package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Sub.java
// Use  funktion fuer das ausfuehren der subtraktion im baum
//-----------------------------------------------------------------------------

class Sub implements OpFuncPointer
{
   public double exec (double a, double b)
   {
      return a-b;
   }
}
