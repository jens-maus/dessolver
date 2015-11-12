package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac21.java
// Use  semantische funktion fuer anfang des Abs
//-----------------------------------------------------------------------------

class Fac21 implements SemFuncPointer
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
      //System.out.println ("\n   Fac21: anfang des Abs! ***\n");

      Node AbsNode = new Node();
      Node MaxTopNode;
      Node K;

      AbsNode.SetInfoString ("Abs-anfang");
      AbsNode.SetRang (abs);

      MaxTopNode = (Node)stack.peek();
      K = AbsNode.DiveToAttachPoint (MaxTopNode);
      AbsNode.AttachToTree (K);
      stack.push (AbsNode);

      return 1;
   }
}


