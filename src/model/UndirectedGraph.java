package model;

import visitors.GraphVisitor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Moritz on 11/18/2016.
 * <p></p>
 */
public class UndirectedGraph extends Graph{

    /**
     * Adds an association between nodes. If the nodes are not yet recorded in the graph, they are
     * added automatically.
     * @param origin source node
     * @param endpoint target node
     */
    public void addEdge(String origin, String endpoint){
        //Make sure the graph contains the relevant nodes
        graph.putIfAbsent(origin, new Node(origin));
        graph.putIfAbsent(endpoint, new Node(endpoint));

        //Create an association from the origin node to the endpoint node and vice versa
        graph.get(origin).addChild(endpoint);
        graph.get(endpoint).addChild(origin);
    }
}
