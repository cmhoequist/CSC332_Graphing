package visitors;

import model.DGraph;
import model.Graph;
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
    public <T extends Graph> List<List<Node>> visit(T graph);
}
