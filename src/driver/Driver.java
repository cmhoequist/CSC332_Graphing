package driver;

import algorithms.Graph;
import algorithms.Solver;

/**
 * Created by Moritz on 11/16/2016.
 * <p></p>
 */
//TODO: input handler
        /*
        First: user selects between graph & digraph
        Then: user adds set of nodes and set of edges
         */
//TODO: graph builder
        /*
        Adjacency list is constructed and printed (DONE)
        Adjacency matrix is constructed and printed
        Graph diagram is constructed and displayed
         */
//TODO: write graph algorithms
        /*
        BFS is executed. Print the nodes in order of visitation, one component per line. (In progress)
        DFS is executed. Print the nodes in order of visitation, one component per line.
        Print a topological ordering for the graph (or error message if graph is not DAG).
        Print list of SCCs for graph (or error message if graph is not digraph).
         */
public class Driver {
    public static void main(String[] args){
        //Testing
        Graph graph = new Graph(false);
        graph.addEdge("a","f");
        graph.addEdge("a","c");
        graph.addEdge("a","d");
        graph.addEdge("c","b");
        graph.addEdge("c","b");
        graph.addEdge("e","b");
        graph.addEdge("f","e");
        System.out.println(graph.adjacencyList());

        Solver solver = new Solver(graph);
        System.out.println(solver.bfs());

    }
}
