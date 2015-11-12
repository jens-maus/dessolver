package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac36.java
// Use  semantische funktion fuer beenden des Tanh
//-----------------------------------------------------------------------------

class Fac36 implements SemFuncPointer
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
      //System.out.println ("\n   Fac36: ende des Tanh!");

      Node K;

      K = (Node) stack.peek();

      if ( K.GetInfoString().equals ("Tanh-anfang") )
      {
         stack.pop();
         //System.out.println ("\n   Fac36: Tanh  wurde entfernt!\n");
      }
      else
      {
         System.out.println ("\n   Fac36: da steht kein Tanh!\n");
         System.exit (-1);
      }

      return 1;
   }
}

