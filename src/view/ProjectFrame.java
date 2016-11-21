package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Moritz on 11/21/2016.
 * <p></p>
 */
public class ProjectFrame extends JFrame {
    CardLayout cardLayout = new CardLayout();

    public ProjectFrame(){
        JPanel content = new JPanel(cardLayout);
        getContentPane().setLayout(cardLayout);
        add(new GraphPanel(), "graphpanel");
        add(new AlgorithmPanel(), "algorithmpanel");

        //Finish frame
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
