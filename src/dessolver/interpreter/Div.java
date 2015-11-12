package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Div.java
// Use  funktion fuer das ausfuehren der Division im baum
//-----------------------------------------------------------------------------

class Div implements OpFuncPointer
{
   public double exec (double a, double b)
   {
      return a/b;
   }
}
