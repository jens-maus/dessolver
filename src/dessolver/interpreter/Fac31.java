package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac31.java
// Use  semantische funktion fuer anfang des sinus hyperbolicus
//-----------------------------------------------------------------------------

class Fac31 implements SemFuncPointer
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
      //System.out.println ("\n   Fac31: anfang des Sinh! ***\n");

      Node SinhNode = new Node();
      Node MaxTopNode;
      Node K;

      SinhNode.SetInfoString ("Sinh-anfang");
      SinhNode.SetRang (sinh);

      MaxTopNode = (Node)stack.peek();
      K = SinhNode.DiveToAttachPoint (MaxTopNode);
      SinhNode.AttachToTree (K);
      stack.push (SinhNode);

      return 1;
   }
}


