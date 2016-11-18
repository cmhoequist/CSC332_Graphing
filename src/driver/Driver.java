package driver;

import model.DGraph;
import model.UGraph;
import visitors.BFSVisitor;
import visitors.JUNGVisitor;

/**
 * Created by Moritz on 11/16/2016.
 * <p></p>
 */
//TODO: input handler (class View)
        /*
        (?) First: user selects between graph & digraph
        (?) Then: user adds set of nodes and set of edges
         */
//TODO: graph builder (class Graph, might need additional Diagram class)
        /*
        (DONE) Adjacency list is constructed
        (DONE) Adjacency matrix is constructed
        (?) Graph diagram is constructed
        (?) Display results
         */
//TODO: write algorithms (class BFSVisitor, DFSVisitor)
        /*
        (Moritz) BFS is executed. Print the nodes in order of visitation, one component per line.
        (?) DFS is executed. Print the nodes in order of visitation, one component per line.
                Print a topological ordering for the graph (or error message if graph is not DAG).
                Print list of SCCs for graph (or error message if graph is not digraph).
         */
public class Driver {
    public static void main(String[] args){
        //Testing
        UGraph graph = new UGraph();
        graph.addEdge("a","f");
        graph.addEdge("a","c");
        graph.addEdge("c","b");
        graph.addEdge("c","b");
        graph.addEdge("d","g");
        graph.addEdge("e","b");
        graph.addEdge("f","e");
        graph.addEdge("g","h");
        graph.addEdge("h","d");
        System.out.println(graph.adjacencyList());
        System.out.println(graph.adjacencyMatrix());

        DGraph digraph = new DGraph();
        digraph.addEdge("a","f");
        digraph.addEdge("a","c");
        digraph.addEdge("c","b");
        digraph.addEdge("c","b");
        digraph.addEdge("d","g");
        digraph.addEdge("e","b");
        digraph.addEdge("f","e");
        digraph.addEdge("g","h");
        digraph.addEdge("h","d");
        System.out.println(digraph.adjacencyList());
        System.out.println(digraph.adjacencyMatrix());


        BFSVisitor bfs = new BFSVisitor();
        System.out.println(bfs.visit(graph));
        System.out.println(bfs.visit(digraph));

        JUNGVisitor jung = new JUNGVisitor();
        jung.visit(graph);
        jung.visit(digraph);
    }
}
