package view;

import model.DGraph;
import model.Graph;
import model.Node;
import model.UGraph;
import visitors.BFSVisitor;
import visitors.DFSVisitor;
import visitors.JUNGVisitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Moritz on 11/21/2016.
 * <p></p>
 */
public class ProjectFrame extends JFrame {
    //Constants
    private final String GRAPHPANEL = "graphpanel";
    private final String ALGPANEL = "algorithmpanel";
    private final String HELPPANEL = "helppanel";

    //View components
    CardLayout cardLayout = new CardLayout();
    GraphPanel graphpanel = new GraphPanel();
    AlgorithmPanel algorithmpanel = new AlgorithmPanel();
    HelpPanel helppanel = new HelpPanel();

    //Data
    Graph graph;

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

        //Populate panels
        JUNGVisitor jung = new JUNGVisitor();
        BFSVisitor bfs = new BFSVisitor();
        DFSVisitor dfs = new DFSVisitor();

        graphpanel.getUndirectedButton().addActionListener(e -> graph = new UGraph());
        graphpanel.getDirectedButton().addActionListener(e -> graph = new DGraph());
        graphpanel.getEdgeButton().addActionListener(e -> {
            String[] nodes = graphpanel.getEdgeData();
            graph.addEdge(nodes[0], nodes[1]);
        });
        graphpanel.getNodeButton().addActionListener(e -> graph.addNode(graphpanel.getNodeData()));
        graphpanel.getBuildButton().addActionListener(e -> {
            graphpanel.setAdjacencyList(graph.adjacencyList());
            graphpanel.setAdjacencyMatrix(graph.adjacencyMatrix());
            graph.accept(jung);
            graphpanel.setGraph(jung.getGraphPanel());

            algorithmpanel.setBFS(graph.accept(bfs));
            algorithmpanel.setDFS(graph.accept(dfs));
            if(graph.isAcyclic() && graph instanceof DGraph){
                List<Node> order = dfs.topologicalOrder((DGraph)graph);
                algorithmpanel.setOrder(order);
                algorithmpanel.setSCC(dfs.sccs((DGraph)graph, order));
            }
        });

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
