package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac30.java
// Use  semantische funktion fuer beenden des Fac
//-----------------------------------------------------------------------------

class Fac30 implements SemFuncPointer
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
      //System.out.println ("\n   Fac30: ende des Fac!");

      Node K;

      K = (Node) stack.peek();

      if ( K.GetInfoString().equals ("Fac-anfang") )
      {
         stack.pop();
         //System.out.println ("\n   Fac30: Fac  wurde entfernt!\n");
      }
      else
      {
         System.out.println ("\n   Fac30: da steht kein Fac!\n");
         System.exit (-1);
      }

      return 1;
   }
}

