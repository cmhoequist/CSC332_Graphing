package visitors;

import model.Graph;
import model.Node;

import java.util.List;

/**
 * Returns a list of nodes in order of visitation. (Order depends on algorithm used for
 * the implementation).
 */
public interface GraphVisitor {
    public List<Node> visit(Graph graph);
}
