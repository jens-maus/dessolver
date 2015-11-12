package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac12.java
// Use  semantische funktion fuer beenden des Sqrt
//-----------------------------------------------------------------------------

class Fac12 implements SemFuncPointer
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
      //System.out.println ("\n   Fac12: ende des Sqrt!");

      Node K;

      K = (Node) stack.peek();

      if ( K.GetInfoString().equals ("Sqrt-anfang") )
      {
         stack.pop();
         //System.out.println ("\n   Fac12: Sqrt  wurde entfernt!\n");
      }
      else
      {
         System.out.println ("\n   Fac12: da steht kein Sqrt!\n");
         System.exit (-1);
      }

      return 1;
   }
}

