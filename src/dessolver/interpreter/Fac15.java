package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac15.java
// Use  semantische funktion fuer anfang des Ln
//-----------------------------------------------------------------------------

class Fac15 implements SemFuncPointer
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
      //System.out.println ("\n   Fac15: anfang des Ln! ***\n");

      Node LnNode = new Node();
      Node MaxTopNode;
      Node K;

      LnNode.SetInfoString ("Ln-anfang");
      LnNode.SetRang (ln);

      MaxTopNode = (Node)stack.peek();
      K = LnNode.DiveToAttachPoint (MaxTopNode);
      LnNode.AttachToTree (K);
      stack.push (LnNode);

      return 1;
   }
}


