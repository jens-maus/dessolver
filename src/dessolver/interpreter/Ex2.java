package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Ex2.java
// Use  semantische funktion fuer vorzeichenminus
//-----------------------------------------------------------------------------

class Ex2 implements SemFuncPointer
{
   public int exec (double a)
   {
      return 1;
   }

   public int exec ()
   {
      //System.out.println ("\n   EX2: vorzeichenminus ende");

      Node K;

      K = (Node) stack.peek();

      if ( K.GetInfoString().equals ("vorzeichenminus") )
      {
         stack.pop();
         //System.out.println ("\n   Ex2: vorzeichenminus wurde entfernt\n");
      }
      else
      {
         //System.out.println ("\n   Ex2: das war gar kein vorzeichenminus!");
         //System.exit(-1);
      }

      return 1;
   }

   public int exec (String str)
   {
      return 1;
   }
}
