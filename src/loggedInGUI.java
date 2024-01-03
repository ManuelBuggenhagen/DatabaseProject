import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class loggedInGUI extends JFrame implements ActionListener {
    private String sql;
    private JMenuItem checkUpdates;
    private JMenuItem aboutUs;
    private JMenuItem aGB;
    private JMenuItem executeQuery;
    Container pane = getContentPane();

    public loggedInGUI() {

        setTitle("Die Knasti GmbH");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        //Menübar
        JMenuBar menu = new JMenuBar();


        JMenu options = new JMenu("Options");
        JMenu advanced = new JMenu("advanced");
        JMenu help = new JMenu("Help");

        checkUpdates = new JMenuItem("check for Updates");
        checkUpdates.addActionListener(this);
        aboutUs = new JMenuItem("about us");
        aboutUs.addActionListener(this);
        aGB = new JMenuItem("unsere AGB");
        aGB.addActionListener(this);
        executeQuery = new JMenuItem("execute query directly");
        executeQuery.addActionListener(this);


        options.add(advanced);
        options.add(aGB);
        advanced.add(executeQuery);
        help.add(aboutUs);
        help.add(checkUpdates);
        menu.add(options);
        menu.add(help);
        setJMenuBar(menu);

        pane.setLayout(new BorderLayout());


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkUpdates) {
            JOptionPane.showMessageDialog(getContentPane(), "no updates available!\nSoftware is up to date", "checking for updates", JOptionPane.INFORMATION_MESSAGE);

        } else if (e.getSource() == aboutUs) {
            JOptionPane.showMessageDialog(getContentPane(),
                    "Die Knasti GmbH ist ein IT Dienstleistungsunternehmen für staatlich geführte Gefängnisse.\nWir haben uns" +
                            " besonders auf die Modernisierung und Digitalisierung alter Strukturen spezialisiert.\n In dieser Rolle  " +
                            "bieten wir umfangreiche IT Lösungen in den Bereichen Logistik, Infrastruktur,\nWirtschaft und vor allem Sicherheit an. " +
                            "Unser Unternehmen hat durch die jahrzehntelange Erfahrung\nviel Expertise aufgebaut und weiß daher nur allzu gut " +
                            "mit wie viel Aufwand Modernisierungen verbunden sind.\nDes Weiteren schulen wir unsere Kunden im Umgang " +
                            "mit den neuen Systemen weitreichend\nund stehen auch lange nach dem Umbau jederzeit für Probleme aller Art zur Verfügung.\n" +
                            "Bei Interesse oder Fragen aller Art wenden sie sich bitte an unsere Support Abteilung\nunter:   knastiSupport@gmail.com "
                    , "Wer wir sind",

                    JOptionPane.INFORMATION_MESSAGE);

        } else if (e.getSource() == aGB) {
            try {
                Desktop desktop = Desktop.getDesktop();
                if (desktop != null && desktop.isSupported(Desktop.Action.OPEN)) {
                    desktop.open(new File("src/AGB.pdf"));
                } else {
                    System.err.println("PDF-Datei kann nicht angezeigt werden!");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == executeQuery) {
            runQueryWindow();
        }

    }

    public void runQueryWindow() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                QueryGUI queryWindow = new QueryGUI();
                queryWindow.setVisible(true);
            }
        });
    }
}
