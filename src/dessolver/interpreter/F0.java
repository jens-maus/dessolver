package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File F0.java
// Use  semantische dummyfunktion
//-----------------------------------------------------------------------------

class F0 implements SemFuncPointer
{
   public int exec ()
   {
      return 1;
   }
   public int exec (String str)
   {
      return 1;
   }
   public int exec (double a)
   {
      return 1;
   }
}
