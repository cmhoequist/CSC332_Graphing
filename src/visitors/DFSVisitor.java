package visitors;

import model.DGraph;
import model.Graph;
import model.Node;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Returns a list of nodes in the order in which they are visited by a
 * depth-first search.
 */
public class DFSVisitor implements GraphVisitor{
    private int time;
    private Set<String> cycleSet = new HashSet<>();

    /**
     * This method finds a traversal order appropriate for determining SCCs.
     * In a DAG, this is a topological order.
     */
    public List<Node> topologicalOrder(DGraph graph){
        if(!graph.isAcyclic()){
            System.out.println("CYCLIC GRAPH: FILTER OUT");
        }
        visit(graph);
        return graph.getNodes().stream()
                .sorted((n1, n2) -> Integer.compare(n2.getLast(), n1.getLast())) //ascending order is default. We want desc.
                .collect(Collectors.toList());
    }

    public List<List<Node>> sccs(DGraph inputGraph){
        List<Node> nodes = topologicalOrder(inputGraph);
        List<List<Node>> scc = new ArrayList<>();

        DGraph graph = new DGraph();
        inputGraph.getNodes().forEach(
                (node) -> node.getChildren().forEach(
                        (child) -> graph.addEdge(child, node.getName())
                )
        );
        graph.getNodes().forEach(node -> node.setColor(-1));

        nodes.forEach(node ->{
            if(graph.getNode(node.getName()).getColor() < 0){

                graph.getNode(node.getName()).setColor(0);
                scc.add(recursivelyVisit(graph, graph.getNode(node.getName())));
            }
        });
        return scc;
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
        graph.getNodes().forEach(node -> node.setColor(-1));
        graph.getNodes().forEach(node -> {
            if(node.getColor() < 0){
                outcome.add(recursivelyVisit(graph, node));
            }
        });
        //Reset in case of repeated calls on graphs with similar nodes
        cycleSet.clear();
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
    private <T extends model.Graph> List<Node> recursivelyVisit(T graph, Node current){
        List<Node> connected = new ArrayList<>();
        connected.add(current);
        cycleSet.add(current.getName());

        current.setColor(0);
        current.setFirst(time);
        time += 1;
        current.getChildren().forEach(childName -> {
            if(graph.getNode(childName).getColor() == -1){
                connected.addAll(recursivelyVisit(graph, graph.getNode(childName)));
            }
            //Determine whether graph is cyclic by checking for edges pointing back to nodes earlier in this hierarchy
            else if(cycleSet.contains(childName) && graph.getNode(childName).getColor() == 0){
                graph.setIsAcyclic(false);
            }
        });
        current.setColor(1);
        current.setLast(time);
        time += 1;
        return connected;
    }
}
