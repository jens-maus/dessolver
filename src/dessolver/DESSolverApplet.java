package dessolver;

import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.net.*;
import java.io.*;

/**
 * <p>Title: DESSolver Applet</p>
 * <p>Description: This is the separate Applet class of the Function Painter</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author <A href="mailto:Jens.Langner@htw-dresden.de">Jens Langner</A>
 * @author <A HREF="Felix.Kraemer@htw-dresden.de">Felix Kraemer</A>
 * @version 1.0
 */

public class DESSolverApplet extends JApplet
{
  static ResourceBundle res = ResourceBundle.getBundle("dessolver");
  private boolean isStandalone = false;
  private DESSolver funcPainter = null;
  private JButton startButton;
  private JLabel statusLabel;
  private DESSolverApplet applet = null;

  // Get a parameter value
  public String getParameter(String key, String def)
  {
    return isStandalone ? System.getProperty(key, def) :
      (getParameter(key) != null ? getParameter(key) : def);
  }

  // Construct the applet
  public DESSolverApplet()
  {
  }

  // Initialize the applet
  public void init()
  {
    try
    {
      initialize();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  // Component initialization
  private void initialize() throws Exception
  {
    this.applet = this;

    startButton = new JButton(res.getString("BT_START_DESSOLVER"));
    startButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        SwingUtilities.invokeLater(new Runnable()
        {
          public void run()
          {
            // lets update the status of the Applet window
            AppletContext ac = getAppletContext();
            ac.showStatus(res.getString("TXT_LOADING_APPLET"));
            statusLabel.setText(res.getString("TXT_LOADING_APPLET"));
            applet.update(applet.getGraphics());

            // lets create a kind a application and signal it that
            // this is an applet and do not need any frame at all.
            funcPainter = new DESSolver(applet);

            if(funcPainter != null)
            {
              ac.showStatus(res.getString("TXT_LOADED_APPLET"));
              statusLabel.setText(res.getString("TXT_LOADED_APPLET"));
            }
          }
	});
      }
    });

    statusLabel = new JLabel();

    JPanel appPanel = new JPanel();
    appPanel.setLayout(new BorderLayout());
//    appPanel.setBackground(getColor());
    appPanel.add(startButton, BorderLayout.NORTH);
    appPanel.add(statusLabel, BorderLayout.CENTER);

    getContentPane().add(appPanel, BorderLayout.CENTER);
  }

  // Get Applet information
  public String getAppletInfo()
  {
    return res.getString("TXT_APPLETINFO");
  }

  // Get parameter info
  public String[][] getParameterInfo()
  {
    return null;
  }
}
