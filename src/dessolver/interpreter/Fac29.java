package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac29.java
// Use  semantische funktion fuer anfang der fakultaet
//-----------------------------------------------------------------------------

class Fac29 implements SemFuncPointer
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
      //System.out.println ("\n   Fac29: anfang des Fac! ***\n");

      Node FacNode = new Node();
      Node MaxTopNode;
      Node K;

      FacNode.SetInfoString ("Fac-anfang");
      FacNode.SetRang (fac);

      MaxTopNode = (Node)stack.peek();
      K = FacNode.DiveToAttachPoint (MaxTopNode);
      FacNode.AttachToTree (K);
      stack.push (FacNode);

      return 1;
   }
}


