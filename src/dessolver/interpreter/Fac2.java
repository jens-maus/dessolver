package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac2.java
// Use  semantische funktion fuer ident
//-----------------------------------------------------------------------------

class Fac2 implements SemFuncPointer
{
   public int exec ()
   {
      return 1;
   }

   public int exec (double a)
   {
      return 1;
   }

   public int exec (String MorphemString)
   {
      Node IdentNode = new Node();
      Node MaxTopNode;
      Node K;
      boolean s = false;

      for(int i = 0; i < vIdent.size(); i++)
      {
         if (((Ident)vIdent.elementAt(i)).IdentName.compareTo(MorphemString) == 0)
         {
            s = true;
            break;
         }
      }

      if(s == false)
      {
        Ident id = new Ident(MorphemString);
        vIdent.addElement(id);

        //System.out.println ("   fac2: variable " + MorphemString + " eingetragen");
      }

      IdentNode.SetInfoString(new String(MorphemString));
      IdentNode.SetRang (ident);

      MaxTopNode = (Node) stack.peek();
      K = IdentNode.DiveToAttachPoint (MaxTopNode);
      IdentNode.AttachToTree (K);

      //System.out.println ("\n   Fac2: ein ident");
      //System.out.println ("           Anzahl: " + vIdent.size());

      return 1;
   }
}


