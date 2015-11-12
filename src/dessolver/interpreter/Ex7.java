package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Ex7.java
// Use  semantische funktion fuer das beendigen von "+" oder "-"
//-----------------------------------------------------------------------------

class Ex7 implements SemFuncPointer
{
   public int exec (double a)
   {
      return 1;
   }

   public int exec ()
   {
      //System.out.println ("\n   *** semantische funktion Ex7 - wozu auch immer ***\n");

      Node K;

      K = (Node) stack.pop();

      if ( K.GetInfoString().equals ("add") || K.GetInfoString().equals ("sub") )
      {
         //System.out.println ("\n   Ex7: '+' | '-'  wurde entfernt!\n");
      }
      else
      {
         System.out.println ("\n   Ex7: irgendwas ging schief, sagen wir, die syntax wars\n");
         System.exit (-1);
      }

      return 1;
   }
   public int exec (String str)
   {
      return 1;
   }
}
