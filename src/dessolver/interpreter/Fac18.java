package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac18.java
// Use  semantische funktion fuer beenden des Log
//-----------------------------------------------------------------------------

class Fac18 implements SemFuncPointer
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
      //System.out.println ("\n   Fac18: ende des Log!");

      Node K;

      K = (Node) stack.peek();

      if ( K.GetInfoString().equals ("Log-anfang") )
      {
         stack.pop();
         //System.out.println ("\n   Fac18: Log  wurde entfernt!\n");
      }
      else
      {
         System.out.println ("\n   Fac18: da steht kein Log!\n");
         System.exit (-1);
      }

      return 1;
   }
}

