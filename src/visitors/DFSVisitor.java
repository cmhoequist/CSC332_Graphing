package visitors;

import model.DGraph;
import model.Graph;
import model.Node;
import model.UGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Returns a list of nodes in the order in which they are visited by a
 * depth-first search.
 */
public class DFSVisitor implements GraphVisitor{
    private int time;

    public List<String> topologicalOrder(){
        return null;
    }

    public List<Set<String>> sccs(){
        return null;
    }


    /*
    for each vertex u in V(G)
        u.color = white
        u.predecessor = null
        time = 0
    for each vertex u in V(G)
        if u.color == white
            visit(u)
     */
    @Override
    public <T extends Graph> List<List<Node>> visit(T graph) {
        List<List<Node>> outcome = new ArrayList<>();
        time = 0;
        graph.getNodes().forEach(node -> {
            node.setColor(-1);
        });
        graph.getNodes().forEach(node -> {
            if(node.getColor() < 0){
                outcome.add(recurse(graph, node));
            }
        });
        return outcome;
    }

    /*
    u.color = gray
    u.firsttime = time
    time += 1
    for each v in adjacent(u)
        if v.color == white
            visit(v)
    u.color = black
    u.lasttime = time
    time += 1
     */
    private <T extends model.Graph> List<Node> recurse(T graph, Node current){
        List<Node> connected = new ArrayList<>();
        connected.add(current);

        current.setColor(0);
        current.setFirst(time);
        time += 1;
        current.getChildren().stream()
                .filter(childName -> graph.getNode(childName).getColor() < 0)
                .forEach(childName -> connected.addAll(recurse(graph, graph.getNode(childName))));
        current.setColor(1);
        current.setLast(time);
        time += 1;
        return connected;
    }
}
