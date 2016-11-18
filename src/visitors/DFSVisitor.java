package visitors;

import model.DirectedGraph;
import model.Graph;
import model.Node;
import model.UndirectedGraph;

import java.util.List;
import java.util.Set;

/**
 * Returns a list of nodes in the order in which they are visited by a
 * depth-first search.
 */
public class DFSVisitor implements GraphVisitor{
    public List<String> topologicalOrder(){
        return null;
    }

    public List<Set<String>> sccs(){
        return null;
    }

    @Override
    public List<List<Node>> visit(UndirectedGraph graph) {
        return null;
    }

    @Override
    public List<List<Node>> visit(DirectedGraph graph) {
        return null;
    }
}
