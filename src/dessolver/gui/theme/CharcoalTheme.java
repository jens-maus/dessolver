package dessolver.gui.theme;

import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * This class describes a theme using gray colors.
 *
 * @author Jens Langner
 */
public class CharcoalTheme extends DefaultMetalTheme
{
    public String getName() { return "Charcoal"; }

    private final ColorUIResource primary1 = new ColorUIResource(66, 33, 66);
    private final ColorUIResource primary2 = new ColorUIResource(90, 86, 99);
    private final ColorUIResource primary3 = new ColorUIResource(99, 99, 99);

    private final ColorUIResource secondary1 = new ColorUIResource(0, 0, 0);
    private final ColorUIResource secondary2 = new ColorUIResource(51, 51, 51);
    private final ColorUIResource secondary3 = new ColorUIResource(102, 102, 102);

    private final ColorUIResource black = new ColorUIResource(222, 222, 222);
    private final ColorUIResource white = new ColorUIResource(0, 0, 0);

    protected ColorUIResource getPrimary1() { return primary1; }
    protected ColorUIResource getPrimary2() { return primary2; }
    protected ColorUIResource getPrimary3() { return primary3; }

    protected ColorUIResource getSecondary1() { return secondary1; }
    protected ColorUIResource getSecondary2() { return secondary2; }
    protected ColorUIResource getSecondary3() { return secondary3; }

    protected ColorUIResource getBlack() { return black; }
    protected ColorUIResource getWhite() { return white; }
}
