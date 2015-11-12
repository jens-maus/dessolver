package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Add.java
// Use  funktion fuer das ausfuehren einer zahl im baum - natuerlich nur
//         eine dummy-funktion
//-----------------------------------------------------------------------------

class Num implements OpFuncPointer
{
   public double exec (double a, double b)
   {
      return a;
   }
}
