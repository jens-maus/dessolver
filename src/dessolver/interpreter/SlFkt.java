package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File SlFkt.java
// Use  schreiben des aktuellen morphemzeichens, lesen des naechsten zeichens
//-----------------------------------------------------------------------------

class SlFkt implements SFkt
{
   public void exec (Morphem Morph)
   {
      Morph.Str = Morph.Str + Lex.GetX();
      //System.out.println ("   sl - Morph.Str = " + Morph.Str);
      Lex.IncIdx();
      Lex.SetX(Lex.GetCharFromStr(Lex.GetIdx()));
      //System.out.println ("   sl - X = '" + Lex.GetX() + "'  -  Ende = " + Lex.GetEnde());
      //System.out.println (" --- sl: " + Morph.Str + " " + Lex.GetX());
   }
}
