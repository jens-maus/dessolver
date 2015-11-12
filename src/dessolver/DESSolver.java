package dessolver;

import java.io.*;
import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.net.*;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;

import dessolver.gui.*;
import dessolver.gui.theme.*;

/**
 * <p>Title: Differential Equation System Solver Application</p>
 * <p>Description: This class is the representation of the DESS Application</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: HTW-Dresden</p>
 * @author <A HREF="Jens.Langner@htw-dresden.de">Jens Langner</A>
 * @author <A HREF="Felix.Kraemer@htw-dresden.de">Felix Kraemer</A>
 * @version 1.5
 */

public class DESSolver extends JPanel
{
  static ResourceBundle res = ResourceBundle.getBundle("dessolver");
  public static final double VERSION  = 1.7;
  public static final String DATE     = "31-March-2002";
  public static final String APPNAME  = res.getString("TXT_APPNAME");

  private DESSolverApplet applet = null;

  // Used only if dessolver is an application
  private static JFrame frame = null;

  // the MainPanel
  private MainPanel mPanel;

  // The preferred size of the application
  private int PREFERRED_WIDTH = 640;
  private int PREFERRED_HEIGHT = 480;

  // now a variable for the ActionListener
  private DESSolverAction funcPainterAction = new DESSolverAction();

  // Possible Look & Feels
  private String mac      = "com.sun.java.swing.plaf.mac.MacLookAndFeel";
  private String metal    = "javax.swing.plaf.metal.MetalLookAndFeel";
  private String motif    = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
  private String windows  = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

  // The current Look & Feel
  private String currentLookAndFeel = metal;

  // Menus
  private JMenuBar menuBar = null;
  private JMenu themesMenu = null;
  private ButtonGroup lafMenuGroup = new ButtonGroup();
  private ButtonGroup themesMenuGroup = new ButtonGroup();
  private JMenuItem aboutMItem;
  private JMenuItem exitMItem;
  private JMenuItem exampleMItem_1;
  private JMenuItem exampleMItem_2;
  private JMenuItem exampleMItem_3;
  private JMenuItem exampleMItem_4;
  private JMenuItem exampleMItem_5;

