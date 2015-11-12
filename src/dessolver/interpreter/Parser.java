package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Parser.java
// Use  parser des interpreters
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Parser implements Const
{
   static final Graph g = new Graph();
   Morphem Morph = new Morphem();
   int ebene = -1;
   Node RootNode = new Node();
   static BufferedReader din = new BufferedReader(new InputStreamReader(System.in));
   boolean error=false;

   // semantische funktionen
   static SemFuncPointer [] sf =
   {
      new F0(), new Ex1(), new Ex2(), new Ex4(), new Ex5(), new Ex7(), new Ex8(),
      new Te1(), new Te2(), new Te3(),
      new Fac1(), new Fac2(), new Fac3(), new Fac4(), new Fac5(), new Fac6(),
      new Fac7(), new Fac8(), new Fac9(), new Fac10(), new Fac11(), new Fac12(),
      new Fac13(), new Fac14(), new Fac15(), new Fac16(), new Fac17(), new Fac18(),
      new Fac19(), new Fac20(), new Fac21(), new Fac22(), new Fac23(), new Fac24(),
      new Fac25(), new Fac26(), new Fac27(), new Fac28(), new Fac29(), new Fac30(),
      new Fac31(), new Fac32(), new Fac33(), new Fac34(), new Fac35(), new Fac36(),
      new Fac37(), new Fac38(), new Fac39(), new Fac40()
   };

   public int parse (Bogen[] Bg)
   {
      int i=0, graph, verarbMorphem = 0;
      int succ=0;

      if ( Morph.MC == mcEmpty )
      {
         //System.out.println ("lexer wird angeworfen");
         Morph = new Morphem(Lex.Lexer());
      }

      ebene++;
      //System.out.println ("Ebene " + ebene);

      while (true)
      {
         //System.out.println ("  Morph.MC = " + Morph.MC);
         //System.out.println ("  Morph.Numb = " + Morph.Numb);
         //System.out.println ("  Bg.BgD = " + Bg[i].BgD);

         switch ( Bg[i].BgD )    // was ist das fuer ein bogentyp?
         {
            case BgNil: succ = 1;
                        break;

            case BgSy: //System.out.println ("  parser - symbol: " + (char) Morph.Symb + "    Bogensymbol: " + (char) Bg[i].BgX );
                       if ( Morph.Symb == Bg[i].BgX )
                       {
                          succ = 1;
                          sf[Bg[i].FuncIndex].exec();
                       }
                       else
                       {
                          succ = 0;
                       }
                       break;

            case BgMo: //System.out.println ("  parser - Mo: " + Morph.Numb);

                       if ( Morph.MC == Bg[i].BgX )
                       {
                          succ = 1;

                          if ( Bg[i].BgX == mcNumb )
                          {
                             sf[Bg[i].FuncIndex].exec(Morph.Numb);
                          }

                          if ( Bg[i].BgX == mcIdent )
                          {
                             sf[Bg[i].FuncIndex].exec(Morph.Str);
                          }
                       }
                       else
                       {
                          succ = 0;
                       }
                       break;

            case BgGr: //System.out.println ("neuer graph startet: " + Bg[i].BgX );
                       succ = parse ( g.Graph[Bg[i].BgX] );
                       ebene--;
                       //System.out.println ("Ebene " + ebene + ", succc = " + succ + "  Morph.MC " + Morph.MC);
                       sf[Bg[i].FuncIndex].exec();
                       break;

            case BgEn: //System.out.println (" BgEn");
                       sf[Bg[i].FuncIndex].exec();
                       return 1;
         } // switch ( Bg[i].BgD )

         if ( succ == -1 )  // das ging daneben
            return -1;

         if ( succ == 0 )   // alternativbogen suchen
         {
            //System.out.println ("  alternative suchen");
            if ( Bg[i].iAlt != 0 )  // existiert alternativbogen?
            {
               i = Bg[i].iAlt;
               //System.out.println ("  alternativbogen nr. " + i + " in ebene " + ebene + " gefunden");
            }
            else  // nee, kein alternativbogen, mist
            {
               //System.out.println ("  keine alternative gefunden - succ = 0 und VerarbMOrphem = " + verarbMorphem);

               if ( verarbMorphem == 1 )
               {
                  return -1;
               }
               else
               {
                  return 0;
               }
            }
         }
         else  // bogen erfolgreich durchlaufen
         {
            //System.out.println ("  bogen " + i + " in ebene " + ebene + " erfolgreich durchlaufen");

            if ( (Bg[i].BgD & BgSy) > 0 || (Bg[i].BgD & BgMo) > 0)
            {
               //System.out.println ("  neues morphem");
               verarbMorphem = 1;
               Morph = new Morphem(Lex.Lexer());
               //System.out.println ("    Morph.MC = " + Morph.MC + "   Morph.Symb = " + Morph.Symb);
            }

            i = Bg[i].iNext;
            //System.out.println ("  folgebogen nr. " + i + " in ebene " + ebene + " verfolgen");
         }
      } // while (1)

   } // boolean parse (Bogen[] Bg)

   public double calc(Vector varVector)
   {
      int count=0;

      // now we have to go through the varVector and set all needed variables
      // otherwise we exit with an error.
      for(int i=0; i < vIdent.size(); i++)
      {
        Ident id = (Ident)vIdent.elementAt(i);

        for(int j=0; j < varVector.size(); j++)
        {
          Ident id2 = (Ident)varVector.elementAt(j);

          if(id2.IdentName.compareTo(id.IdentName) == 0)
          {
            id.IdentValue = id2.IdentValue;
            count++;
            break;
          }
        }
      }

      // if not all values were refilled we exit here with a error
      if(count != vIdent.size())
      {
        if(error == false)
        {
          ResourceBundle res = ResourceBundle.getBundle("dessolver");
          JOptionPane.showMessageDialog(null, res.getString("ERR_INTERPRETER"), res.getString("TXT_ERROR"), JOptionPane.ERROR_MESSAGE);
          System.err.println("Interpreter: Some variables in the Equation are unresolved -> Incomplete Equation !");
          error=true;
        }

        return 0;
      }
      else error=false;

      return RootNode.lNode.getErgebnis();
   }

   public void init ()
   {
      Ident id, id2;
      Klammer.Ebene = 0;   // klammerebenen in hunderter schritten

      RootNode.SetInfoString ("root");
      RootNode.SetRang(0);  // einstellige operation, ich will nur am linken ast was haben
      stack.push(RootNode);

      KeyWordVector.addElement("sin");
      KeyWordVector.addElement("cos");
      KeyWordVector.addElement("tan");
      KeyWordVector.addElement("sqrt");
      KeyWordVector.addElement("exp");
      KeyWordVector.addElement("ln");
      KeyWordVector.addElement("log");
      KeyWordVector.addElement("pow");
      KeyWordVector.addElement("abs");
      KeyWordVector.addElement("arcsin");
      KeyWordVector.addElement("arccos");
      KeyWordVector.addElement("arctan");
      KeyWordVector.addElement("fac");
      KeyWordVector.addElement("sinh");
      KeyWordVector.addElement("cosh");
      KeyWordVector.addElement("tanh");
      KeyWordVector.addElement("coth");
      KeyWordVector.addElement("cot");
   }

   /*public static void main (String[] args) throws IOException
   {
      String strFunk = new String();

      Klammer.Ebene = 0;   // klammerebenen (in hunderter schritten)

      /*RootNode.SetInfoString ("root");
      RootNode.SetRang(0);  // einstellige operation, ich will nur am linken ast was haben
      stack.push(RootNode);
      */

     /* init();

      System.out.println ("Geben Sie die Funktion f(x) ein: ");
      strFunk = din.readLine();
      strFunk = strFunk.concat("\0");
      Lex.Init (strFunk);

      if ( parse (g.Graph[0]) == 1 )
      {
         System.out.println ("syntaktisch korrekt\n\n");

         // nur vorlaeufig, das wird noch in eine funktion verpackt
            //Ident id;

            //id = (Ident) vIdent.get(0);
            //id.IdentValue = 3.5;

            //id = (Ident) vIdent.get(1);
            //id.IdentValue = 2;

         //System.out.println ("Ergebnis: " + RootNode.lNode.getErgebnis() );

         System.out.println ("Ergebnis: " + calc (0, 3));
         //System.out.println ("Ergebnis: " + calc (3, 3));

         //System.out.println ("rootNode.lNode = " + RootNode.lNode.GetInfoString() );

      }
      else
      {
         System.out.println ("syntaktisch falsch");
      }
   }*/
}
