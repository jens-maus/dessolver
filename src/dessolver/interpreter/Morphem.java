package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Morphem.java
// Use  Darstellung des Objektes "morphem"
//-----------------------------------------------------------------------------

public class Morphem implements Const
{
   int MC;
   int Symb;

   double Numb;
   int  MorphLen;

   String Str = new String();

   public Morphem ()
   {
      MC = mcEmpty;
   };

   public Morphem (Morphem Morph)
   {
      MC = Morph.MC;
      Symb = Morph.Symb;

      Numb = Morph.Numb;
      MorphLen = Morph.MorphLen;

      Str = new String(Morph.Str);
   }
}
