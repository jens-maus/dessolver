package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Bogen.java
// Use  bogen fuer syntaxanalyse
//-----------------------------------------------------------------------------

class Bogen implements Const
{
   int BgD, BgX;
   int FuncIndex;
   int iNext, iAlt;

   private static SemFuncPointer [] sf =
   {
      new F0(), new Ex1(), new Ex2(), new Te1(), new Te2(), new Fac1()
   };

   public Bogen ()
   {

   }

   public Bogen ( int BgDesc, int BgXX, int func, int next, int alt )
   {
      BgD = BgDesc;
      BgX = BgXX;
      FuncIndex = func;
      iNext = next;
      iAlt = alt;
   }

   public Bogen (Bogen Bg)
   {
      BgD = BgNil;
      BgX = Bg.BgX;

      iNext = Bg.iNext;
      iAlt = Bg.iAlt;
   }
}
