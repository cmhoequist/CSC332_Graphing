package visitors;

import model.DGraph;
import model.Node;
import model.UGraph;

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
    public List<List<Node>> visit(UGraph graph);

    public List<List<Node>> visit(DGraph graph);
}
