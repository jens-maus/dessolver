package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac6.java
// Use  semantische funktion fuer beenden des sinus
//-----------------------------------------------------------------------------

class Fac6 implements SemFuncPointer
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
      //System.out.println ("\n   Fac6: ende des sinus!");

      Node K;

      K = (Node) stack.peek();

      if ( K.GetInfoString().equals ("sinus-anfang") )
      {
         stack.pop();
         //System.out.println ("\n   Fac6: sinus  wurde entfernt!\n");
      }
      else
      {
         System.out.println ("\n   Fac6: da steht kein sinus!\n");
         System.exit (-1);
      }

      return 1;
   }
}


