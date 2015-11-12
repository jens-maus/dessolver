package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Ex4.java
// Use  semantische funktion fuer "+"
//-----------------------------------------------------------------------------

class Ex4 implements SemFuncPointer
{
   public int exec (double a)
   {
      return 1;
   }

   public int exec ()
   {
      //System.out.println ("\n   Ex4: additives plus");

      Node AddNode = new Node();
      Node MaxTopNode;
      Node K;

      AddNode.SetInfoString ("add");
      AddNode.SetRang (add);

      MaxTopNode =  (Node) stack.peek();
      K = AddNode.DiveToAttachPoint (MaxTopNode);
      AddNode.AttachToTree (K);
      stack.push (AddNode);

      return 1;
   }
   public int exec (String str)
   {
      return 1;
   }
}
