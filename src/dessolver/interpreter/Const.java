package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Const.java
// Use  interface fuer saemtliche konstanten
//-----------------------------------------------------------------------------

import java.util.Vector;
import java.util.Stack;

public interface Const
{
   //static int Klammer = 0;

   static final int mcEmpty = 0;
   static final int mcSymb  = 1;
   static final int mcNumb  = 2;
   static final int mcIdent = 3;
   static final int mcStrng = 4;

   static final int zNil = 0;
   static final int zErg = 128;
   static final int  zle = 2;
   static final int  zge = 3;

   // --- werte fuer "operation" in Node.java

   static final int numeral = 0;
   static final int ident = 0;
   static final int add = 1;
   static final int sub = 2;
   static final int mul = 3;
   static final int div = 4;
   static final int sin = 5;
   static final int cos = 6;
   static final int tan = 7;
   static final int sqrt = 8;
   static final int exp = 9;
   static final int ln = 10;
   static final int log = 11;
   static final int pow = 12;
   static final int abs = 13;
   static final int arcsin = 14;
   static final int arccos = 15;
   static final int arctan = 16;
   static final int fac = 17;
   static final int sinh = 18;
   static final int cosh = 19;
   static final int tanh = 20;
   static final int coth = 21;
   static final int cot = 22;

   // --- werte fuer zugriff auf die einzelnen graphen in Graph.java

   static final int Func = 0;
   static final int Expr = 1;
   static final int Term = 2;
   static final int Fac  = 3;

   // --- morphemidentifikation in Graph.java

   static final int BgNil = 0;
   static final int  BgSy = 1;
   static final int  BgMo = 2;
   static final int  BgGr = 4;
   static final int  BgAl = 8;
   static final int  BgEn = 16;

   // --- werte fuer zugriff auf das feld der funktionspointer

   static final int    F0 =  0;
   static final int   ex1 =  1;
   static final int   ex2 =  2;
   static final int   ex4 =  3;
   static final int   ex5 =  4;
   static final int   ex7 =  5;
   static final int   ex8 =  6;
   static final int   te1 =  7;
   static final int   te2 =  8;
   static final int   te3 =  9;
   static final int  fac1 = 10;
   static final int  fac2 = 11;
   static final int  fac3 = 12;
   static final int  fac4 = 13;
   static final int  fac5 = 14;
   static final int  fac6 = 15;
   static final int  fac7 = 16;
   static final int  fac8 = 17;
   static final int  fac9 = 18;
   static final int fac10 = 19;
   static final int fac11 = 20;
   static final int fac12 = 21;
   static final int fac13 = 22;
   static final int fac14 = 23;
   static final int fac15 = 24;
   static final int fac16 = 25;
   static final int fac17 = 26;
   static final int fac18 = 27;
   static final int fac19 = 28;
   static final int fac20 = 29;
   static final int fac21 = 30;
   static final int fac22 = 31;
   static final int fac23 = 32;
   static final int fac24 = 33;
   static final int fac25 = 34;
   static final int fac26 = 35;
   static final int fac27 = 36;
   static final int fac28 = 37;
   static final int fac29 = 38;
   static final int fac30 = 39;
   static final int fac31 = 40;
   static final int fac32 = 41;
   static final int fac33 = 42;
   static final int fac34 = 43;
   static final int fac35 = 44;
   static final int fac36 = 45;
   static final int fac37 = 46;
   static final int fac38 = 47;
   static final int fac39 = 48;
   static final int fac40 = 49;

   //String [] SWort = { "sin", "cos", "tan", "sqrt", "exp", "ln" };

   Stack stack = new Stack();

   // nur bis zum cot (22) erstmal
   int [] OpRang = {3, 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                    2, 2, 2, 2, 2};

   int IdentCount = 0;
   Vector vIdent = new Vector(1,1);          // vector für werte
   Vector KeyWordVector = new Vector(1,1);   // vector fuer schluesselwoerter
}
