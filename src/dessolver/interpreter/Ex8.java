package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Ex7.java
// Use  semantische funktion zum beenden der expression
//-----------------------------------------------------------------------------

class Ex8 implements SemFuncPointer
{
   public int exec ()
   {
      //System.out.println ("\n   *** semantische funktion Ex8 - expression zuende! ***\n");
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
