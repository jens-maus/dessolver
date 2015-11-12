package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac38.java
// Use  semantische funktion fuer beenden des Coth
//-----------------------------------------------------------------------------

class Fac38 implements SemFuncPointer
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
      //System.out.println ("\n   Fac38: ende des Coth!");

      Node K;

      K = (Node) stack.peek();

      if ( K.GetInfoString().equals ("coth-anfang") )
      {
         stack.pop();
         //System.out.println ("\n   Fac38: Coth  wurde entfernt!\n");
      }
      else
      {
         System.out.println ("\n   Fac38: da steht kein Coth!\n");
         System.exit (-1);
      }

      return 1;
   }
}

