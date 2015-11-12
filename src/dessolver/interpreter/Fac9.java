package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac9.java
// Use  semantische funktion fuer anfang des Tangens
//-----------------------------------------------------------------------------

class Fac9 implements SemFuncPointer
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
      //System.out.println ("\n   Fac9: anfang des Tangens! ***\n");

      Node TangensNode = new Node();
      Node MaxTopNode;
      Node K;

      TangensNode.SetInfoString ("Tangens-anfang");
      TangensNode.SetRang (tan);

      MaxTopNode = (Node)stack.peek();
      K = TangensNode.DiveToAttachPoint (MaxTopNode);
      TangensNode.AttachToTree (K);
      stack.push (TangensNode);

      return 1;
   }
}


