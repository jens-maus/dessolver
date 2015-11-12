package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac34.java
// Use  semantische funktion fuer beenden des Cosh
//-----------------------------------------------------------------------------

class Fac34 implements SemFuncPointer
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
      //System.out.println ("\n   Fac34: ende des Cosh!");

      Node K;

      K = (Node) stack.peek();

      if ( K.GetInfoString().equals ("Cosh-anfang") )
      {
         stack.pop();
         //System.out.println ("\n   Fac34: Cosh  wurde entfernt!\n");
      }
      else
      {
         System.out.println ("\n   Fac34: da steht kein Cosh!\n");
         System.exit (-1);
      }

      return 1;
   }
}

