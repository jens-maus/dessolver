package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Expr.java
// Use  klasse fuer bogen "expression"
//-----------------------------------------------------------------------------

class Expr extends Bogen
{
   Bogen [] gExpr =
   {
      new Bogen (BgGr, Term, 0, 1, 3),
      new Bogen (BgSy, '+', ex1, 0, 2),
      new Bogen (BgSy, '-', ex2, 0, 2),
      new Bogen (BgEn, 0, 0, 0, 0)
   };
}
