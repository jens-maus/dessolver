package dessolver.gui;

import java.awt.Toolkit;
import java.util.Locale;
import javax.swing.*;
import javax.swing.text.*;
import java.text.*;

/**
 *
 * <p>Title: DecimalField class</p>
 * <p>Description: This is a special class to support change-validating decimal textfields</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: HTW-Dresden</p>
 * @author <A HREF="Jens.Langner@htw-dresden.de">Jens Langner</A>
 * @version 1.0
 */

public class DecimalField extends JTextField
{
  private Toolkit toolkit;
  private NumberFormat numberFormatter;

  public DecimalField(double value, int columns)
  {
    super(columns);
    toolkit = Toolkit.getDefaultToolkit();
    numberFormatter = NumberFormat.getNumberInstance(Locale.US);
    numberFormatter.setParseIntegerOnly(false);
    setValue(value);
  }

  public double getValue()
  {
    double retVal = 0;

    try
    {
      retVal = numberFormatter.parse(getText()).doubleValue();
    }
    catch(ParseException e)
    {
      // This should never happen because insertString allows
      // only properly formatted data to get in the field.
      toolkit.beep();
    }
    return retVal;
  }

  public void setValue(double value)
  {
    setText(numberFormatter.format(value));
  }

  protected Document createDefaultModel()
  {
    return new NumberDocument();
  }

  protected class NumberDocument extends PlainDocument
  {
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
    {
      char[] source = str.toCharArray();
      char[] result = new char[source.length];
      int j = 0;

      for(int i = 0; i < result.length; i++)
      {
        // we allow digits and a '-' & '.' sign, nothing else
        if( Character.isDigit(source[i]) ||
            source[i] == '-' || source[i] == '.')
        {
          result[j++] = source[i];
        }
        else
        {
          toolkit.beep();
          //System.err.println("insertString: " + source[i]);
        }
      }
      super.insertString(offs, new String(result, 0, j), a);
    }
  }
}