package view;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Moritz on 11/21/2016.
 * <p></p>
 */
public class GraphPanel extends JPanel {
    private JButton node = new JButton("Add Node");
    private JButton edge = new JButton("Add Edge");
    private JButton build = new JButton("Build Graph");
    private JRadioButton undirected = new JRadioButton("Undirected");
    private JRadioButton directed = new JRadioButton("Directed");
    private JButton reset = new JButton("Reset Choices");

    public GraphPanel(){
        add(new JLabel("GRAPHPANEL"));

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(undirected);
        buttonGroup.add(directed);
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
            *Then they add edges and nodes, one at a time (edge:JButton & node:JButton)
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

    private void reset(){
        //Should empty all fields and reset states to step one.
        //TODO
    }

    public String[] getEdgeData(){
        //Should return a start node name and an endpoint node name.
        return new String[]{null, null}; //TODO
    }

    public String getNodeData(){
        //Should return a node name.
        return new String();    //TODO
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


    public JButton getEdgeButton(){
        return edge;
    }

    public JButton getNodeButton(){
        return node;
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
