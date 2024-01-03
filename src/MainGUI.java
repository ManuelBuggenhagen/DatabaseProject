import javax.swing.*;
import java.awt.*;

public class MainGUI  {
    public void test(){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Overlay overlay = new Overlay();
                overlay.setVisible(true);
            }
        });
    }

}
