package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac3.java
// Use  semantische funktion fuer '( expr )'
//-----------------------------------------------------------------------------

class Fac3 implements SemFuncPointer
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
      //System.out.println ("\n   Fac3: anfang einer geklammerten expression");

      Klammer.Ebene+=100;  // klammerebene

      /*Node BracketNode = new Node();
      Node MaxTopNode;
      Node K;

      BracketNode.SetInfoString ("klammer-anfang");
      BracketNode.SetRang (sin);

      MaxTopNode = (Node)stack.peek();
      K = BracketNode.DiveToAttachPoint (MaxTopNode);
      BracketNode.AttachToTree (K);
      stack.push (BracketNode);*/

      return 1;
   }
}


