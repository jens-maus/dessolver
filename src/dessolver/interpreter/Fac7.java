package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac7.java
// Use  semantische funktion fuer anfang des cosinus
//-----------------------------------------------------------------------------

class Fac7 implements SemFuncPointer
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
      //System.out.println ("\n   Fac5: anfang des cosinus! ***\n");

      Node CosinusNode = new Node();
      Node MaxTopNode;
      Node K;

      CosinusNode.SetInfoString ("cosinus-anfang");
      CosinusNode.SetRang (cos);

      MaxTopNode = (Node)stack.peek();
      K = CosinusNode.DiveToAttachPoint (MaxTopNode);
      CosinusNode.AttachToTree (K);
      stack.push (CosinusNode);
return 1;
   }
}


