package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File EFkt.java
// Use  beenden des Morphems
//-----------------------------------------------------------------------------

class EFkt implements SFkt
{
   public void exec (Morphem Morph)
   {
      //System.out.println ("e - Zustand: " + Lex.GetZ() + " Str: " + Morph.Str);
      Double a = new Double(1);

      switch (Lex.GetZ())
      {
         case 1:
         case 3: Morph.MC=mcNumb;
                 //System.out.println(" --- eFkt: " + Morph.Str + " " + Lex.GetX());
                 //Morph.Numb=Double.parseDouble(Morph.Str); // wegen 1.1.8
                 Morph.Numb = Double.valueOf(Morph.Str).doubleValue();
                 //System.out.println(" --- eFkt Morph.Numb: " + Morph.Numb);
                 break;

         case 4: Morph.MC = mcIdent;
                 int index;

                 /*for ( int i=0; i < 5; i++ )
                 {
                    if ( Morph.Str.equals (SWort[i]) )
                    {
                       //System.out.println ("### morphem ist schluesselwort " + SWort[i]);
                       switch (i)
                       {
                          case 0: Morph.Symb = sin;
                                  break;
                          case 1: Morph.Symb = cos;
                                  break;
                          case 2: Morph.Symb = tan;
                                  break;
                          case 3: Morph.Symb = sqrt;
                                  break;
                          case 4: Morph.Symb = exp;
                       }
                       Morph.MC = mcSymb;
                    }
                 }
                 */

                 index = KeyWordVector.indexOf (Morph.Str);

                 if ( index != -1 )
                 {
                    Morph.Symb = index + 5;
                    Morph.MC = mcSymb;
                 }

                 if ( Morph.Str.toUpperCase().equals("PI") )
                 {
                    Morph.MC = mcNumb;
                    Morph.Numb = Math.PI;
                 }

                 if ( Morph.Str.toUpperCase().equals("E") )
                 {
                    Morph.MC = mcNumb;
                    Morph.Numb = Math.E;
                 }
                 break;

         case 5: Morph.MC = mcSymb;
                 Morph.Symb = Lex.GetX();
                 //Morph.Symb = (char)'-';
                 //System.out.println (" --- eFkt: MINUS!");
                 //System.out.println (" --- eFkt: Morphemcode: " + Morph.MC);
                 break;
         case 6: Morph.MC = mcSymb;
                 Morph.Symb = Lex.GetX();
                 //Morph.Symb = (char)'-';
                 //System.out.println (" --- eFkt: MINUS!");
                 //System.out.println (" --- eFkt: Morphemcode: " + Morph.MC);
                 break;
         default:
      }

      if ( Lex.GetX() == '\0' )
      {
         Lex.SetSchluss (false);
      }

      Lex.SetEnde(1);
      //System.out.println ("Lex.Ende = " + Lex.GetEnde());
      //Lex.IncIdx();
   }
}
