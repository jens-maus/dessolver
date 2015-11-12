package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File fac8.java
// Use  semantische funktion fuer beenden des Cosinus
//-----------------------------------------------------------------------------

class Fac8 implements SemFuncPointer
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
      //System.out.println ("\n   fac8: ende des Cosinus!");

      Node K;

      K = (Node) stack.peek();

      if ( K.GetInfoString().equals ("cosinus-anfang") )
      {
         stack.pop();
         //System.out.println ("\n   fac8: Cosinus  wurde entfernt!\n");
      }
      else
      {
         System.out.println ("\n   fac8: da steht kein Cosinus!\n");
         System.exit (-1);
      }

      return 1;
   }
}

