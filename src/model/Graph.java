package model;

import visitors.GraphVisitor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * In this implementation, the graph is considered a collection of nodes. Edges are the associations
 * between these nodes, and are recorded by the Node objects.
 */
public abstract class Graph {
    protected Map<String, Node> graph = new HashMap<>();

    /**
     * Adds an association between nodes. If the nodes are not yet recorded in the graph, they are
     * added automatically.
     * @param origin source node
     * @param endpoint target node
     */
    public abstract void addEdge(String origin, String endpoint);

    public void addNode(String node){
        graph.putIfAbsent(node, new Node(node));
    }

    public Node getStartingNode(){
        Iterator<Node> iter = graph.values().iterator();
        return iter.next();
    }

    public Node getNode(String nodeName){
        return graph.get(nodeName);
    }

    public List<Node> getNodes(){
        return graph.values().stream().collect(Collectors.toList());
    }

    public void accept(GraphVisitor visitor){
        visitor.visit(this);
    }

    public Map<String, List<String>> adjacencyList(){
        Map<String, List<String>> outcome = new HashMap<>();
        for(Map.Entry<String, Node> entry : graph.entrySet()){
            outcome.put(entry.getKey(), entry.getValue().getChildren().stream().collect(Collectors.toList()));
        }
        return outcome;
    }

    public String adjacencyMatrix(){
        StringBuilder sb = new StringBuilder();
        Map<String, List<String>> adj = adjacencyList();
        final int padding = 2;
        //Find offset length
        int offset = adj.keySet().stream().max((k1, k2) -> Integer.compare(k1.length(), k2.length())).get().length();
        //Print header
        sb.append(String.format("%-"+(offset+padding)+"s",""));
        adj.keySet().forEach(key -> sb.append(String.format("%-"+(offset+padding)+"s",key)));
        sb.append("\n");
        //Print body
        adj.entrySet().forEach((entry) -> {
            sb.append(String.format("%-"+(offset+padding)+"s",entry.getKey()));
            adj.keySet().forEach((key) ->  sb.append(String.format("%-"+(offset+padding)+"s",entry.getValue().contains(key) ? "X" : "O")));
            sb.append("\n");
        });
        return sb.toString();
    }


}
