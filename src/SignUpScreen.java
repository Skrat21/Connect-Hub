import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

public class SignUpScreen extends JFrame {
    private JPanel panel1;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JButton datePickerButton;
    private JButton loginButton;
    private JButton signUpButton;
    private static final UserManagement userManagement = UserManagement.getInstance();
    public SignUpScreen() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,300);
        setVisible(true);
        setContentPane(panel1);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String username = usernameField.getText();
                String password = Arrays.toString(passwordField.getPassword());
                if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Error("please don't leave an empty field");
                } else {
                    if (!userManagement.isEmailInUse(email)) {
                        if(userManagement.findUserByUsername(username)==null)
                        {
                            try {
                                userManagement.addUser(email,password,username, new Date());
                                User loggedInUser=userManagement.validateUser(email,password);
                                new MainScreen(loggedInUser);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            } catch (NoSuchAlgorithmException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        else
                        {
                            Error("Username already exists");
                        }
                    }
                    else {
                        Error("Email already exists");

                    }
                }

            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginScreen();
            }
        });
    }


    private void Error(String errorMessage) {
        JOptionPane.showMessageDialog(panel1, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        new SignUpScreen();

    }


}
