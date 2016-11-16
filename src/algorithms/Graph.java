package algorithms;

import java.util.*;

/**
 * In this implementation, the graph is considered a collection of nodes. Edges are the associations
 * between these nodes.
 */
public class Graph {
    private boolean isDigraph;
    private Map<String, Node> graph = new HashMap<>();

    private class Node{
        final String name;
        Set<String> children;

        public Node(String nodeName){
            this.name = nodeName;
        }

        public void addChild(String nodeName){
            children.add(nodeName);
        }

        @Override
        public int hashCode(){
            return name.hashCode();
        }

        public boolean equals(Node otherNode){
            return name.equals(otherNode.name);
        }
    }

    public Graph(boolean isDigraph){
        this.isDigraph = isDigraph;
    }

    /**
     * Adds an association between nodes. If the nodes are not yet recorded in the graph, they are
     * added automatically.
     * @param origin source node
     * @param endpoint target node
     */
    public void addEdge(String origin, String endpoint){
        //Make sure the graph contains the relevant nodes
        if(graph.get(origin)==null){
            graph.put(origin, new Node(origin));
        }
        if(graph.get(endpoint)==null){
            graph.put(origin, new Node(endpoint));
        }
        //Create an association from the origin node to the endpoint node
        graph.get(origin).addChild(endpoint);
        //If the graph is undirected, create an association from the endpoint node to the origin node
        if(!isDigraph){
            graph.get(endpoint).addChild(origin);
        }
    }

    public void addNode(String node){
        if(graph.get(node)==null){
            graph.put(node, new Node(node));
        }
    }

}
