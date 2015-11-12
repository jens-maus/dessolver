package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac35.java
// Use  semantische funktion fuer anfang des tangens hyperbolicus
//-----------------------------------------------------------------------------

class Fac35 implements SemFuncPointer
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
      //System.out.println ("\n   Fac35: anfang des Tanh! ***\n");

      Node TanhNode = new Node();
      Node MaxTopNode;
      Node K;

      TanhNode.SetInfoString ("Tanh-anfang");
      TanhNode.SetRang (tanh);

      MaxTopNode = (Node)stack.peek();
      K = TanhNode.DiveToAttachPoint (MaxTopNode);
      TanhNode.AttachToTree (K);
      stack.push (TanhNode);

      return 1;
   }
}


