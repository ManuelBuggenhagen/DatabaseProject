import java.awt.*;

public class MainGUI  {
    public void test(){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginGUI loginGUI = new LoginGUI();
                loginGUI.setVisible(true);
            }
        });
    }

}
