package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Te1.java
// Use  semantische funktion fuer "*"
//-----------------------------------------------------------------------------

class Te1 implements SemFuncPointer
{
   public int exec (double a)
   {
      return 1;
   }

   public int exec ()
   {
      //System.out.println ("\n   Te1: multiplikation");

      Node MulNode = new Node();
      Node MaxTopNode;
      Node K;

      MulNode.SetInfoString ("mul");
      MulNode.SetRang (mul);

      MaxTopNode =  (Node) stack.peek();
      K = MulNode.DiveToAttachPoint (MaxTopNode);
      MulNode.AttachToTree (K);
      stack.push (MulNode);

      return 1;
   }
   public int exec (String str)
   {
      return 1;
   }
}
