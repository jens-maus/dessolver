package dessolver.interpreter;

//-----------------------------------------------------------------------------
// Written 2002 by Juergen Arndt.
//
//
// File Node.java
// Use  knoten des baumes, der die gleichung repraesentiert
//-----------------------------------------------------------------------------

class Node implements Const
{
   Node lNode;
   Node rNode;
   Node parentNode;

   int operation, rang;

   char direction; // 'l' - fuer linker ast, 'r' - fuer rechter ast

   boolean terminated;

   double value;

   private String InfoString;

   OpFuncPointer [] op =
   {
      new Num(), new Add(), new Sub(), new Mul(), new Div(),
      new Sin(), new Cos(), new Tan(),
      new Sqrt(), new Exp(), new Ln(), new Log(), new Pow(), new Abs(),
      new Arcsin(), new Arccos(), new Arctan(), new Faculty(),
      new Sinh(), new Cosh(), new Tanh(), new Coth(), new Cot()
   };


   public void SetInfoString (String str)
   {
      InfoString = new String (str);
   }

   public String GetInfoString ()
   {
      return InfoString;
   }

   public void SetRang (int opcode)
   {
      rang = OpRang[operation=opcode] + Klammer.Ebene;
   }

//-----------------------------------------------------------------------------
// DiveToAttachPoint
//
// returns: knoten, _hinter_ dem der aktuelle knoten eingehaengt werden soll
//
// modifies: -
//
//-----------------------------------------------------------------------------

   public Node DiveToAttachPoint (Node Knoten)
   {
      if ( Knoten.lNode == null ) // linker knoten frei?
      {
         direction = 'l';
         return Knoten;
      }

      if ( Knoten.rang%100 < 2 ) // ist aktueller knoten zweistellige operation?
      {
         if ( Knoten.rNode == null ) // rechter knoten frei?
         {
            direction = 'r';
            return Knoten;
         }
         else
         {
            if ( Knoten.rNode.rang < rang ) // rechten ast hinuntergehen
            {
               direction = 'r';
               return DiveToAttachPoint (Knoten.rNode);
            }
            else
            {
               direction = 'r';
               return Knoten;
            }
         }
      }
      else // es ist einstellige operation, wir muessen den linken ast begucken
      {
         direction = 'l';

         if ( Knoten.lNode.rang < rang ) // muessen wir tiefer in den baum?
         {
            return DiveToAttachPoint (Knoten.lNode);
         }
         else
         {
            return Knoten;
         }
      }
   }

   public int AttachToTree (Node Knoten)
   {
      // es wird ein -und umgehaengt

      //System.out.println ("        MaxTopKnoten: " + Knoten.GetInfoString() );
      //System.out.println ("        Direction: " + direction);
      parentNode = Knoten;

      if ( direction == 'l' )
      {
         lNode = Knoten.lNode;

         if ( lNode != null )
         {
            lNode.parentNode = this;
         }

         Knoten.lNode = this;

         //System.out.println ("        this: " + InfoString);
         //System.out.println ("        parentknoten: " + parentNode.GetInfoString() );
         if ( lNode != null )
         {
            //System.out.println ("        lNode: " + lNode.GetInfoString() );
         }

         //System.out.println ("\n");

         return 1;
      }
      else
      {
         lNode = Knoten.rNode;

         if ( lNode != null )
         {
            lNode.parentNode = this;
         }

         Knoten.rNode = this;

         //System.out.println ("        this: " + InfoString);
         //System.out.println ("        parentknoten: " + parentNode.GetInfoString() );
         if ( lNode != null )
         {
           // System.out.println ("        lNode: " + lNode.GetInfoString() );
         }

         //System.out.println ("\n");
         return 1;
      }
   }

   public int AttachToTree (Node Knoten, double val)
   {
      value = val;
      return AttachToTree (Knoten);
   }


   public double getErgebnis ()
   {
      Ident id;
      double a, b;

      if ( operation == 0 )  // ist es etwa nur ne zahl oder variable
      {
         if ( InfoString.equals("numeral") || InfoString.equals("vorzeichenminuseins") )
         {
            return value;
         }
         else
         {
            //System.out.println (" --- getErgebnis ---");
            for ( int i = 0; i < vIdent.size(); i++ )
            {
               id = (Ident) vIdent.elementAt(i);

               if ( id.IdentName.equals(InfoString) )
               {
                  //System.out.println (" --- " + id.IdentName + ": " + id.IdentValue);
                  return (id.IdentValue);
               }
            }

            return 0; // dummy return
         }
      }
      else  // oder doch ne operation?
      {
         //System.out.println ("Knoten: " + InfoString);
         //System.out.println ("lnode : " + lNode.GetInfoString());

         a = lNode.getErgebnis ();  // wunderbare rekursion!!

         if ( rNode != null )
         {
            //System.out.println ("rnode : " + rNode.GetInfoString());
            if ( rNode.GetInfoString().equals("vorzeichenminuseins") )
            {
               //System.out.println ("Vorzeichen: " + rNode.value);
            }
            b = rNode.getErgebnis ();
            //System.out.println ("Vorzeichen_b: " + b);
         }
         else
         {
            b = 0;
         }

         //System.out.println ("a = " + a + "    b = " + b);
         return op[operation].exec(a, b);
      }
   }
}
