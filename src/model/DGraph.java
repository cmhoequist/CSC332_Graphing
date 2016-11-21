package model;

import visitors.GraphVisitor;

import java.util.List;

/**
 * Created by Moritz on 11/18/2016.
 * <p></p>
 */
public class DGraph extends Graph{

    @Override
    public List<List<Node>> accept(GraphVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public void addEdge(String origin, String endpoint){
        //Make sure the graph contains the relevant nodes
        graph.putIfAbsent(origin, new Node(origin));
        graph.putIfAbsent(endpoint, new Node(endpoint));

        //Create an association from the origin node to the endpoint node
        graph.get(origin).addChild(endpoint);
    }
}
