package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac13.java
// Use  semantische funktion fuer anfang des Exp
//-----------------------------------------------------------------------------

class Fac13 implements SemFuncPointer
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
      //System.out.println ("\n   Fac13: anfang des Exp! ***\n");

      Node ExpNode = new Node();
      Node MaxTopNode;
      Node K;

      ExpNode.SetInfoString ("Exp-anfang");
      ExpNode.SetRang (exp);

      MaxTopNode = (Node)stack.peek();
      K = ExpNode.DiveToAttachPoint (MaxTopNode);
      ExpNode.AttachToTree (K);
      stack.push (ExpNode);

      return 1;
   }
}


