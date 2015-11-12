package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac33.java
// Use  semantische funktion fuer anfang des cosinus hyperbolicus
//-----------------------------------------------------------------------------

class Fac33 implements SemFuncPointer
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
      //System.out.println ("\n   Fac33: anfang des Cosh! ***\n");

      Node CoshNode = new Node();
      Node MaxTopNode;
      Node K;

      CoshNode.SetInfoString ("Cosh-anfang");
      CoshNode.SetRang (cosh);

      MaxTopNode = (Node)stack.peek();
      K = CoshNode.DiveToAttachPoint (MaxTopNode);
      CoshNode.AttachToTree (K);
      stack.push (CoshNode);

      return 1;
   }
}


