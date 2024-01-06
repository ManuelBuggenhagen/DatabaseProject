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

    public loggedInGUI(String user) {

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

        JLabel welcomeMessage = new JLabel();
        welcomeMessage.setText("<html>" + "Welcome back " + user + "<html>");
        welcomeMessage.setFont(welcomeMessage.getFont().deriveFont(30.0F));
        welcomeMessage.setSize(300, 300);
        JPanel welcomePanel = new JPanel();
        welcomePanel.add(welcomeMessage);
        welcomePanel.setBackground(Color.lightGray);


        options.add(advanced);
        options.add(aGB);
        advanced.add(executeQuery);
        help.add(aboutUs);
        help.add(checkUpdates);
        menu.add(options);
        menu.add(help);
        setJMenuBar(menu);


        JPanel middleSection = new JPanel(new GridLayout(3, 6));

        for (int i = 0; i < 18; i++) {
            switch (i) {
                case 0:
                    JButton angebot = new JButton("<html>" + "<br> Angebot: <br><br>" + "ID: number<br> " + "Bezeichnung: Varchar2<br>"
                            + "Beschreibung: Clob<br>" + "Kategorie: Varchar2" + "<html>");
                    angebot.setFont(angebot.getFont().deriveFont(20.0F));
                    angebot.setHorizontalAlignment(SwingConstants.LEFT);
                    angebot.setVerticalAlignment(SwingConstants.TOP);
                    middleSection.add(angebot);
                    break;
                case 1:
                    JButton besucher = new JButton("<html>" + "<br> Besucher: <br><br>" + "ID: number<br> "
                            + "Vorname: Varcher2<br>" + "Nachname: Varcher2<br>" + "Geburtsdatum: Date<br>"
                            + "Geschlecht: Varcher2<br>" + "Wohnort: Varchar2<br>" + "<html>");
                    besucher.setFont(besucher.getFont().deriveFont(20.0F));
                    besucher.setHorizontalAlignment(SwingConstants.LEFT);
                    besucher.setVerticalAlignment(SwingConstants.TOP);
                    middleSection.add(besucher);
                    break;
                case 2:
                    JButton besucherHistorie = new JButton("<html>" + "<br> Besucherhistorie: <br><br>" + "ID: number<br> "
                            + "Bes_ID: Number<br>" + "Datum: Date<br>" + "Notiz: Clob<br>" + "Besuchergrund: Varcher2" + "<html>");
                    besucherHistorie.setFont(besucherHistorie.getFont().deriveFont(20.0F));
                    besucherHistorie.setHorizontalAlignment(SwingConstants.LEFT);
                    besucherHistorie.setVerticalAlignment(SwingConstants.TOP);
                    middleSection.add(besucherHistorie);
                    break;
                case 3:
                    JButton dienstleistungsVertrag = new JButton("<html>" + "<br> Dienstleistungsvertrag: <br><br>" + "ID: number<br> "
                            + "Fir_ID: Number<br>" + "Beginn: Date<br>" + "Ende: Date<br>" + "Notiz: Clob<br>" + "Import_Export: Varchar2<br>" + "<html>");
                    dienstleistungsVertrag.setFont(dienstleistungsVertrag.getFont().deriveFont(20.0F));
                    dienstleistungsVertrag.setHorizontalAlignment(SwingConstants.LEFT);
                    dienstleistungsVertrag.setVerticalAlignment(SwingConstants.TOP);
                    middleSection.add(dienstleistungsVertrag);
                    break;
                case 4:
                    JButton finanzen = new JButton("<html>" + "<br> Finanzen: <br><br>" + "ID: number<br> "
                            + "Gef_ID: number<br>" + "Die_ID: number<br>" + "Jahr: Number<br>" + "Budget: Float<br>"
                            + "Ausgaben: Float<br>" + "Einnahmen: Float<br>" + "Sonstiges: Float<br>" + "<html>");
                    finanzen.setFont(finanzen.getFont().deriveFont(20.0F));
                    finanzen.setHorizontalAlignment(SwingConstants.LEFT);
                    finanzen.setVerticalAlignment(SwingConstants.TOP);
                    middleSection.add(finanzen);
                    break;
                case 5:
                    JButton firma = new JButton("<html>" + "<br> Firma: <br><br>" + "ID: number<br> "
                            + "Firmenname: Varchar2<br>" + "Adresse: Varcher2<br>" + "Telephonnummer: Number<br>" + "Email: Varcher2<br>"
                            + "Notiz: Clob<br>" + "AnsprechpartnerVorname: Varchar2<br>" + "AnsprechpartnerNachname: Varchar2<br>" + "<html>");
                    firma.setFont(firma.getFont().deriveFont(20.0F));
                    firma.setHorizontalAlignment(SwingConstants.LEFT);
                    firma.setVerticalAlignment(SwingConstants.TOP);
                    middleSection.add(firma);
                    break;
                case 6:
                    JButton gebaeudeTeil = new JButton("<html>" + "<br> Gebäudeteil: <br><br>" + "ID: number<br> "
                            + "Gef_ID: Number<br>" + "Fläche: Number<br>" + "Bezeichnung: Varcher2<br>" + "Zellenanzahl: Number" + "<html>");
                    gebaeudeTeil.setFont(gebaeudeTeil.getFont().deriveFont(20.0F));
                    gebaeudeTeil.setHorizontalAlignment(SwingConstants.LEFT);
                    gebaeudeTeil.setVerticalAlignment(SwingConstants.TOP);
                    middleSection.add(gebaeudeTeil);
                    break;
                case 7:
                    JButton gefaengnis = new JButton("<html>" + "<br> Gefängnis: <br><br>" + "ID: number<br> "
                            + "Adresse: Varchar2<br>" + "Standort: Varcher2<br>" + "Name: Varchar2" + "<html>");
                    gefaengnis.setFont(gefaengnis.getFont().deriveFont(20.0F));
                    gefaengnis.setHorizontalAlignment(SwingConstants.LEFT);
                    gefaengnis.setVerticalAlignment(SwingConstants.TOP);
                    middleSection.add(gefaengnis);
                    break;
                case 8:
                    JButton gegenstand = new JButton("<html>" + "<br> Gegenstand: <br><br>" + "ID: number<br> "
                            + "Bezeichnung: Varchar2<br>" + "Beschreibung: Clob<br>" + "<html>");
                    gegenstand.setFont(gegenstand.getFont().deriveFont(20.0F));
                    gegenstand.setHorizontalAlignment(SwingConstants.LEFT);
                    gegenstand.setVerticalAlignment(SwingConstants.TOP);
                    middleSection.add(gegenstand);
                    break;
                case 9:
                    JButton insasse = new JButton("<html>" + "<br> Insasse: <br><br>" + "ID: number<br> "
                            + "Vorname: Varchar2<br>" + "Nachname: Varchar2<br>" + "Inhaftierung: Date<br>" + "Entlassung: Date<br>"
                            + "Geschlecht: Varchar2<br>" + "Geburtsdatum: Date<br>" + "letzter_Wohnort: Varcher2<br>" + "Notiz: Clob<br>"
                            + "Sicherheitsstufe: Varchar2" + "<html>");
                    insasse.setFont(insasse.getFont().deriveFont(20.0F));
                    insasse.setHorizontalAlignment(SwingConstants.LEFT);
                    insasse.setVerticalAlignment(SwingConstants.TOP);
                    middleSection.add(insasse);
                    break;
                case 10:
                    JButton insassenHistorie = new JButton("<html>" + "<br> Insassenhistorie: <br><br>" + "ID: number<br> "
                            + "Ins_ID: Number<br>" + "Zel_ID: Number<br>" + "Datum: Date<br>" + "Notiz: Clob" + "<html>");
                    insassenHistorie.setFont(insassenHistorie.getFont().deriveFont(20.0F));
                    insassenHistorie.setHorizontalAlignment(SwingConstants.LEFT);
                    insassenHistorie.setVerticalAlignment(SwingConstants.TOP);
                    middleSection.add(insassenHistorie);
                    break;
                case 11:
                    JButton mitarbeiter = new JButton("<html>" + "<br> Mitarbeiter: <br><br>" + "ID: number<br> "
                            + "Mit_ID: Number<br>" + "Gef_ID: Number<br>" + "Vorname: Varcher2<br>" + "Nachname: Varcher2<br>"
                            + "Wohnort: Varcher2<br>" + "Geburtsdatum: Date<br>" + "Gehalt: Float<br>" + "Geschlecht: Varchar2" + "<html>");
                    mitarbeiter.setFont(mitarbeiter.getFont().deriveFont(20.0F));
                    mitarbeiter.setHorizontalAlignment(SwingConstants.LEFT);
                    mitarbeiter.setVerticalAlignment(SwingConstants.TOP);
                    middleSection.add(mitarbeiter);
                    break;
                case 12:
                    JButton rechte = new JButton("<html>" + "<br>  Rechte: <br><br>" + "ID: number<br> "
                            + "Bezeichnung: Varchar2<br>" + "Beschreibung: Clob<br>" + "<html>");
                    rechte.setFont(rechte.getFont().deriveFont(20.0F));
                    rechte.setHorizontalAlignment(SwingConstants.LEFT);
                    rechte.setVerticalAlignment(SwingConstants.TOP);
                    middleSection.add(rechte);
                    break;
                case 13:
                    JButton rolle = new JButton("<html>" + "<br> Rolle: <br><br>" + "ID: number<br> "
                            + "Bezeichnung: Varchar2<br>" + "Beschreibung: Clob<br>" + "<html>");
                    rolle.setFont(rolle.getFont().deriveFont(20.0F));
                    rolle.setHorizontalAlignment(SwingConstants.LEFT);
                    rolle.setVerticalAlignment(SwingConstants.TOP);
                    middleSection.add(rolle);
                    break;
                case 14:
                    JButton vertrag = new JButton("<html>" + "<br> Vertrag: <br><br>" + "ID: number<br> " + "Ang_ID: number<br> "
                            + "Ins_ID: number<br>" + "Bezeichnung: Varchar2<br>" + "Beginn: Date<br>" + "Ende: Date<br>"
                            + "Vergütung: Float<br>" + "Notiz: Clob" + "<html>");
                    vertrag.setFont(vertrag.getFont().deriveFont(20.0F));
                    vertrag.setHorizontalAlignment(SwingConstants.LEFT);
                    vertrag.setVerticalAlignment(SwingConstants.TOP);
                    middleSection.add(vertrag);
                    break;
                case 15:
                    JButton zelle = new JButton("<html>" + "<br> Zelle: <br><br>" + "ID: number<br> " + "Gef_ID: number<br> "
                            + "Zel_ID: number<br>" + "freie_Plätze: Number<br>" + "Plätze: Number<br>" + "Nummer: Nummer<br>"
                            + "Fläche: Number<br>" + "Sicherheitsstufe: Varchar2" + "<html>");
                    zelle.setFont(zelle.getFont().deriveFont(20.0F));
                    zelle.setHorizontalAlignment(SwingConstants.LEFT);
                    zelle.setVerticalAlignment(SwingConstants.TOP);
                    middleSection.add(zelle);
                    break;
                case 16:
                    JButton zellenTyp = new JButton("<html>" + "<br> Zellentyp: <br><br>" + "ID: number<br> " + "Bezeichnung: Varchar2<br>"
                            + "Beschreibung: Clob<br>" + "<html>");
                    zellenTyp.setFont(zellenTyp.getFont().deriveFont(20.0F));
                    zellenTyp.setHorizontalAlignment(SwingConstants.LEFT);
                    zellenTyp.setVerticalAlignment(SwingConstants.TOP);
                    middleSection.add(zellenTyp);
                    break;
                case 17:
                    JButton zwischenfallHistorie = new JButton("<html>" + "<br> Zwischenfallhistorie: <br><br>" + "ID: number<br> "
                            + "Ins_ID: Number<br>" + "Notiz: Clob<br>" + "Datum: Date<br>" + "Verstoß: Varchar2<br>" + "<html>");
                    zwischenfallHistorie.setFont(zwischenfallHistorie.getFont().deriveFont(20.0F));
                    zwischenfallHistorie.setHorizontalAlignment(SwingConstants.LEFT);
                    zwischenfallHistorie.setVerticalAlignment(SwingConstants.TOP);
                    middleSection.add(zwischenfallHistorie);
                    break;

                default:
                    break;

            }
        }

        pane.setLayout(new BorderLayout());
        pane.add(welcomePanel, BorderLayout.NORTH);
        pane.add(middleSection, BorderLayout.CENTER);

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
