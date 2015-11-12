package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac40.java
// Use  semantische funktion fuer beenden des Cot
//-----------------------------------------------------------------------------

class Fac40 implements SemFuncPointer
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
      //System.out.println ("\n   Fac40: ende des Cot!");

      Node K;

      K = (Node) stack.peek();

      if ( K.GetInfoString().equals ("Cot-anfang") )
      {
         stack.pop();
         //System.out.println ("\n   Fac40: Cot  wurde entfernt!\n");
      }
      else
      {
         System.out.println ("\n   Fac40: da steht kein Cot!\n");
         System.exit (-1);
      }

      return 1;
   }
}

