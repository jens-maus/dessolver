package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac23.java
// Use  semantische funktion fuer anfang des Arcsin
//-----------------------------------------------------------------------------

class Fac23 implements SemFuncPointer
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
      //System.out.println ("\n   Fac23: anfang des Arcsin! ***\n");

      Node ArcsinNode = new Node();
      Node MaxTopNode;
      Node K;

      ArcsinNode.SetInfoString ("Arcsin-anfang");
      ArcsinNode.SetRang (arcsin);

      MaxTopNode = (Node)stack.peek();
      K = ArcsinNode.DiveToAttachPoint (MaxTopNode);
      ArcsinNode.AttachToTree (K);
      stack.push (ArcsinNode);

      return 1;
   }
}


