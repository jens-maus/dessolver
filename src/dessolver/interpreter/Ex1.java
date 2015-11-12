package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Ex1.java
// Use  semantische funktion fuer vorzeichenminus
//         vorzeichenminus wird durch einen teilbaum "* -1" dargestellt
//-----------------------------------------------------------------------------

class Ex1 implements SemFuncPointer
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
      //System.out.println ("\n   Ex1: vorzeichenminus");

      Node VorzMulNode = new Node();
      Node VorzNumNode = new Node();
      Node MaxTopNode;
      Node K;

      VorzMulNode.SetInfoString ("vorzeichenminus");
      VorzMulNode.SetRang (mul);
      VorzMulNode.rang = 3;      // gar unsauber!

      VorzNumNode.SetInfoString ("vorzeichenminuseins");
      VorzNumNode.SetRang (numeral);
      VorzNumNode.value = -1;

      VorzMulNode.rNode = VorzNumNode;
      VorzNumNode.parentNode = VorzMulNode;

      MaxTopNode = (Node)stack.peek();
      K = VorzMulNode.DiveToAttachPoint (MaxTopNode);
      VorzMulNode.AttachToTree (K);

      stack.push(VorzMulNode);

      return 1;
   }
}
