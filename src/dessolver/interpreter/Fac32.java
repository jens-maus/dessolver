package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac32.java
// Use  semantische funktion fuer beenden des Sinh
//-----------------------------------------------------------------------------

class Fac32 implements SemFuncPointer
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
      //System.out.println ("\n   Fac32: ende des Sinh!");

      Node K;

      K = (Node) stack.peek();

      if ( K.GetInfoString().equals ("Sinh-anfang") )
      {
         stack.pop();
         //System.out.println ("\n   Fac32: Sinh  wurde entfernt!\n");
      }
      else
      {
         System.out.println ("\n   Fac32: da steht kein Sinh!\n");
         System.exit (-1);
      }

      return 1;
   }
}

