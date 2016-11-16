package algorithms;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Moritz on 11/16/2016.
 * <p></p>
 */
public class Graph {
    private boolean isDigraph;
    private List<Node> graph = new LinkedList<>();

    private class Node{

    }

    public Graph(boolean isDigraph){
        this.isDigraph = isDigraph;
    }

}
