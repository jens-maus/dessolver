package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac17.java
// Use  semantische funktion fuer anfang des Log
//-----------------------------------------------------------------------------

class Fac17 implements SemFuncPointer
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
      //System.out.println ("\n   Fac17: anfang des Log! ***\n");

      Node LogNode = new Node();
      Node MaxTopNode;
      Node K;

      LogNode.SetInfoString ("Log-anfang");
      LogNode.SetRang (log);

      MaxTopNode = (Node)stack.peek();
      K = LogNode.DiveToAttachPoint (MaxTopNode);
      LogNode.AttachToTree (K);
      stack.push (LogNode);

      return 1;
   }
}


