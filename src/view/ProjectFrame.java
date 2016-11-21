package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Moritz on 11/21/2016.
 * <p></p>
 */
public class ProjectFrame extends JFrame {
    private final String GRAPHPANEL = "graphpanel";
    private final String ALGPANEL = "algorithmpanel";
    CardLayout cardLayout = new CardLayout();

    public ProjectFrame(){
        //Set up menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem menuGraph = new JMenuItem("Graph View");
        menuGraph.addActionListener(e -> cardLayout.show(getContentPane(), GRAPHPANEL));
        JMenuItem menuAlg = new JMenuItem("Algorithm View");
        menuAlg.addActionListener(e -> cardLayout.show(getContentPane(), ALGPANEL));
        menu.add(menuGraph);
        menu.add(menuAlg);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        //Set up content panel
        JPanel content = new JPanel(cardLayout);
        getContentPane().setLayout(cardLayout);
        add(new GraphPanel(), GRAPHPANEL);
        add(new AlgorithmPanel(), ALGPANEL);

        //Finish frame
        setTitle("CSC332-PA3");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public String graphPanelName(){
        return GRAPHPANEL;
    }

    public String algPanelName(){
        return ALGPANEL;
    }
}
