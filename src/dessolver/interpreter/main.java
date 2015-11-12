package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File main.java
// Use
//-----------------------------------------------------------------------------

import java.io.*;

class main
{
   static final Graph g = new Graph();

   static BufferedReader din = new BufferedReader (
                                  new InputStreamReader(System.in));

   public static void main (String[] args) throws IOException
   {
      Parser p = new Parser();

      String strFunk = new String();

      p.init ();

      System.out.println ("Geben Sie die Funktion f(x) ein: ");
      strFunk = din.readLine();
      strFunk = strFunk.concat("\0");
      Lex.Init (strFunk);

/*
      if ( p.parse (g.Graph[0]) == 1 )
      {
         System.out.println ("Ergebnis: " + p.calc (0,1));
      }
      else
      {
         System.out.println ("syntaktisch falsch");
      }
*/
   }
}
