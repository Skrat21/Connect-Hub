import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class LoginScreen extends JFrame {


    private JTextField emailField;
    private JPanel panel1;
    private JButton loginButton;

    private JPasswordField passwordField;
    private JButton signUpButton;

    private final static UserManagement userManagement = UserManagement.getInstance();

    public LoginScreen () {
        setTitle("Login screen");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,300);
        setVisible(true);
        setContentPane(panel1);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = Arrays.toString(passwordField.getPassword());// should just change the method signature unsafe casting
                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(panel1, "Incomplete ", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        User user = userManagement.validateUser(email,password);
                        if(user!=null)
                        {
                            setVisible(false);
                            new MainScreen(user);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(panel1,"fail","okay",JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (UnsupportedEncodingException ex) {
                        throw new RuntimeException(ex);
                    } catch (NoSuchAlgorithmException | IOException ex) {
                        throw new RuntimeException(ex);
                    }

                /* sends to backend
                userID = backend.confirmCredentials(email,password)// null if false else userID on the backend set status to online
                if(userID!= null)
                {
                    setVisible(false);
                    new LoginScreen(userID);
                }

                */
                }
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new SignUpScreen();
            }
        });
    }

    public static void main(String[] args) {
        new LoginScreen();
    }
}
