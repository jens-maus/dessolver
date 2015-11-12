package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Ex5.java
// Use  semantische funktion fuer "-"
//-----------------------------------------------------------------------------

class Ex5 implements SemFuncPointer
{
   public int exec (double a) // dummy
   {
      return 1;
   }

   public int exec ()
   {
      //System.out.println ("   \nEx5: subtraktives minus");

      Node SubNode = new Node();
      Node MaxTopNode;
      Node K;

      SubNode.SetInfoString ("sub");
      SubNode.SetRang (sub);

      MaxTopNode =  (Node) stack.peek();
      K = SubNode.DiveToAttachPoint (MaxTopNode);
      SubNode.AttachToTree (K);
      stack.push (SubNode);

      return 1;
   }
   public int exec (String str)
   {
      return 1;
   }
}
