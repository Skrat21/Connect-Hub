import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ContentPanel extends JPanel {
    private JPanel panel1;
    private JButton nextButton;
    private JScrollPane postPanel;
    public ContentPanel(ArrayList<Content> contentArrayList) {
        setVisible(true);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Content content: contentArrayList) {
                    try {
                        postPanel.add(new ContentLoader(content));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    postPanel.repaint();
                    postPanel.revalidate();
                }
            }
        });
    }
}
