package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac10.java
// Use  semantische funktion fuer beenden des Tangens
//-----------------------------------------------------------------------------

class Fac10 implements SemFuncPointer
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
      //System.out.println ("\n   Fac10: ende des Tangens!");

      Node K;

      K = (Node) stack.peek();

      if ( K.GetInfoString().equals ("Tangens-anfang") )
      {
         stack.pop();
         //System.out.println ("\n   Fac10: Tangens  wurde entfernt!\n");
      }
      else
      {
         //System.out.println ("\n   Fac10: da steht kein Tangens!\n");
         System.exit (-1);
      }

      return 1;
   }
}

