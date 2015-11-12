package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac20.java
// Use  semantische funktion fuer beenden des Pow
//-----------------------------------------------------------------------------

class Fac20 implements SemFuncPointer
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
      //System.out.println ("\n   Fac20: ende des Pow!");

      Node K;

      K = (Node) stack.peek();

      if ( K.GetInfoString().equals ("Pow-anfang") )
      {
         K.rang = 2; // man moege mir das verzeihen
         stack.pop();
         //System.out.println ("\n   Fac20: Pow  wurde entfernt!\n");
      }
      else
      {
         System.out.println ("\n   Fac20: da steht kein Pow!\n");
         System.exit (-1);
      }

      return 1;
   }
}

