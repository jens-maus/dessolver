package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Graph.java
// Use  definiert graphen fuer die syntaxanalyse
//         ein graph ist ein vektor von  boegen
//-----------------------------------------------------------------------------

public class Graph implements Const
{
   Bogen [] gFunc =
   {
      /* 0 */ new Bogen (BgGr, Expr, 0, 1, 0),
      /* 1 */ new Bogen (BgSy, (int)'\0', 0, 2, 0),
      /* 2 */ new Bogen (BgEn, 0, 0, 0, 0)
   };

   Bogen [] gExpr =
   {
      /* 0 */  new Bogen (BgSy, (int)'-', ex1, 1, 2),
      /* 1 */  new Bogen (BgGr, Term, ex2, 4, 0),
      /* 2 */  new Bogen (BgSy, (int)'+', 0, 3, 3),
      /* 3 */  new Bogen (BgGr, Term, 0, 4, 0),
      /* 4 */  new Bogen (BgSy, (int)'+', ex4, 6, 5),
      /* 5 */  new Bogen (BgSy, (int)'-', ex5, 6, 7),
      /* 6 */  new Bogen (BgGr, Term, ex7, 4, 0),
      /* 7 */  new Bogen (BgEn, 0, ex8, 0, 0)

   };

   Bogen [] gTerm =
   {
      /* 0 */ new Bogen (BgGr, Fac, 0, 1, 0),
      /* 1 */ new Bogen (BgSy, '*', te1, 0, 2),
      /* 2 */ new Bogen (BgSy, '/', te2, 0, 3),
      /* 3 */ new Bogen (BgEn, 0, te3, 0, 0)
   };

