package driver;

import model.DGraph;
import model.UGraph;
import view.ProjectFrame;
import visitors.BFSVisitor;
import visitors.DFSVisitor;
import visitors.JUNGVisitor;

/**
 * Created by Moritz on 11/16/2016.
 * <p></p>
 */
//TODO: handle input
        /*
        (?) First: user selects between graph & digraph
        (?) Then: user adds set of nodes and set of edges
         */
//TODO: build graphs
        /*
        (DONE) Adjacency list is constructed
        (DONE) Adjacency matrix is constructed
        (DONE) Graph diagram is constructed
        (?) Display results appropriately
         */
//TODO: write algorithms
        /*
        (DONE) Run BFS and retrieve results.
        (?) Display BFS properly.
        (DONE) Run DFS and retrieve results.
        (?) Display DFS properly.
         (DONE) Display a topological ordering for the graph.
            (?) Error message if graph is not DAG.
         (DONE) Display SCCs for graph.
            (?) Error message if graph is not digraph.
         */
public class Driver {
    public static void main(String[] args){

        ProjectFrame frame = new ProjectFrame();
//
//        //Testing
//        UGraph graph = new UGraph();
//        graph.addEdge("a","f");
//        graph.addEdge("a","c");
//        graph.addEdge("c","b");
//        graph.addEdge("c","b");
//        graph.addEdge("d","g");
//        graph.addEdge("e","b");
//        graph.addEdge("f","e");
//        graph.addEdge("g","h");
//        graph.addEdge("h","d");
//
//        DGraph digraph = new DGraph();
//        digraph.addEdge("a","f");
//        digraph.addEdge("a","c");
//        digraph.addEdge("c","b");
//        digraph.addEdge("c","b");
//        digraph.addEdge("d","g");
//        digraph.addEdge("e","b");
//        digraph.addEdge("f","e");
//        digraph.addEdge("g","h");
//        digraph.addEdge("h","d");
//
//        BFSVisitor bfs = new BFSVisitor();
//        DFSVisitor dfs = new DFSVisitor();
//        JUNGVisitor jung = new JUNGVisitor();
//
////        System.out.println(graph.adjacencyList());
////        System.out.println(graph.adjacencyMatrix());
////        System.out.println(bfs.visit(graph));
////        System.out.println(dfs.visit(graph));
////        jung.visit(graph);
//
//        System.out.println(digraph.adjacencyList());
//        System.out.println(digraph.adjacencyMatrix());
//        jung.visit(digraph);
//        System.out.println("BFS: "+bfs.visit(digraph));
//        System.out.println("DFS: "+dfs.visit(digraph));
//        //*Doesn't filter out DAGs: this is technically just a traversal order for SCC calculation
//        System.out.println("Topological ordering*: "+dfs.topologicalOrder(digraph));
//        System.out.println("SCCs: "+dfs.sccs(digraph, dfs.topologicalOrder(digraph)));
    }
}