  public DESSolver(DESSolverApplet applet)
  {
    this.applet = applet;

    // lets create the frame first
    frame = createFrame();

    // lets set the Look & Feel of the Application now.
    updateLookAndFeel();

    // setLayout(new BorderLayout());
    setLayout(new BorderLayout());

    // now we add the Menus
    frame.getContentPane().add(createMenus(), BorderLayout.NORTH);

    // set the preferred size of the demo
    setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));

    initialize();

    showDESSolver();
  }

  /**
   * MAIN method to start this Application now.
   * @param args
   */
  public static void main(String[] args)
  {
    DESSolver funcPainter = new DESSolver(null);
  }

  /**
  * Create a frame for DESSolver to reside in if brought up
  * as an application.
  */
  public JFrame createFrame()
  {
    JFrame frame = new JFrame();

    frame.getContentPane().setLayout(new BorderLayout());

    if(!isApplet()) frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    else  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    WindowListener l = new WindowAdapter()
    {
      public void windowClosing(WindowEvent e)
      {
        exitApp();
      }
    };

    frame.addWindowListener(l);

    return frame;
  }

  /**
  * Determines if this is an applet or application
  */
  public boolean isApplet()
  {
    return (applet != null);
  }

  /**
  * Returns the frame instance
  */
  public JFrame getFrame()
  {
    return frame;
  }

  /**
  * Bring up the DESSolver by showing the frame (only
  * applicable if coming up as an application, not an applet);
  */
  public void showDESSolver()
  {
    if(getFrame() != null)
    {
      // put swingset in a frame and show it
      JFrame f = getFrame();
      f.setTitle(APPNAME+" v"+VERSION+" ("+DATE+")");
      f.getContentPane().add(this, BorderLayout.CENTER);
      f.pack();
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      getFrame().setLocation(screenSize.width/2 - f.getSize().width/2, screenSize.height/2 - f.getSize().height/2);
      getFrame().show();
    }
  }

  /**
  * main init method that creates all necessary GUI components
  */
  public void initialize()
  {
    // lets add the Main Panel of the Application
    mPanel = new MainPanel();
    add(mPanel);
  }

  /**
  * exitApp()
  *
  */
  public void exitApp()
  {
    if(!isApplet())
    {
      System.exit(0);
    }
    else
    {
      frame.hide();
      frame.dispose();
    }
  }

  /**
  * Create menus
  */
  private JMenuBar createMenus()
  {
    JMenuItem mi;

    // ***** create the menubar ****
    JMenuBar menuBar = new JMenuBar();
    menuBar.getAccessibleContext().setAccessibleName(res.getString("MN_MAIN_DESC"));

    // ***** create Project Menu
    JMenu projectMenu = (JMenu)menuBar.add(new JMenu(res.getString("MN_PROJECT")));
    projectMenu.setMnemonic(res.getString("MN_PROJECT").charAt(0));
    projectMenu.getAccessibleContext().setAccessibleDescription(res.getString("MN_PROJECT_DESC"));
    aboutMItem = createMenuItem(projectMenu, res.getString("MN_PROJECT_ABOUT"), res.getString("MN_PROJECT_ABOUT_DESC"));
    projectMenu.addSeparator();
    exitMItem = createMenuItem(projectMenu, res.getString("MN_PROJECT_EXIT"), res.getString("MN_PROJECT_EXIT_DESC"));

    // ***** create Examples Menu
    JMenu exampleMenu = (JMenu)menuBar.add(new JMenu(res.getString("MN_EXAMPLES")));
    exampleMenu.setMnemonic(res.getString("MN_EXAMPLES").charAt(0));
    exampleMenu.getAccessibleContext().setAccessibleDescription(res.getString("MN_EXAMPLES_DESC"));
    exampleMItem_1 = createMenuItem(exampleMenu, res.getString("MN_EXAMPLES_EX1"), res.getString("MN_EXAMPLES_EX1"));
    exampleMItem_2 = createMenuItem(exampleMenu, res.getString("MN_EXAMPLES_EX2"), res.getString("MN_EXAMPLES_EX2"));
    exampleMItem_3 = createMenuItem(exampleMenu, res.getString("MN_EXAMPLES_EX3"), res.getString("MN_EXAMPLES_EX3"));
    exampleMItem_4 = createMenuItem(exampleMenu, res.getString("MN_EXAMPLES_EX4"), res.getString("MN_EXAMPLES_EX4"));
    exampleMItem_5 = createMenuItem(exampleMenu, res.getString("MN_EXAMPLES_EX5"), res.getString("MN_EXAMPLES_EX5"));

    // ***** create laf switcher menu
    JMenu lafMenu = (JMenu)menuBar.add(new JMenu(res.getString("MN_LAF")));
    lafMenu.setMnemonic(res.getString("MN_LAF").charAt(0));
    lafMenu.getAccessibleContext().setAccessibleDescription(res.getString("MN_LAF_DESC"));

    mi = createLafMenuItem(lafMenu, res.getString("MN_LAF_JAVA"), res.getString("MN_LAF_JAVA_DESC"), metal);
    mi.setSelected(true); // this is the default l&f

    createLafMenuItem(lafMenu, res.getString("MN_LAF_MAC"),     res.getString("MN_LAF_MAC_DESC"),      mac);
    createLafMenuItem(lafMenu, res.getString("MN_LAF_MOTIF"),   res.getString("MN_LAF_MOTIF_DESC"),    motif);
    createLafMenuItem(lafMenu, res.getString("MN_LAF_WINDOWS"), res.getString("MN_LAF_WINDOWS_DESC"),  windows);

    // ***** create themes menu
    themesMenu = (JMenu) menuBar.add(new JMenu(res.getString("MN_THEMES")));
    themesMenu.setMnemonic(res.getString("MN_THEMES").charAt(0));
    themesMenu.getAccessibleContext().setAccessibleDescription(res.getString("MN_THEMES_DESC"));

    mi = createThemesMenuItem(themesMenu, res.getString("MN_THEME_DEFAULT"), res.getString("MN_THEME_DEFAULT_DESC"), new DefaultMetalTheme());
    mi.setSelected(true); // This is the default theme

    createThemesMenuItem(themesMenu, res.getString("MN_THEME_AQUA"),      res.getString("MN_THEME_AQUA_DESC"),       new AquaTheme());
    createThemesMenuItem(themesMenu, res.getString("MN_THEME_CHAROCOAL"), res.getString("MN_THEME_CHAROCOAL_DESC"),  new CharcoalTheme());
    createThemesMenuItem(themesMenu, res.getString("MN_THEME_CONTRAST"),  res.getString("MN_THEME_CONTRAST_DESC"),   new ContrastTheme());
    createThemesMenuItem(themesMenu, res.getString("MN_THEME_EMERALD"),   res.getString("MN_THEME_EMERALD_DESC"),    new EmeraldTheme());
    createThemesMenuItem(themesMenu, res.getString("MN_THEME_RUBY"),      res.getString("MN_THEME_RUBY_DESC"),       new RubyTheme());

    return menuBar;
  }

  /**
  * Creates a generic menu item
  */
  private JMenuItem createMenuItem(JMenu menu, String label, String accessibleDescription)
  {
    JMenuItem mi = (JMenuItem)menu.add(new JMenuItem(label));

    mi.setMnemonic(label.charAt(0));
    mi.getAccessibleContext().setAccessibleDescription(accessibleDescription);
    mi.addActionListener(funcPainterAction);

    return mi;
  }

  /**
  * Creates a JRadioButtonMenuItem for the Themes menu
  */
  private JMenuItem createThemesMenuItem(JMenu menu, String label, String accessibleDescription, DefaultMetalTheme theme)
  {
    JRadioButtonMenuItem mi = (JRadioButtonMenuItem) menu.add(new JRadioButtonMenuItem(label));
    themesMenuGroup.add(mi);
    mi.setMnemonic(label.charAt(0));
    mi.getAccessibleContext().setAccessibleDescription(accessibleDescription);
    mi.addActionListener(new ChangeThemeAction(this, theme));

    return mi;
  }

  /**
  * Creates a JRadioButtonMenuItem for the Look and Feel menu
  */
  private JMenuItem createLafMenuItem(JMenu menu, String label, String accessibleDescription, String laf)
  {
    JMenuItem mi = (JRadioButtonMenuItem) menu.add(new JRadioButtonMenuItem(label));
    lafMenuGroup.add(mi);
    mi.setMnemonic(label.charAt(0));
    mi.getAccessibleContext().setAccessibleDescription(accessibleDescription);
    mi.addActionListener(new ChangeLookAndFeelAction(this, laf));

    mi.setEnabled(isAvailableLookAndFeel(laf));

    return mi;
  }

  /**
  * Sets the current L&F on each demo module
  */
  public void updateLookAndFeel()
  {
    try
    {
      UIManager.setLookAndFeel(currentLookAndFeel);
      SwingUtilities.updateComponentTreeUI(frame);
    }
    catch (Exception ex)
    {
      System.out.println(res.getString("TXT_FAILED_LAF") + currentLookAndFeel);
      System.out.println(ex);
    }
  }

  /**
  * isAvailableLookAndFeel
  *
  * special method that identifies which L&F is available at
  * this system
  */
  private boolean isAvailableLookAndFeel(String laf)
  {
    try
    {
      Class lnfClass = Class.forName(laf);
      LookAndFeel newLAF = (LookAndFeel)(lnfClass.newInstance());
      return newLAF.isSupportedLookAndFeel();
    }
    catch(Exception e)
    {
      return false;
    }
  }

  /**
  * Stores the current L&F, and calls updateLookAndFeel, below
  */
  public void setLookAndFeel(String laf)
  {
    if(currentLookAndFeel != laf)
    {
      currentLookAndFeel = laf;
      themesMenu.setEnabled(laf == metal);
      updateLookAndFeel();
    }
  }

  /**
   *
   * <p>Title: Action subclass of DESSolver</p>
   * <p>Description: This is a special inner class for Action handling of DESSolver</p>
   * <p>Copyright: Copyright (c) 2002</p>
   * <p>Company: HTW-Dresden</p>
   * @author <A HREF="Jens.Langner@htw-dresden.de">Jens Langner</A>
   * @version 1.0
   */
  class DESSolverAction implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      Object source = e.getSource();

      if(source.equals(exitMItem))
      {
        exitApp();
      }
      else if(source.equals(aboutMItem))
      {
        String message =  APPNAME+" v"+DESSolver.VERSION+"\n"+
                          "["+DESSolver.DATE+"]\n\n"+
                          "Copyright (c) 2002 by\n"+
                          "http://www.letzte-bankreihe.de/\n\n"+
                          "Authors:\n"+
                          "Juergen.Arndt@htw-dresden.de\n"+
                          "Felix.Kraemer@htw-dresden.de\n"+
                          "Jens.Langner@htw-dresden.de";

        JOptionPane.showMessageDialog(null, message, APPNAME+" v"+DESSolver.VERSION, JOptionPane.INFORMATION_MESSAGE);
      }
      else if(source.equals(exampleMItem_1))
      {
        double xMin = 0.0, xMax = 30.0, yMin = -3.0, yMax = 3.0, incr = 0.1;
        String function = "y2,0.5\n"+
                          "-0.2*y2-sin(y1),1.5";

        mPanel.setEnvironment(xMin, xMax, yMin, yMax, incr, function);
      }
      else if(source.equals(exampleMItem_2))
      {
        double xMin = 0.0, xMax = 120.0, yMin = 0.0, yMax = 3.0, incr = 0.1;
        String function = "-0.04*y1,2.5\n"+
                          "0.04*y1-0.03*y2,0\n"+
                          "0.03*y2,0";

        mPanel.setEnvironment(xMin, xMax, yMin, yMax, incr, function);
      }
      else if(source.equals(exampleMItem_3))
      {
        double xMin = 0.0, xMax = 1.0, yMin = 0.0, yMax = 300.0, incr = 0.15;
        String function = "1*y1,100\n"+
                          "-1*y2,100";

        mPanel.setEnvironment(xMin, xMax, yMin, yMax, incr, function);
      }
      else if(source.equals(exampleMItem_4))
      {
        double xMin = 0.0, xMax = 2.0, yMin = 0.0, yMax = 2.0, incr = 0.1;
        String function = "y1+x-1,1";

        mPanel.setEnvironment(xMin, xMax, yMin, yMax, incr, function);
      }
      else if(source.equals(exampleMItem_5))
      {
        double xMin = -10.0, xMax = 10.0, yMin = -10.0, yMax = 10.0, incr = 0.2;
        String function = "4*exp(0.8*x)-0.5*y1,-5";

        mPanel.setEnvironment(xMin, xMax, yMin, yMax, incr, function);
      }
    }
  }

  /**
  * ChangeLookAndFeelAction
  * special inner class
  */
  class ChangeLookAndFeelAction extends AbstractAction
  {
    DESSolver fpainter;
    String laf;

    protected ChangeLookAndFeelAction(DESSolver fpainter, String laf)
    {
      super("ChangeLaF");
      this.fpainter = fpainter;
      this.laf = laf;
    }

    public void actionPerformed(ActionEvent e)
    {
      fpainter.setLookAndFeel(laf);
    }
  }

  /**
  * ChangeThemeAction
  * special inner class
  */
  class ChangeThemeAction extends AbstractAction
  {
    DESSolver fpainter;
    DefaultMetalTheme theme;

    protected ChangeThemeAction(DESSolver fpainter, DefaultMetalTheme theme)
    {
      super("ChangeTheme");
      this.fpainter = fpainter;
      this.theme  = theme;
    }

    public void actionPerformed(ActionEvent e)
    {
      MetalLookAndFeel.setCurrentTheme(theme);
      fpainter.updateLookAndFeel();
    }
  }
}