   Bogen [] gFac =
   {
      /*  0 */ new Bogen (BgSy, (int)'-', ex1, 1, 1),    // ob das so ist,
                                                         // muss sich rausstellen
      /*  1 */ new Bogen (BgMo, mcNumb, fac1, 79, 2),

      /*  2 */ new Bogen (BgMo, mcIdent, fac2, 79, 3),

      /*  3 */ new Bogen (BgSy, (int)'(', fac3,  4, 6),     // <- ( Expr )
      /*  4 */ new Bogen (BgGr, Expr, 0, 5, 0),
      /*  5 */ new Bogen (BgSy, (int)')', fac4, 79, 0),

      /*  6 */ new Bogen (BgSy, sin, fac5, 7, 10),          // <- sinus
      /*  7 */ new Bogen (BgSy, (int)'(', 0, 8, 0),
      /*  8 */ new Bogen (BgGr, Expr, 0, 9, 0),
      /*  9 */ new Bogen (BgSy, (int)')', fac6, 79, 0),

      /* 10 */ new Bogen (BgSy, cos, fac7, 11, 14),         // <- cosinus
      /* 11 */ new Bogen (BgSy, (int)'(', 0, 12, 0),
      /* 12 */ new Bogen (BgGr, Expr, 0, 13, 0),
      /* 13 */ new Bogen (BgSy, (int)')', fac8, 79, 0),

      /* 14 */ new Bogen (BgSy, tan, fac9, 15, 18),         // <- tangens
      /* 15 */ new Bogen (BgSy, (int)'(', 0, 16, 0),
      /* 16 */ new Bogen (BgGr, Expr, 0, 17, 0),
      /* 17 */ new Bogen (BgSy, (int)')', fac10, 79, 0),

      /* 18 */ new Bogen (BgSy, sqrt, fac11, 19, 22),       // <- sqrt
      /* 19 */ new Bogen (BgSy, (int)'(', 0, 20, 0),
      /* 20 */ new Bogen (BgGr, Expr, 0, 21, 0),
      /* 21 */ new Bogen (BgSy, (int)')', fac12, 79, 0),

      /* 22 */ new Bogen (BgSy, exp, fac13, 23, 26),        // <- exp
      /* 23 */ new Bogen (BgSy, (int)'(', 0, 24, 0),
      /* 24 */ new Bogen (BgGr, Expr, 0, 25, 0),
      /* 25 */ new Bogen (BgSy, (int)')', fac14, 79, 0),

      /* 26 */ new Bogen (BgSy, ln, fac15, 27, 30),         // <- ln
      /* 27 */ new Bogen (BgSy, (int)'(', 0, 28, 0),
      /* 28 */ new Bogen (BgGr, Expr, 0, 29, 0),
      /* 29 */ new Bogen (BgSy, (int)')', fac16, 79, 0),

      /* 30 */ new Bogen (BgSy, log, fac17, 31, 34),        // <- log
      /* 31 */ new Bogen (BgSy, (int)'(', 0, 32, 0),
      /* 32 */ new Bogen (BgGr, Expr, 0, 33, 0),
      /* 33 */ new Bogen (BgSy, (int)')', fac18, 79, 0),

      /* 34 */ new Bogen (BgSy, pow, fac19, 35, 39),        // <- pow
      /* 35 */ new Bogen (BgSy, (int)'(', 0, 36, 0),
      /* 36 */ new Bogen (BgGr, Expr, 0, 37, 0),
      /* 37 */ new Bogen (BgGr, Expr, 0, 38, 0),
      /* 38 */ new Bogen (BgSy, (int)')', fac20, 79, 0),

      /* 39 */ new Bogen (BgSy, abs, fac21, 40, 43),        // <- abs
      /* 40 */ new Bogen (BgSy, (int)'(', 0, 41, 0),
      /* 41 */ new Bogen (BgGr, Expr, 0, 42, 0),
      /* 42 */ new Bogen (BgSy, (int)')', fac22, 79, 0),

      /* 43 */ new Bogen (BgSy, arcsin, fac23, 44, 47),     // <- arcsin
      /* 44 */ new Bogen (BgSy, (int)'(', 0, 45, 0),
      /* 45 */ new Bogen (BgGr, Expr, 0, 46, 0),
      /* 46 */ new Bogen (BgSy, (int)')', fac24, 79, 0),

      /* 47 */ new Bogen (BgSy, arccos, fac25, 48, 51),     // <- arccos
      /* 48 */ new Bogen (BgSy, (int)'(', 0, 49, 0),
      /* 49 */ new Bogen (BgGr, Expr, 0, 50, 0),
      /* 50 */ new Bogen (BgSy, (int)')', fac26, 79, 0),

      /* 51 */ new Bogen (BgSy, arctan, fac27, 52, 55),     // <- arctan
      /* 52 */ new Bogen (BgSy, (int)'(', 0, 53, 0),
      /* 53 */ new Bogen (BgGr, Expr, 0, 54, 0),
      /* 54 */ new Bogen (BgSy, (int)')', fac28, 79, 0),

      /* 55 */ new Bogen (BgSy, fac, fac29, 56, 59),        // <- fakultaet
      /* 56 */ new Bogen (BgSy, (int)'(', 0, 57, 0),
      /* 57 */ new Bogen (BgGr, Expr, 0, 58, 0),
      /* 58 */ new Bogen (BgSy, (int)')', fac30, 79, 0),

      /* 59 */ new Bogen (BgSy, sinh, fac31, 60, 63),       // <- sinh
      /* 60 */ new Bogen (BgSy, (int)'(', 0, 61, 0),
      /* 61 */ new Bogen (BgGr, Expr, 0, 62, 0),
      /* 62 */ new Bogen (BgSy, (int)')', fac32, 79, 0),

      /* 63 */ new Bogen (BgSy, cosh, fac33, 64, 67),       // <- cosh
      /* 64 */ new Bogen (BgSy, (int)'(', 0, 65, 0),
      /* 65 */ new Bogen (BgGr, Expr, 0, 66, 0),
      /* 66 */ new Bogen (BgSy, (int)')', fac34, 79, 0),

      /* 67 */ new Bogen (BgSy, tanh, fac35, 68, 71),       // <- tanh
      /* 68 */ new Bogen (BgSy, (int)'(', 0, 69, 0),
      /* 69 */ new Bogen (BgGr, Expr, 0, 70, 0),
      /* 70 */ new Bogen (BgSy, (int)')', fac36, 79, 0),

      /* 71 */ new Bogen (BgSy, coth, fac37, 72, 75),       // <- coth
      /* 72 */ new Bogen (BgSy, (int)'(', 0, 73, 0),
      /* 73 */ new Bogen (BgGr, Expr, 0, 74, 0),
      /* 74 */ new Bogen (BgSy, (int)')', fac38, 79, 0),

      /* 75 */ new Bogen (BgSy, cot, fac39, 76, 0),        // <- cot
      /* 76 */ new Bogen (BgSy, (int)'(', 0, 77, 0),
      /* 77 */ new Bogen (BgGr, Expr, 0, 78, 0),
      /* 78 */ new Bogen (BgSy, (int)')', fac40, 79, 0),

      /* 79 */ new Bogen (BgEn, 0, ex2, 0, 0)
   };

   Bogen [][] Graph = {gFunc, gExpr, gTerm, gFac};

   public Bogen [] getGraph(int i)
   {
      return Graph[i];
   }
}
