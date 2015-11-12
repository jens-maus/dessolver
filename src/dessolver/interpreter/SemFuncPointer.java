package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File SemFuncPointer.java
// Use  interface fuer semantische funktionen
//-----------------------------------------------------------------------------

interface SemFuncPointer extends Const
{
   public int exec ();
   public int exec (double a);
   public int exec (String str);
}

