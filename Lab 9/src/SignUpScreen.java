import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

public class SignUpScreen extends JFrame {
    private JPanel panel1;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JButton datePickerButton;
    private JButton button1;
    private JButton signUpButton;
    private BackEnd backEnd = BackEnd.getInstance();
    public SignUpScreen() {
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
                    if (backEnd.checkEmail(email)==null) {
                        if(backEnd.checkUsername(username))
                        {
                            try {
                                backEnd.createUser(email,password,username, new Date());
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
    }


    private void Error(String errorMessage) {
        JOptionPane.showMessageDialog(panel1, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        new SignUpScreen();

    }


}
