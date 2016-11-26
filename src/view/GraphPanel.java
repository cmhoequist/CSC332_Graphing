package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Created by Moritz on 11/21/2016.
 * <p></p>
 */
public class GraphPanel extends JTabbedPane {
    private JButton nodeButton = new JButton("Add Node");
    private JTextField nodeField = new JTextField();
    private JButton edgeButton = new JButton("Add Edge");
    private JTextField startNode = new JTextField();
    private JTextField endNode = new JTextField();
    private JButton build = new JButton("Build Graph");
    private JRadioButton undirected = new JRadioButton("Undirected");
    private JRadioButton directed = new JRadioButton("Directed");
    private JButton reset = new JButton("Reset Choices");
    private DefaultListModel<String> nodeListModel = new DefaultListModel<>();
    private DefaultListModel<String> edgeListModel = new DefaultListModel<>();

    private JPanel buildPanel = new JPanel();
    private JPanel outcomePanel = new JPanel();

    public GraphPanel(){
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(undirected);
        buttonGroup.add(directed);

        addTab("Graph Input", null, buildPanel, "Handles user input to construct graph");
        addTab("Graph Information", null, outcomePanel, "Displays information about the completed graph");

        //Generate graph type
        JPanel graphType = new JPanel();
        graphType.setLayout(new FlowLayout());
        graphType.add(directed);
        graphType.add(undirected);
        //Generate nodes and edges
        JPanel generateGraph = new JPanel();
        generateGraph.setLayout(new GridLayout(4, 3, 10, 10));
        generateGraph.add(new JLabel("Start Node"));
        generateGraph.add(new JLabel("End Node"));
        generateGraph.add(new JLabel());
        generateGraph.add(startNode);
        generateGraph.add(endNode);
        generateGraph.add(edgeButton);
        generateGraph.add(new JLabel("Node Name"));
        generateGraph.add(new JLabel());
        generateGraph.add(new JLabel());
        generateGraph.add(nodeField);
        generateGraph.add(new JLabel());
        generateGraph.add(nodeButton);
        //Display nodes and edges
        JPanel displayGraph = new JPanel();
        JList<String> nodeList = new JList<>(nodeListModel);
        JList<String> edgeList = new JList<>(edgeListModel);
        JScrollPane nodeScroller = new JScrollPane(nodeList);
        JScrollPane edgeScroller = new JScrollPane(edgeList);
        displayGraph.add(nodeScroller);
        displayGraph.add(edgeScroller);
        //Finalize
        JPanel finalize = new JPanel();
        finalize.add(build);
        finalize.add(reset);

        //Construct buildPanel
        buildPanel.setLayout(new BoxLayout(buildPanel, BoxLayout.PAGE_AXIS));
        buildPanel.add(graphType);
        generateGraph.setMaximumSize(displayGraph.getPreferredSize());
        buildPanel.add(generateGraph);
        buildPanel.add(displayGraph);
        buildPanel.add(finalize);

        IntStream.range(1, buildPanel.getComponentCount()).forEach(i -> {
            Container panel = (Container)buildPanel.getComponent(i);
            IntStream.range(0, panel.getComponentCount()).forEach(j -> {
               panel.getComponent(j).setEnabled(false);
            });
        });

        //Add listeners
        AbstractAction start = new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleGraphInput();
            }
        };
        directed.addActionListener(start);
        undirected.addActionListener(start);
        nodeButton.addActionListener(e -> {
            addNode(nodeField.getText());
            toggleGraphCompletion();
        });
        edgeButton.addActionListener(e -> {
            addEdge(startNode.getText(), endNode.getText());
            toggleGraphCompletion();
        });
        build.addActionListener(e -> toggleReset());
        reset.addActionListener(e -> reset());

        //TODO:
        /*
        This panel should display:
            *The graph construction screen (with the options described below)
            *The adjacency list (when construction is complete)
            *The adjacency matrix (when construction is complete)
            *The graph diagram (when construction is complete)
        I recommend the use of a tabbed pane to display each graph view component separately, but any user-friendly
        implementation is good. Because it's critical that the view play nice with the controller, I've outlined
        some assumptions and constraints resulting from the existing controller implementation below.

        For ease of coding, I have written the controller (:ProjectFrame) on the assumption that the user is locked
        into their choices at each of the three steps of graph creation:
            *First they choose a directed or undirected graph (directed:JRadioButton & undirected:JRadioButton)
            *Then they add edges and nodes, one at a time (edgeButton:JButton & nodeButton:JButton)
            *Finally they build the graph (build:JButton).
        If they ever want to redo ANY of those choices, they MUST reset (reset:JButton) ALL their choices & start over.
            *I recommend simply having all the fields and buttons that don't pertain to the step the user is on
            disabled at any given time - that way they can't accidentally screw with the controller.

        The controller is responsible for mediating between the model and the view, so a number of button listeners
        are implemented there. All of these (and the related getters) have already been implemented.
            *I have assumed everybody will use buttons for committing user input, but there are other (perhaps more
            user-friendly?) options, such as table input or straight-up action listeners on text fields.
            *NOTE that if you pursue one of these options, you will need to change the pre-built getters
            and update the relevant portions of the controller.

        All the method bodies labeled with a to-do flag (including this constructor) still need to be completed in
        accordance with the assumptions outlined above.
         */
    }

    private void toggleComponent(Container parent, boolean toggle){
        IntStream.range(0, parent.getComponentCount()).forEach(j -> {
            parent.getComponent(j).setEnabled(toggle);
        });
    }

    public void toggleGraphInput(){
        toggleComponent((Container)buildPanel.getComponent(0), false);
        toggleComponent((Container)buildPanel.getComponent(1), true);
        toggleComponent((Container)buildPanel.getComponent(2), true);
        toggleComponent((Container)buildPanel.getComponent(3), false);
    }

    public void toggleGraphCompletion(){
        toggleComponent((Container)buildPanel.getComponent(0), false);
        toggleComponent((Container)buildPanel.getComponent(1), true);
        toggleComponent((Container)buildPanel.getComponent(2), true);
        toggleComponent((Container)buildPanel.getComponent(3), true);
    }

    public void toggleReset(){
        toggleComponent((Container)buildPanel.getComponent(0), true);
        toggleComponent((Container)buildPanel.getComponent(1), false);
        toggleComponent((Container)buildPanel.getComponent(2), false);
        toggleComponent((Container)buildPanel.getComponent(3), false);
    }

    private void reset(){
        nodeListModel.clear();
        edgeListModel.clear();
        nodeField.setText("");
        startNode.setText("");
        endNode.setText("");
        toggleReset();
    }

    public String[] getEdgeData(){
        return new String[]{startNode.getText(), endNode.getText()};
    }

    public String getNodeData(){
        return nodeField.getText();
    }

    public void setAdjacencyList(Map<String, List<String>> adjacencies){
        //Map structure is self-explanatory: K=Node, V={list of adjacent nodes}
        //TODO
    }

    public void setAdjacencyMatrix(List<List<String>> adjacencies){
        //Each internal List is a line in the matrix, starting with the header line. Each element in the line is a cell.
        //TODO
    }

    public void setGraph(JPanel graph){
        //Self-explanatory. The parameter has been painted with the graph diagram.
        //TODO
    }

    public void addNode(String nodeName){
        nodeListModel.addElement(nodeName);
    }

    public void addEdge(String start, String end){
        String connector = directed.isSelected() ? "->" : "-";
        edgeListModel.addElement(start+connector+end);
    }

    public JButton getEdgeButton(){
        return edgeButton;
    }

    public JButton getNodeButton(){
        return nodeButton;
    }

    public JButton getBuildButton(){
        return build;
    }

    public JRadioButton getUndirectedButton(){
        return undirected;
    }

    public JRadioButton getDirectedButton(){
        return directed;
    }
}
