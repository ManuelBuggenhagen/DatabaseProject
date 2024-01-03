import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loggedInGUI extends JFrame {
    public loggedInGUI() {
        setTitle("new Window");
        setSize(1920,1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);



        //Menübar
        JMenuBar menu = new JMenuBar();

        JMenu options = new JMenu("Options");
        JMenuItem help = new JMenuItem("Help");
        JMenuItem aboutUs = new JMenuItem("about us");
        JMenuItem test = new JMenuItem("test");
        test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        options.add(test);
        options.add(help);
        options.add(aboutUs);
        menu.add(options);
        setJMenuBar(menu);
    }
}
