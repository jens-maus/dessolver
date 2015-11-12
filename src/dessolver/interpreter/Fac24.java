package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac24.java
// Use  semantische funktion fuer beenden des Arcsin
//-----------------------------------------------------------------------------

class Fac24 implements SemFuncPointer
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
      //System.out.println ("\n   Fac24: ende des Arcsin!");

      Node K;

      K = (Node) stack.peek();

      if ( K.GetInfoString().equals ("Arcsin-anfang") )
      {
         stack.pop();
         //System.out.println ("\n   Fac24: Arcsin  wurde entfernt!\n");
      }
      else
      {
         System.out.println ("\n   Fac24: da steht kein Arcsin!\n");
         System.exit (-1);
      }

      return 1;
   }
}

