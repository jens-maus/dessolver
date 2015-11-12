package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Fac4.java
// Use  semantische funktion fuer '( expr )'
//-----------------------------------------------------------------------------

class Fac4 implements SemFuncPointer
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
      //System.out.println ("\n   Fac4: ende der klammer!");

      //System.out.println ("           Klammerebene: " + Klammer.Ebene);
      Klammer.Ebene-=100;
      //System.out.println ("           wird zurueckgesetzt auf: " + Klammer.Ebene);

      return 1;
   }
}


