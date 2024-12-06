import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class LoginScreen extends JFrame {


    private JTextField emailField;
    private JPanel panel1;
    private JButton loginButton;

    private JPasswordField passwordField;

    private final BackEnd backEnd = BackEnd.getInstance();

    public LoginScreen () {
        setSize(200,200);
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
                        User user = backEnd.validateUser(email,password);
                        if(user!=null)
                        {
                            JOptionPane.showMessageDialog(panel1,"Successs","cool",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(panel1,"fail","okay",JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (UnsupportedEncodingException ex) {
                        throw new RuntimeException(ex);
                    } catch (NoSuchAlgorithmException ex) {
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
    }

    public static void main(String[] args) {
        new LoginScreen();
    }
}
