package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Te2.java
// Use  semantische funktion fuer "/"
//-----------------------------------------------------------------------------

class Te2 implements SemFuncPointer
{
   public int exec (double a)
   {
      return 1;
   }

   public int exec ()
   {
      //System.out.println ("\n   Te2: division");

      Node DivNode = new Node();
      Node MaxTopNode;
      Node K;

      DivNode.SetInfoString ("div");
      DivNode.SetRang (div);

      MaxTopNode =  (Node) stack.peek();
      K = DivNode.DiveToAttachPoint (MaxTopNode);
      DivNode.AttachToTree (K);
      stack.push (DivNode);

      return 1;
   }
   public int exec (String str)
   {
      return 1;
   }
}

