package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac1.java
// Use  semantische funktion fuer numeral
//-----------------------------------------------------------------------------

class Fac1 implements SemFuncPointer
{
   public int exec ()
   {
      return 1;
   }

   public int exec (String str)
   {
      return 1;
   }

   public int exec (double a)
   {
      //System.out.println ("\n   Fac1: eine nummer");

      Node NumeralNode = new Node();
      Node MaxTopNode;
      Node K;

      NumeralNode.SetInfoString ("numeral");
      NumeralNode.SetRang (numeral);

      MaxTopNode = (Node)stack.peek();
      K = NumeralNode.DiveToAttachPoint (MaxTopNode);
      NumeralNode.AttachToTree (K, a );

      return 1;
   }
}


