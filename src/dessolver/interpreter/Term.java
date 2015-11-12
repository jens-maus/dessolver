package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Term.java
// Use  klasse fuer bogen "term"
//-----------------------------------------------------------------------------

class Term extends Bogen
{
   Bogen [] gTerm =
   {
      new Bogen (BgGr, Fac, 0, 1, 3),
      new Bogen (BgSy, '*', te1, 0, 2),
      new Bogen (BgSy, '/', te2, 0, 0),
      new Bogen (BgEn, 0, 0, 0, 0)
   };
}
