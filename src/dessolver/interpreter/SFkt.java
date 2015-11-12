package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File SFkt.java
// Use  fuer funktionspointer des lexer-automaten
//-----------------------------------------------------------------------------

interface SFkt extends Const
{

   void exec(Morphem Morph);
}
