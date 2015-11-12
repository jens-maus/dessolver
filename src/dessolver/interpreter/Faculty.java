package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Faculty.java
// Use  berechnung der fakultaet
//-----------------------------------------------------------------------------

class Faculty implements OpFuncPointer
{
   public double exec (double a, double b)
   {
      return fac (a);
   }

   public double fac (double x)
   {
      if ( x == 0 )
         return 1;

      return x*fac(x-1);
   }
}
