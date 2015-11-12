package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac19.java
// Use  semantische funktion fuer anfang des Pow
//-----------------------------------------------------------------------------

class Fac19 implements SemFuncPointer
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
      //System.out.println ("\n   Fac19: anfang des Pow! ***\n");

      Node PowNode = new Node();
      Node MaxTopNode;
      Node K;

      PowNode.SetInfoString ("Pow-anfang");
      PowNode.SetRang (pow);
      PowNode.rang = 1; // wiedermal mehr als unsauber


      MaxTopNode = (Node)stack.peek();
      K = PowNode.DiveToAttachPoint (MaxTopNode);
      PowNode.AttachToTree (K);
      stack.push (PowNode);

      return 1;
   }
}


