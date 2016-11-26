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
    //Tabs
    private JPanel buildPanel = new JPanel();
    private JPanel outcomePanel = new JPanel();
    private JPanel diagramPanel = new JPanel();

    //buildPanel components
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

    //outcomePanel components
    private JTextArea adjList = new JTextArea();
    private JTextArea adjMatrix = new JTextArea();

    public GraphPanel(){
        addTab("Graph Input", null, buildPanel, "Handles user input to construct graph");
        addTab("Graph Information", null, outcomePanel, "Displays information about the completed graph");
        addTab("Graph Diagram", null, diagramPanel, "Displays the graph diagram");

        //Construct buildPanel
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
        nodeList.setFixedCellWidth(250);
        JList<String> edgeList = new JList<>(edgeListModel);
        edgeList.setFixedCellWidth(250);
        JScrollPane nodeScroller = new JScrollPane(nodeList);
        JScrollPane edgeScroller = new JScrollPane(edgeList);
        displayGraph.add(nodeScroller);
        displayGraph.add(edgeScroller);
        //Finalize
        JPanel finalize = new JPanel();
        finalize.add(build);
        finalize.add(reset);

        //Assemble buildPanel
        buildPanel.setLayout(new BoxLayout(buildPanel, BoxLayout.PAGE_AXIS));
        buildPanel.add(graphType);
        generateGraph.setMaximumSize(displayGraph.getPreferredSize());
        buildPanel.add(generateGraph);
        buildPanel.add(displayGraph);
        buildPanel.add(finalize);

        //Initialize buildPanel
        IntStream.range(1, buildPanel.getComponentCount()).forEach(i -> {
            Container panel = (Container)buildPanel.getComponent(i);
            IntStream.range(0, panel.getComponentCount()).forEach(j -> {
               panel.getComponent(j).setEnabled(false);
            });
        });
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(undirected);
        buttonGroup.add(directed);

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

        //Construct outcomePanel
        adjMatrix.setFont(new Font("monospaced", Font.PLAIN, 12));
        outcomePanel.setLayout(new BoxLayout(outcomePanel, BoxLayout.PAGE_AXIS));
        outcomePanel.add(new JLabel("Adjacency List"));
        outcomePanel.add(adjList);
        outcomePanel.add(new JLabel("Adjacency Matrix"));
        outcomePanel.add(adjMatrix);
    }

    public void setAdjacencyList(Map<String, List<String>> adjacencies){
        //Map structure is self-explanatory: K=Node, V={list of adjacent nodes}
        StringBuilder sb = new StringBuilder();
        adjacencies.entrySet().forEach(entry -> {
            sb.append(entry.getKey()+": ");
            if(entry.getValue().isEmpty()){
                sb.append("- ");
            }
            else{
                entry.getValue().forEach(node -> sb.append(node+" -> "));
                sb.setLength(sb.length()-3);
            }
            sb.append("\n");
        });
        adjList.setText(sb.toString());
    }

    private int formatOffset = 0;
    private int padding = 1;
    public void setAdjacencyMatrix(List<List<String>> adjacencies){
        //Each internal List is a line in the matrix, starting with the header line. Each element in the line is a cell.
        adjacencies.get(0).forEach(entry -> formatOffset = Math.max(entry.length(), formatOffset));
        StringBuilder sb = new StringBuilder();
        //Build table header
        adjacencies.get(0).forEach(entry -> sb.append(String.format("%-"+(formatOffset+padding)+"s",entry)));
        sb.append("\n");
        //Build table body
        IntStream.range(1, adjacencies.size()).forEach(i -> {
            adjacencies.get(i).forEach(entry -> sb.append(String.format("%-" + (formatOffset+padding) + "s", entry)));
            sb.append("\n");
        });
        adjMatrix.setText(sb.toString());
    }

    public void setGraph(JPanel graph){
        diagramPanel.removeAll();
        diagramPanel.add(graph);
    }

    public void addNode(String nodeName){
        nodeListModel.addElement(nodeName);
    }

    public void addEdge(String start, String end){
        String connector = directed.isSelected() ? " -> " : " - ";
        edgeListModel.addElement(start+connector+end);
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
        directed.setSelected(false);
        undirected.setSelected(false);
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
