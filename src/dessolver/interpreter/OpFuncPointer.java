package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File OpFuncPointer.java
// Use  funktionspointer fuer rechenoperationen der knoten im baum
//-----------------------------------------------------------------------------

interface OpFuncPointer extends Const
{
   double exec (double a, double b);
}
