package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File LFkt.java
// Use  lesen des naechsten morphemzeichens
//-----------------------------------------------------------------------------

class LFkt implements SFkt
{
   public void exec (Morphem Morph)
   {
      //System.out.println ("l");
      Lex.IncIdx();
      Lex.SetX(Lex.GetCharFromStr(Lex.GetIdx()));
   }
}
