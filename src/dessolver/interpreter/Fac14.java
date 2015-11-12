package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac14.java
// Use  semantische funktion fuer beenden des Exp
//-----------------------------------------------------------------------------

class Fac14 implements SemFuncPointer
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
      //System.out.println ("\n   Fac14: ende des Exp!");

      Node K;

      K = (Node) stack.peek();

      if ( K.GetInfoString().equals ("Exp-anfang") )
      {
         stack.pop();
         //System.out.println ("\n   Fac14: Exp  wurde entfernt!\n");
      }
      else
      {
         System.out.println ("\n   Fac14: da steht kein Exp!\n");
         System.exit (-1);
      }

      return 1;
   }
}

