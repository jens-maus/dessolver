package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac27.java
// Use  semantische funktion fuer anfang des Arctan
//-----------------------------------------------------------------------------

class Fac27 implements SemFuncPointer
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
      //System.out.println ("\n   Fac27: anfang des Arctan! ***\n");

      Node ArctanNode = new Node();
      Node MaxTopNode;
      Node K;

      ArctanNode.SetInfoString ("Arctan-anfang");
      ArctanNode.SetRang (arctan);

      MaxTopNode = (Node)stack.peek();
      K = ArctanNode.DiveToAttachPoint (MaxTopNode);
      ArctanNode.AttachToTree (K);
      stack.push (ArctanNode);

      return 1;
   }
}


