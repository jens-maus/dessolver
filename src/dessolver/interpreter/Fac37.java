package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac37.java
// Use  semantische funktion fuer anfang des cotangens hyperbolicus
//-----------------------------------------------------------------------------

class Fac37 implements SemFuncPointer
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
      //System.out.println ("\n   Fac37: anfang des coth! ***\n");

      Node cothNode = new Node();
      Node MaxTopNode;
      Node K;

      cothNode.SetInfoString ("coth-anfang");
      cothNode.SetRang (coth);

      MaxTopNode = (Node)stack.peek();
      K = cothNode.DiveToAttachPoint (MaxTopNode);
      cothNode.AttachToTree (K);
      stack.push (cothNode);

      return 1;
   }
}


