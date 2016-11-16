package visitors;

import model.Graph;
import model.Node;

import java.util.List;
import java.util.Set;

/**
 * Returns a list of nodes in the order in which they are visited by a
 * depth-first search.
 */
public class DFSVisitor implements GraphVisitor{
    @Override
    public List<Node> visit(Graph graph) {
        return null;
    }

    public List<String> topologicalOrder(){
        return null;
    }

    public List<Set<String>> sccs(){
        return null;
    }
}
