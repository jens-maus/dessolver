package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac25.java
// Use  semantische funktion fuer anfang des Arccos
//-----------------------------------------------------------------------------

class Fac25 implements SemFuncPointer
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
      //System.out.println ("\n   Fac25: anfang des Arccos! ***\n");

      Node ArccosNode = new Node();
      Node MaxTopNode;
      Node K;

      ArccosNode.SetInfoString ("Arccos-anfang");
      ArccosNode.SetRang (arccos);

      MaxTopNode = (Node)stack.peek();
      K = ArccosNode.DiveToAttachPoint (MaxTopNode);
      ArccosNode.AttachToTree (K);
      stack.push (ArccosNode);

      return 1;
   }
}


