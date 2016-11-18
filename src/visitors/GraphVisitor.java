package visitors;

import model.DirectedGraph;
import model.Graph;
import model.Node;
import model.UndirectedGraph;

import java.util.List;

/**
 * Returns a list of nodes in order of visitation. (Order depends on algorithm used for
 * the implementation).
 */
public interface GraphVisitor {
    /**
     * Returns a list of nodes in order of traversal, partitioned by components.
     * @param graph
     * @return
     */
    public List<List<Node>> visit(UndirectedGraph graph);

    public List<List<Node>> visit(DirectedGraph graph);
}
