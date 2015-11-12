package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Ident.java
// Use  klasse fuer das objekt "Ident"
//-----------------------------------------------------------------------------

public class Ident implements Const
{
  String IdentName = new String();
  double IdentValue;

  public Ident(String idString)
  {
    IdentName = new String(idString);
  }

  public Ident(String idString, double value)
  {
    IdentName = new String(idString);
    IdentValue = value;
  }
}
