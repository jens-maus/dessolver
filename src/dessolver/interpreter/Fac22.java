package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac22.java
// Use  semantische funktion fuer beenden des Abs
//-----------------------------------------------------------------------------

class Fac22 implements SemFuncPointer
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
      //System.out.println ("\n   Fac22: ende des Abs!");

      Node K;

      K = (Node) stack.peek();

      if ( K.GetInfoString().equals ("Abs-anfang") )
      {
         stack.pop();
         //System.out.println ("\n   Fac22: Abs  wurde entfernt!\n");
      }
      else
      {
         System.out.println ("\n   Fac22: da steht kein Abs!\n");
         System.exit (-1);
      }

      return 1;
   }
}

