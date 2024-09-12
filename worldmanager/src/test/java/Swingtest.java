import java.awt.Color;

import javax.swing.JFrame;
import com.thebest12lines.gui.WindowModifier;

public class Swingtest {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        WindowModifier windowModifier = new WindowModifier(frame);
        windowModifier.addJLabel("he",new Color(255,255,255));
        windowModifier.addJButton("he");
    }
}
