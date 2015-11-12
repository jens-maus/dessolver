package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac11.java
// Use  semantische funktion fuer anfang des Sqrt
//-----------------------------------------------------------------------------

class Fac11 implements SemFuncPointer
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
      //System.out.println ("\n   Fac11: anfang des Sqrt! ***\n");

      Node SqrtNode = new Node();
      Node MaxTopNode;
      Node K;

      SqrtNode.SetInfoString ("Sqrt-anfang");
      SqrtNode.SetRang (sqrt);

      MaxTopNode = (Node)stack.peek();
      K = SqrtNode.DiveToAttachPoint (MaxTopNode);
      SqrtNode.AttachToTree (K);
      stack.push (SqrtNode);

      return 1;
   }
}


