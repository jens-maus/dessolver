package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac39.java
// Use  semantische funktion fuer anfang des cotangens
//-----------------------------------------------------------------------------

class Fac39 implements SemFuncPointer
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
      //System.out.println ("\n   Fac39: anfang des Cot! ***\n");

      Node CotNode = new Node();
      Node MaxTopNode;
      Node K;

      CotNode.SetInfoString ("Cot-anfang");
      CotNode.SetRang (cot);

      MaxTopNode = (Node)stack.peek();
      K = CotNode.DiveToAttachPoint (MaxTopNode);
      CotNode.AttachToTree (K);
      stack.push (CotNode);

      return 1;
   }
}


