import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class LoginGUI extends JFrame {
    public String usernameTrue = "testuser";
    private String passwordTrue = "123";
    private Container pane = getContentPane();

    public LoginGUI() {
        setTitle("Die Knasti GmbH");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        Image resized = getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        JLabel pictureLabel = new JLabel(new ImageIcon(resized));


        JPanel subPanel = new JPanel();
        GridLayout gl = new GridLayout(12, 2);
        subPanel.setLayout(gl);

        JLabel username = new JLabel("Username", SwingConstants.CENTER);
        username.setFont(new Font(Font.DIALOG, Font.BOLD, 22));

        JLabel password = new JLabel("Password", SwingConstants.CENTER);
        password.setFont(new Font(Font.DIALOG, Font.BOLD, 22));

        JTextField userField = new JTextField("testuser", SwingConstants.CENTER);
        userField.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));

        JPasswordField passwordField = new JPasswordField("123",SwingConstants.CENTER);
        passwordField.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));

        JButton loginButton = new JButton("LOGIN") {
        };
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userField.getText().equals(usernameTrue) && passwordField.getText().equals(passwordTrue)) {
                    System.out.println("Logged in!");
                    runLoggedImWindow();
                    dispose();
                } else {
                    System.out.println("wrong username or password!");
                    JOptionPane.showMessageDialog(getContentPane(), "wrong username or passwort", "Login failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        loginButton.setFont(new Font(Font.DIALOG, Font.ITALIC, 20));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(new Dimension(200, 200));
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(loginButton);

        subPanel.add(new JLabel(""));
        subPanel.add(new JLabel(""));
        subPanel.add(new JLabel(""));
        subPanel.add(username);
        subPanel.add(userField);
        subPanel.add(new JLabel(""));
        subPanel.add(password);
        subPanel.add(passwordField);
        subPanel.add(new JLabel(""));
        subPanel.add(buttonPanel);


        pane.setLayout(new BorderLayout(10, 10));
        pane.add(pictureLabel, BorderLayout.WEST);
        pane.add(subPanel, BorderLayout.CENTER);


    }

    private BufferedImage getImage() {
        try {
            BufferedImage logo = ImageIO.read(new File("src/Logo.png"));
            return logo;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void runLoggedImWindow() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                loggedInGUI newWindow = new loggedInGUI(usernameTrue);
                newWindow.setVisible(true);
            }
        });
    }


}