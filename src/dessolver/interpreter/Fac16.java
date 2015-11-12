package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac16.java
// Use  semantische funktion fuer beenden des Ln
//-----------------------------------------------------------------------------

class Fac16 implements SemFuncPointer
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
      //System.out.println ("\n   Fac16: ende des Ln!");

      Node K;

      K = (Node) stack.peek();

      if ( K.GetInfoString().equals ("Ln-anfang") )
      {
         stack.pop();
         //System.out.println ("\n   Fac16: Ln  wurde entfernt!\n");
      }
      else
      {
         System.out.println ("\n   Fac16: da steht kein Ln!\n");
         System.exit (-1);
      }

      return 1;
   }
}

