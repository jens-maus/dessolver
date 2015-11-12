package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac26.java
// Use  semantische funktion fuer beenden des Arccos
//-----------------------------------------------------------------------------

class Fac26 implements SemFuncPointer
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
      //System.out.println ("\n   Fac26: ende des Arccos!");

      Node K;

      K = (Node) stack.peek();

      if ( K.GetInfoString().equals ("Arccos-anfang") )
      {
         stack.pop();
         //System.out.println ("\n   Fac26: Arccos  wurde entfernt!\n");
      }
      else
      {
         System.out.println ("\n   Fac26: da steht kein Arccos!\n");
         System.exit (-1);
      }

      return 1;
   }
}

