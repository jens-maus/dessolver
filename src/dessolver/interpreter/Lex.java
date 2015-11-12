package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Lex.java
// Use  Lexikalische Analyse der Funktion
//-----------------------------------------------------------------------------

import java.io.*;

//-----------------------------------------------------------------------------
// class Lex --
//      Lexikalische Analyse
//-----------------------------------------------------------------------------

public class Lex implements Const
{

   static private String strFunk;

   static BufferedReader din = new BufferedReader (
                                new InputStreamReader(System.in));
   private static char X;
   private static int Z=0, Zn, Ende, len, idx;
   private static boolean schluss = true;

   /*
    * zeichenklassen: 0: + | - | * | / | ( | )
    *                 1: Ziffern
    *                 2: Buchstaben
    *                 3: alles andere
    *                 4: .
    *                 5: \0
    */

   private static char vZKl [] =
   {
         5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,
         3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,
         3,3,3,3,3,3,3,3,0,0,0,0,3,0,4,0,
         1,1,1,1,1,1,1,1,1,1,3,3,3,3,3,3,
         3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
         2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,
         3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
         2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3
    };

   /*  tabellen fuer funktionspointer und zustandsuebergaenge
    *
    *              Z e i c h e n k l a s s e n
    *
    *           |   0   |   1   |   2   |   3   |   4   |   5
    *        ---------------------------------------------------
    *           |       |       |       |       |       |
    *    Z    0 | -> 0  | -> 1  | -> 4  | -> 0  | -> 0  | -> 0
    *           | sle   | sl    | sl    | l     | sle   | e
    *           |       |       |       |       |       |
    *        ---------------------------------------------------
    *           |       |       |       |       |       |
    *    u    1 | -> 0  | -> 1  | -> 0  | -> 0  | -> 0  | -> 2
    *           | e     | sl    | e     | e     | sl    | e
    *           |       |       |       |       |       |
    *        ---------------------------------------------------
    *           |       |       |       |       |       |
    *    s    2 | -> 0  | -> 3  | -> 0  | -> 0  | -> 0  | -> 0
    *           | e     | sl    | e     | e     | e     | e
    *           |       |       |       |       |       |
    *        ---------------------------------------------------
    *           |       |       |       |       |       |
    *    t    3 | -> 0  | -> 3  | -> 0  | -> 0  | -> 0  | -> 0
    *           | e     | sl    | e     | e     | e     | e
    *           |       |       |       |       |       |
    *        ---------------------------------------------------
    *           |       |       |       |       |       |
    *    a    4 | -> 0  | -> 4  | -> 4  | -> 0  | -> 0  | -> 0
    *           | e     | sl    | sl    | e     | e     | e
    *           |       |       |       |       |       |
    *        ---------------------------------------------------
    *           |       |       |       |       |       |
    *    n    5 | -> 6  | -> 1  | -> 0  | -> 0  | -> 0  | -> 0
    *           | e     | e     | e     | e     | e     | e
    *           |       |       |       |       |       |
    *        ---------------------------------------------------
    *           |       |       |       |       |       |
    *    d    6 | -> 0  | -> 1  | -> 4  | -> 0  | -> 0  | -> 0
    *           | sle   | sl    | sl    | l     | sle   | e
    *           |       |       |       |       |       |
    *        ---------------------------------------------------
    *           |       |       |       |       |       |
    *         7 | -> 0  | -> 1  | -> 4  | -> 0  | -> 0  | -> 0
    *           | e     | sl    | sl    | l     | sle   | e
    *           |       |       |       |       |       |
    *        ---------------------------------------------------
    *           |       |       |       |       |       |
    *
    */

   private static SFkt [][] Fkt =
   {
      {new SleFkt(), new SlFkt(), new SlFkt(), new LFkt(), new SleFkt() , new EFkt()},
      {new EFkt(),   new SlFkt(), new EFkt() , new EFkt(), new SlFkt()  , new EFkt()},
      {new EFkt(),   new SlFkt(), new EFkt() , new EFkt(), new EFkt()   , new EFkt()},
      {new EFkt(),   new SlFkt(), new EFkt() , new EFkt(), new EFkt()   , new EFkt()},
      {new EFkt(),   new SlFkt(), new SlFkt(), new EFkt(), new EFkt()   , new EFkt()}

   };

   private static char [][] SZ =
   {
      {0, 1, 4, 0, 0, 0},
      {0, 1, 0, 0, 2, 0},
      {0, 3, 0, 0, 0, 0},
      {0, 3, 0, 0, 0, 0},
      {0, 4, 4, 0, 0, 0},
   };

   //
   // zugriffsfunktionen fuer private variablen
   //    etwas nervig, aber information hiding ist mir wirklich wichtig (???)
   //

   public static void IncIdx ()
   {
      idx++;
   }

   public static int GetIdx ()
   {
      return idx;
   }

   public static void Init (String str)
   {
      Z = Zn = Ende = idx = 0;
      strFunk = new String (str);
      X = strFunk.charAt(0);
   }

   public static char GetCharFromStr (int index)
   {
      return strFunk.charAt(index);
   }

   public static void SetX (char c)
   {
      X = c;
   }

   public static char GetX ()
   {
      return X;
   }

   public static void SetSchluss (boolean b)
   {
      schluss = b;
   }

   public static void SetEnde (int i)
   {
      Ende = i;
   }

   public static int GetEnde ()
   {
      return Ende;
   }

   public static int GetZ ()
   {
      return Z;
   }


   public static void main (String[] args) throws IOException
   {
      strFunk = new String();

      System.out.println ("Geben Sie die Funktion f(x) ein: ");
      strFunk = din.readLine();
      len = strFunk.length();
      strFunk = strFunk.concat("\0");
      System.out.println ("Stringlaenge: " + len + "\n");

      X=strFunk.charAt(0);

      while ( schluss )
      {
         Z = Zn = Ende = 0;

         Morphem Morph = new Morphem ();

         do
         {
            Zn = SZ [Z][vZKl[X]];
            System.out.println ("Index: " + idx + " Gelesenes Zeichen: '" + X + "' Zustand: " + Z + " - " + Zn + " Klasse: " + (int)(vZKl[X]));
            Fkt[Z][vZKl[X]].exec(Morph);
            Z = Zn;
         } while ( Ende == 0 );


      //System.out.println ("    +++ Lex.Lexer() - Morph.MC = " + Morph.MC);

         System.out.println ("\nEnde!");
      }
   }

   public static Morphem Lexer ()
   {
      //System.out.println ("   +++ Lex.Lexer() aufgerufen");
      Morphem Morph = new Morphem();
      Zn=Ende = 0;
      do
      {
         Zn = SZ [Z][vZKl[X]];
         //System.out.println ("Index: " + idx + " Gelesenes Zeichen: '" + X + "' Zustand: " + Z + " - " + Zn + " Klasse: " + (int)(vZKl[X]));
         Fkt[Z][vZKl[X]].exec(Morph);
         Z = Zn;
      } while ( Ende == 0 );

      //System.out.println ("    +++ Lex.Lexer() - Morph.MC = " + Morph.MC);
      return Morph;
   }
}
