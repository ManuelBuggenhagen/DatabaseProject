import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class loggedInGUI extends JFrame implements ActionListener {
    private String sql;
    private JMenuItem checkUpdates;
    private JMenuItem aboutUs;
    private JMenuItem aGB;
    private JMenuItem executeQuery;

    private JPanel middleSection;
    private JButton angebot;
    private JButton besucher;
    private JButton besucherHistorie;
    private JButton dienstleistungsVertrag;
    private JButton finanzen;
    private JButton firma;
    private JButton gebaeudeTeil;
    private JButton gefaengnis;
    private JButton gegenstand;
    private JButton insasse;
    private JButton insassenHistorie;
    private JButton mitarbeiter;
    private JButton rechte;
    private JButton rolle;
    private JButton vertrag;
    private JButton zelle;
    private JButton zellenTyp;
    private JButton zwischenfallHistorie;
    private JPanel welcomePanel;
    private JButton back;
    private JPanel backPanel;
    private JScrollPane tableShow;
    Container pane = getContentPane();
    private String currentUser;

    //main GUI window
    public loggedInGUI(String user) {

        setTitle("Die Knasti GmbH");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.currentUser = user;

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
        welcomePanel = new JPanel();
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


        middleSection = new JPanel(new GridLayout(3, 6, 10, 10));
        middleSection.setBackground(Color.lightGray);

        for (int i = 0; i < 18; i++) {
            switch (i) {
                case 0:
                    angebot = new JButton("<html>" + "<br><&nbsp Angebot: <br><br>" + "<&nbsp ID: number<br> "
                            + "<&nbsp Bezeichnung: Varchar2<br>" + "<&nbsp Beschreibung: Clob<br>"
                            + "<&nbsp Kategorie: Varchar2" + "<html>");
                    angebot.setFont(angebot.getFont().deriveFont(20.0F));
                    angebot.setHorizontalAlignment(SwingConstants.LEFT);
                    angebot.setVerticalAlignment(SwingConstants.TOP);
                    angebot.setBounds(30, 30, 30, 30);
                    angebot.addActionListener(this);
                    middleSection.add(angebot);
                    break;
                case 1:
                    besucher = new JButton("<html>" + "<br><&nbsp Besucher: <br><br>" + "<&nbsp ID: number<br> "
                            + "<&nbsp Vorname: Varcher2<br>" + "<&nbsp Nachname: Varcher2<br>" + "<&nbsp Geburtsdatum: Date<br>"
                            + "<&nbsp Geschlecht: Varcher2<br>" + "<&nbsp Wohnort: Varchar2<br>" + "<html>");
                    besucher.setFont(besucher.getFont().deriveFont(20.0F));
                    besucher.setHorizontalAlignment(SwingConstants.LEFT);
                    besucher.setVerticalAlignment(SwingConstants.TOP);
                    besucher.addActionListener(this);
                    middleSection.add(besucher);
                    break;
                case 2:
                    besucherHistorie = new JButton("<html>" + "<br><&nbsp Besucherhistorie: <br><br>" + "<&nbsp ID: number<br> "
                            + "<&nbsp Bes_ID: Number<br>" + "<&nbsp Datum: Date<br>" + "<&nbsp Notiz: Clob<br>" + "<&nbsp Besuchergrund: Varcher2" + "<html>");
                    besucherHistorie.setFont(besucherHistorie.getFont().deriveFont(20.0F));
                    besucherHistorie.setHorizontalAlignment(SwingConstants.LEFT);
                    besucherHistorie.setVerticalAlignment(SwingConstants.TOP);
                    besucherHistorie.addActionListener(this);
                    middleSection.add(besucherHistorie);
                    break;
                case 3:
                    dienstleistungsVertrag = new JButton("<html>" + "<br><&nbsp Dienstleistungsvertrag: <br><br>" + "<&nbsp ID: number<br> "
                            + "<&nbsp Fir_ID: Number<br>" + "<&nbsp Beginn: Date<br>" + "<&nbsp Ende: Date<br>" + "<&nbsp Notiz: Clob<br>" + "<&nbsp Import_Export: Varchar2<br>" + "<html>");
                    dienstleistungsVertrag.setFont(dienstleistungsVertrag.getFont().deriveFont(20.0F));
                    dienstleistungsVertrag.setHorizontalAlignment(SwingConstants.LEFT);
                    dienstleistungsVertrag.setVerticalAlignment(SwingConstants.TOP);
                    dienstleistungsVertrag.addActionListener(this);
                    middleSection.add(dienstleistungsVertrag);
                    break;
                case 4:
                    finanzen = new JButton("<html>" + "<br><&nbsp Finanzen: <br><br>" + "<&nbsp ID: number<br> "
                            + "<&nbsp Gef_ID: number<br>" + "<&nbsp Die_ID: number<br>" + "<&nbsp Jahr: Number<br>" + "<&nbsp Budget: Float<br>"
                            + "<&nbsp Ausgaben: Float<br>" + "<&nbsp Einnahmen: Float<br>" + "<&nbsp Sonstiges: Float<br>" + "<html>");
                    finanzen.setFont(finanzen.getFont().deriveFont(20.0F));
                    finanzen.setHorizontalAlignment(SwingConstants.LEFT);
                    finanzen.setVerticalAlignment(SwingConstants.TOP);
                    finanzen.addActionListener(this);
                    middleSection.add(finanzen);
                    break;
                case 5:
                    firma = new JButton("<html>" + "<br><&nbsp Firma: <br><br>" + "<&nbsp ID: number<br> "
                            + "<&nbsp Firmenname: Varchar2<br>" + "<&nbsp Adresse: Varcher2<br>" + "<&nbsp Telephonnummer: Number<br>" + "<&nbsp Email: Varcher2<br>"
                            + "<&nbsp Notiz: Clob<br>" + "<&nbsp AnsprechpartnerVM: Varchar2<br>" + "<&nbsp AnsprechpartnerNM: Varchar2<br>" + "<html>");
                    firma.setFont(firma.getFont().deriveFont(20.0F));
                    firma.setHorizontalAlignment(SwingConstants.LEFT);
                    firma.setVerticalAlignment(SwingConstants.TOP);
                    firma.addActionListener(this);
                    middleSection.add(firma);
                    break;
                case 6:
                    gebaeudeTeil = new JButton("<html>" + "<br><&nbsp Gebäudeteil: <br><br>" + "<&nbsp ID: number<br> "
                            + "<&nbsp Gef_ID: Number<br>" + "<&nbsp Fläche: Number<br>" + "<&nbsp Bezeichnung: Varcher2<br>" + "<&nbsp Zellenanzahl: Number" + "<html>");
                    gebaeudeTeil.setFont(gebaeudeTeil.getFont().deriveFont(20.0F));
                    gebaeudeTeil.setHorizontalAlignment(SwingConstants.LEFT);
                    gebaeudeTeil.setVerticalAlignment(SwingConstants.TOP);
                    gebaeudeTeil.addActionListener(this);
                    middleSection.add(gebaeudeTeil);
                    break;
                case 7:
                    gefaengnis = new JButton("<html>" + "<br><&nbsp Gefängnis: <br><br>" + "<&nbsp ID: number<br> "
                            + "<&nbsp Adresse: Varchar2<br>" + "<&nbsp Standort: Varcher2<br>" + "<&nbsp Name: Varchar2" + "<html>");
                    gefaengnis.setFont(gefaengnis.getFont().deriveFont(20.0F));
                    gefaengnis.setHorizontalAlignment(SwingConstants.LEFT);
                    gefaengnis.setVerticalAlignment(SwingConstants.TOP);
                    gefaengnis.addActionListener(this);
                    middleSection.add(gefaengnis);
                    break;
                case 8:
                    gegenstand = new JButton("<html>" + "<br><&nbsp Gegenstand: <br><br>" + "<&nbsp ID: number<br> "
                            + "<&nbsp Bezeichnung: Varchar2<br>" + "<&nbsp Beschreibung: Clob<br>" + "<html>");
                    gegenstand.setFont(gegenstand.getFont().deriveFont(20.0F));
                    gegenstand.setHorizontalAlignment(SwingConstants.LEFT);
                    gegenstand.setVerticalAlignment(SwingConstants.TOP);
                    gegenstand.addActionListener(this);
                    middleSection.add(gegenstand);
                    break;
                case 9:
                    insasse = new JButton("<html>" + "<br><&nbsp Insasse: <br><br>" + "<&nbsp ID: number<br> "
                            + "<&nbsp Vorname: Varchar2<br>" + "<&nbsp Nachname: Varchar2<br>" + "<&nbsp Geschlecht: Varchar2<br>"
                            + "<&nbsp Geburtsdatum: Date<br>" + "<&nbsp letzter_Wohnort: Varcher2<br>" + "<&nbsp Notiz: Clob<br>"
                            + "<&nbsp Sicherheitsstufe: Varchar2" + "<html>");
                    insasse.setFont(insasse.getFont().deriveFont(20.0F));
                    insasse.setHorizontalAlignment(SwingConstants.LEFT);
                    insasse.setVerticalAlignment(SwingConstants.TOP);
                    insasse.addActionListener(this);
                    middleSection.add(insasse);
                    break;
                case 10:
                    insassenHistorie = new JButton("<html>" + "<br><&nbsp Insassenhistorie: <br><br>" + "<&nbsp ID: number<br> "
                            + "<&nbsp Ins_ID: Number<br>" + "<&nbsp Zel_ID: Number<br>" + "<&nbsp Datum: Date<br>" + "<&nbsp Notiz: Clob" + "<html>");
                    insassenHistorie.setFont(insassenHistorie.getFont().deriveFont(20.0F));
                    insassenHistorie.setHorizontalAlignment(SwingConstants.LEFT);
                    insassenHistorie.setVerticalAlignment(SwingConstants.TOP);
                    insassenHistorie.addActionListener(this);
                    middleSection.add(insassenHistorie);
                    break;
                case 11:
                    mitarbeiter = new JButton("<html>" + "<br><&nbsp Mitarbeiter: <br><br>" + "<&nbsp ID: number<br> "
                            + "<&nbsp Mit_ID: Number<br>" + "<&nbsp Gef_ID: Number<br>" + "<&nbsp Vorname: Varcher2<br>" + "<&nbsp Nachname: Varcher2<br>"
                            + "<&nbsp Wohnort: Varcher2<br>" + "<&nbsp Geburtsdatum: Date<br>" + "<&nbsp Gehalt: Float<br>" + "<&nbsp Geschlecht: Varchar2" + "<html>");
                    mitarbeiter.setFont(mitarbeiter.getFont().deriveFont(20.0F));
                    mitarbeiter.setHorizontalAlignment(SwingConstants.LEFT);
                    mitarbeiter.setVerticalAlignment(SwingConstants.TOP);
                    mitarbeiter.addActionListener(this);
                    middleSection.add(mitarbeiter);
                    break;
                case 12:
                    rechte = new JButton("<html>" + "<br><&nbsp  Rechte: <br><br>" + "<&nbsp ID: number<br> "
                            + "<&nbsp Bezeichnung: Varchar2<br>" + "<&nbsp Beschreibung: Clob<br>" + "<html>");
                    rechte.setFont(rechte.getFont().deriveFont(20.0F));
                    rechte.setHorizontalAlignment(SwingConstants.LEFT);
                    rechte.setVerticalAlignment(SwingConstants.TOP);
                    rechte.addActionListener(this);
                    middleSection.add(rechte);
                    break;
                case 13:
                    rolle = new JButton("<html>" + "<br><&nbsp Rolle: <br><br>" + "<&nbsp ID: number<br> "
                            + "<&nbsp Bezeichnung: Varchar2<br>" + "<&nbsp Beschreibung: Clob<br>" + "<html>");
                    rolle.setFont(rolle.getFont().deriveFont(20.0F));
                    rolle.setHorizontalAlignment(SwingConstants.LEFT);
                    rolle.setVerticalAlignment(SwingConstants.TOP);
                    rolle.addActionListener(this);
                    middleSection.add(rolle);
                    break;
                case 14:
                    vertrag = new JButton("<html>" + "<br><&nbsp Vertrag: <br><br>" + "<&nbsp ID: number<br> " + "<&nbsp Ang_ID: number<br> "
                            + "<&nbsp Ins_ID: number<br>" + "<&nbsp Bezeichnung: Varchar2<br>" + "<&nbsp Beginn: Date<br>" + "<&nbsp Ende: Date<br>"
                            + "<&nbsp Vergütung: Float<br>" + "<&nbsp Notiz: Clob" + "<html>");
                    vertrag.setFont(vertrag.getFont().deriveFont(20.0F));
                    vertrag.setHorizontalAlignment(SwingConstants.LEFT);
                    vertrag.setVerticalAlignment(SwingConstants.TOP);
                    vertrag.addActionListener(this);
                    middleSection.add(vertrag);
                    break;
                case 15:
                    zelle = new JButton("<html>" + "<br><&nbsp Zelle: <br><br>" + "<&nbsp ID: number<br> " + "<&nbsp Gef_ID: number<br> "
                            + "<&nbsp Zel_ID: number<br>" + "<&nbsp Plätze: Number<br>" + "<&nbsp Nummer: Nummer<br>"
                            + "<&nbsp Fläche: Number<br>" + "<&nbsp Sicherheitsstufe: Varchar2" + "<html>");
                    zelle.setFont(zelle.getFont().deriveFont(20.0F));
                    zelle.setHorizontalAlignment(SwingConstants.LEFT);
                    zelle.setVerticalAlignment(SwingConstants.TOP);
                    zelle.addActionListener(this);
                    middleSection.add(zelle);
                    break;
                case 16:
                    zellenTyp = new JButton("<html>" + "<br><&nbsp Zellentyp: <br><br>" + "<&nbsp ID: number<br> " + "<&nbsp Bezeichnung: Varchar2<br>"
                            + "<&nbsp Beschreibung: Clob<br>" + "<html>");
                    zellenTyp.setFont(zellenTyp.getFont().deriveFont(20.0F));
                    zellenTyp.setHorizontalAlignment(SwingConstants.LEFT);
                    zellenTyp.setVerticalAlignment(SwingConstants.TOP);
                    zellenTyp.addActionListener(this);
                    middleSection.add(zellenTyp);
                    break;
                case 17:
                    zwischenfallHistorie = new JButton("<html>" + "<br><&nbsp Zwischenfallhistorie: <br><br>" + "<&nbsp ID: number<br> "
                            + "<&nbsp Ins_ID: Number<br>" + "<&nbsp Notiz: Clob<br>" + "<&nbsp Datum: Date<br>" + "<&nbsp Verstoß: Varchar2<br>" + "<html>");
                    zwischenfallHistorie.setFont(zwischenfallHistorie.getFont().deriveFont(20.0F));
                    zwischenfallHistorie.setHorizontalAlignment(SwingConstants.LEFT);
                    zwischenfallHistorie.setVerticalAlignment(SwingConstants.TOP);
                    zwischenfallHistorie.addActionListener(this);
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


    //Action event for every button inside the main GUI window
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

        } else if (e.getSource() == back) {
            System.out.println("backButton");
            backPanel.setVisible(false);
            welcomePanel.setVisible(true);
            tableShow.setVisible(false);
            middleSection.setVisible(true);

        } else if (e.getSource() == angebot) {
            openTable("angebot");

        } else if (e.getSource() == besucher) {
            openTable("besucher");

        } else if (e.getSource() == besucherHistorie) {
            openTable("besucherhistorie");

        } else if (e.getSource() == dienstleistungsVertrag) {
            openTable("dienstleistungsvertrag");

        } else if (e.getSource() == finanzen) {
            openTable("finanzen");

        } else if (e.getSource() == firma) {
            openTable("firma");

        } else if (e.getSource() == gebaeudeTeil) {
            openTable("gebaudeteil");

        } else if (e.getSource() == gefaengnis) {
            openTable("gefangnis");

        } else if (e.getSource() == gegenstand) {
            openTable("gegenstand");

        } else if (e.getSource() == insasse) {
            openTable("insasse");

        } else if (e.getSource() == insassenHistorie) {
            openTable("insassenhistorie");

        } else if (e.getSource() == mitarbeiter) {
            openTable("mitarbeiter");

        } else if (e.getSource() == rechte) {
            openTable("rechte");

        } else if (e.getSource() == rolle) {
            openTable("rolle");

        } else if (e.getSource() == vertrag) {
            openTable("vertrag");

        } else if (e.getSource() == zelle) {
            openTable("zelle");

        } else if (e.getSource() == zellenTyp) {
            openTable("zellentyp");

        } else if (e.getSource() == zwischenfallHistorie) {
            openTable("zwischenfallhistorie");

        }
    }

    private void runQueryWindow() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                QueryGUI queryWindow = new QueryGUI();
                queryWindow.setVisible(true);
            }
        });
    }

    private void openTable(String table) {
        try {
            welcomePanel.setVisible(false);
            back = new JButton();
            back.addActionListener(this);
            try {
                Image img = ImageIO.read(new File("src/go-back.png"));
                Image smaller = img.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
                back.setIcon(new ImageIcon(smaller));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            backPanel = new JPanel();
            backPanel.setLayout(new GridLayout(1, 6));
            backPanel.setBackground(Color.WHITE);
            backPanel.add(new JLabel(""));
            backPanel.add(new JLabel(""));
            backPanel.add(back);
            backPanel.add(new JLabel(""));
            backPanel.add(new JLabel(""));


            pane.add(backPanel, BorderLayout.NORTH);
            middleSection.setVisible(false);
            tableShow = new SQLRequest().sendSQLRequest("select * from " + table);
            pane.add(tableShow);

        } catch (SQLException e) {
            System.out.println("fehler!");
            JOptionPane.showMessageDialog(getContentPane(), "Die SQL Abfrage war leider fehlerhaft", "error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }


    }
}
