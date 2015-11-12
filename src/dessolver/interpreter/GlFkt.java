package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File GlFkt.java
// Use  Grossschreiben des aktuellen Morphemzeichens
//-----------------------------------------------------------------------------

class glFkt implements SFkt
{
   public void exec (Morphem Morph)
   {
      //System.out.println ("gl");
      Morph.Str = Morph.Str + Character.toUpperCase(Lex.GetX());
      Lex.IncIdx();
      Lex.SetX(Lex.GetCharFromStr(Lex.GetIdx()));
   }
}
