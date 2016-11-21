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
    private final String HELPPANEL = "helppanel";
    CardLayout cardLayout = new CardLayout();
    GraphPanel graphpanel = new GraphPanel();
    AlgorithmPanel algorithmpanel = new AlgorithmPanel();
    HelpPanel helppanel = new HelpPanel();

    public ProjectFrame(){
        //Set up menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem menuGraph = new JMenuItem("Graph View");
        menuGraph.addActionListener(e -> cardLayout.show(getContentPane(), GRAPHPANEL));
        JMenuItem menuAlg = new JMenuItem("Algorithm View");
        menuAlg.addActionListener(e -> cardLayout.show(getContentPane(), ALGPANEL));
        JMenuItem help = new JMenuItem("View Help Text");
        help.addActionListener(e -> cardLayout.show(getContentPane(), HELPPANEL));
        menu.add(menuGraph);
        menu.add(menuAlg);
        menu.add(help);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        //Set up content panel
        getContentPane().setLayout(cardLayout);
        add(graphpanel, GRAPHPANEL);
        add(algorithmpanel, ALGPANEL);
        add(helppanel, HELPPANEL);

        //Finish frame
        setTitle("CSC332-PA3");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public GraphPanel getGraphpanel(){
        return graphpanel;
    }

    public AlgorithmPanel getAlgorithmpanel(){
        return algorithmpanel;
    }

    public HelpPanel getHelppanel(){
        return helppanel;
    }
}
