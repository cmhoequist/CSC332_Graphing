package view;

import model.Node;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by Moritz on 11/21/2016.
 * <p></p>
 */
public class AlgorithmPanel extends JTabbedPane {
    private JPanel bfs = new JPanel();
    private JPanel dfs = new JPanel();
    private JPanel top = new JPanel();
    private JPanel scc = new JPanel();

    public AlgorithmPanel(){
        JLabel description = new JLabel("Nodes in order of traversal:");
        bfs.add(description);
        dfs.add(description);
        addTab("Breadth First Search", null, bfs, "Displays BFS results");
        addTab("Depth First Search", null, dfs,"Displays DFS results");
        addTab("Topological Order", null, top, "Displays topological ordering");
        addTab("Strongly Connected Components", null, scc, "Displays strongly connected components");

        setPreferredSize(new Dimension(800, 300));
    }

    public void setBFS(List<List<Node>> components) {
        StringJoiner sj = new StringJoiner(", ");
        components.forEach(component -> {
            sj.add("SCC: ");
            component.forEach(node -> sj.add(node.getName()));
            sj.add("\n");
        });
        bfs.add(new JTextArea(sj.toString()));
    }

    public void setDFS(List<List<Node>> components) {
        //TODO
    }

    public void setOrder(List<Node> order) {
        //TODO
    }

    public void setSCC(List<List<Node>> order) {
        //TODO
    }
}
