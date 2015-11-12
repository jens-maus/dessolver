package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File SleFkt.java
// Use  schreiben des aktuellen morphemzeichens, lesen des naechsten zeichens,
//         morphem beenden
//-----------------------------------------------------------------------------

class SleFkt implements SFkt
{
   public void exec (Morphem Morph)
   {
      //System.out.println ("sle");

      if ( Lex.GetX()== (char)'\0' )
      {
         //System.out.println("schluss");
         Lex.SetSchluss(false);
      }
      else
      {
         switch (Lex.GetZ())
         {
            case 0: Morph.MC = mcSymb;
                    Morph.Symb = Lex.GetX();
                    //System.out.println ("sle - Symbol: " + (char)Morph.Symb);
                    break;
            case 3: Morph.MC = mcSymb;
                    Morph.Symb = zErg;
                    break;
            case 4: Morph.MC = mcSymb;
                    Morph.Symb = zle;
                    break;
            case 5: Morph.MC = mcSymb;
                    Morph.Symb = Lex.GetX();
                    //Morph.Symb = '-';
                    //System.out.println ("sle - Symbol: " + (char)Morph.Symb);
                    break;
            case 6: Morph.MC = mcSymb;
                    //Morph.Symb = '-';
                    Morph.Symb = Lex.GetX();
                    //System.out.println ("sle - Symbol: " + (char)Morph.Symb);
//                    break;
            default:
         }

         Lex.IncIdx();
         Lex.SetX(Lex.GetCharFromStr(Lex.GetIdx()));
      }

      Lex.SetEnde(1);
   }
}
