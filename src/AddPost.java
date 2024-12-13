import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.ArrayList;


public class AddPost extends JFrame {
    private JButton postButton;
    private JFormattedTextField formattedTextField1;
    private JButton addPhotoButton;
    private JCheckBox isStoryCheckBox;
    private JPanel panel1;
    private String photoPath;
    private ArrayList<String> photoPaths;
    private final static ContentManagement contentManagement = ContentManagement.getInstance();

    public AddPost(User currentUser) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel1);
        setVisible(true);
        this.photoPaths = new ArrayList<>();


        addPhotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select Photos");
                // Disable the "All files" filter
                fileChooser.setAcceptAllFileFilterUsed(false);
                // Allow selecting multiple files
                fileChooser.setMultiSelectionEnabled(true);

                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png");
                // Add the filter to the file chooser
                fileChooser.addChoosableFileFilter(filter);

                int returnValue = fileChooser.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File[] selectedFiles = fileChooser.getSelectedFiles();
                    // Loop through each selected file
                    for (File file : selectedFiles) {
                        photoPaths.add(file.getAbsolutePath());
                    }
                }
            }
        });
        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String postContent = formattedTextField1.getText();

                if (!postContent.isEmpty()) {
                    String typeOfContent;
                    if (isStoryCheckBox.isSelected()) {
                        typeOfContent = "Story";
                    } else {
                        typeOfContent = "Post";
                    }
                    String[] arrayOfPhotoPaths = new String[photoPaths.size()];
                    arrayOfPhotoPaths = photoPaths.toArray(arrayOfPhotoPaths);
                    contentManagement.addContent(arrayOfPhotoPaths,currentUser.getUserId(),postContent,typeOfContent);
                    System.out.println("User ID: " + currentUser.getUserId());
                    System.out.println("Post content: " + postContent);
                    System.out.println("type of content:"+ typeOfContent);
                    System.out.println("filePaths:");
                    for(String filepath:photoPaths)
                    {
                        System.out.println(filepath);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(panel1,"Please don't leave an empty content field","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

//    public static void main(String[] args) {
//        User user = new User("example@example.com", "john_doe", new Date());
//        AddPost addPost = new AddPost(user);
//    }

}
