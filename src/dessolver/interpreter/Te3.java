package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Te2.java
// Use  semantische funktion zum abschliessen des termes
//-----------------------------------------------------------------------------

class Te3 implements SemFuncPointer
{
   public int exec (double a)
   {
      return 1;
   }

   public int exec ()
   {
      Node K;

      K = (Node) stack.peek();

      if ( K.GetInfoString().equals ("mul") || K.GetInfoString().equals ("div") )
      {
         stack.pop();
         //System.out.println ("\n   Te3: '*' | '/'  wurde entfernt!\n");
      }
      else
      {
         //System.out.println ("\n   Te3: da steht kein mul/div - ignoriere\n");
         //System.exit (-1);
      }

      return 1;
   }
   public int exec (String str)
   {
      return 1;
   }
}

