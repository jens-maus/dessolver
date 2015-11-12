package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac5.java
// Use  semantische funktion fuer beginn des sinus
//-----------------------------------------------------------------------------

class Fac5 implements SemFuncPointer
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
      //System.out.println ("\n   Fac5: anfangg des sinus! ***\n");

      Node SinusNode = new Node();
      Node MaxTopNode;
      Node K;

      SinusNode.SetInfoString ("sinus-anfang");
      SinusNode.SetRang (sin);

      MaxTopNode = (Node)stack.peek();
      K = SinusNode.DiveToAttachPoint (MaxTopNode);
      SinusNode.AttachToTree (K);
      stack.push (SinusNode);

      return 1;
   }
}


