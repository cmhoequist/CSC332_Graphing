package view;

import model.Node;

import javax.swing.*;
import java.util.List;

/**
 * Created by Moritz on 11/21/2016.
 * <p></p>
 */
public class AlgorithmPanel extends JPanel {
    private JTextArea bfs = new JTextArea();
    private JTextArea dfs = new JTextArea();
    private JTextArea top = new JTextArea();
    private JTextArea scc = new JTextArea();

    public AlgorithmPanel(){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(new JLabel("BFS: nodes in order of traversal."));
        add(bfs);
        add(new JLabel("DFS: nodes in order of traversal."));
        add(dfs);
        add(new JLabel("Topological Order (if applicable):"));
        add(top);
        add(new JLabel("Strongly Connected Components (if applicable):"));
        add(scc);
    }

    public void setBFS(List<List<Node>> components) {
        StringBuilder sb = new StringBuilder();
        bfs.setText(bySCC(components));
    }

    public void setDFS(List<List<Node>> components) {
        dfs.setText(bySCC(components));
    }

    public void setOrder(List<Node> order) {
        if(order==null){
            top.setText("Graph is not a DAG.");
        }
        else{
            StringBuilder sb = new StringBuilder();
            order.forEach(node -> sb.append(node.getName()).append(" -> "));
            sb.setLength(sb.length()-3);
            top.setText(sb.toString());
        }
    }

    public void setSCC(List<List<Node>> order) {
        if(order==null){
            scc.setText("Graph is not a digraph.");
        }
        else{
            scc.setText(bySCC(order));
        }
    }

    private String bySCC(List<List<Node>> components){
        StringBuilder sb = new StringBuilder();
        components.forEach(component -> {
            if(component.isEmpty()){
                sb.append("- ");
            }
            else{
                component.forEach(node -> sb.append(node.getName()).append(", "));
                sb.setLength(sb.length()-2);
            }
            sb.append("\n");
        });
        return sb.toString();
    }
}
