package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac28.java
// Use  semantische funktion fuer beenden des Arctan
//-----------------------------------------------------------------------------

class Fac28 implements SemFuncPointer
{
   public int exec (double a)
   {
      return 1;
   }

   public int exec (String str)
   {
      return 1;
   }

   public int exec ()
   {
      //System.out.println ("\n   Fac28: ende des Arctan!");

      Node K;

      K = (Node) stack.peek();

      if ( K.GetInfoString().equals ("Arctan-anfang") )
      {
         stack.pop();
         //System.out.println ("\n   Fac28: Arctan  wurde entfernt!\n");
      }
      else
      {
         System.out.println ("\n   Fac28: da steht kein Arctan!\n");
         System.exit (-1);
      }

      return 1;
   }
}

