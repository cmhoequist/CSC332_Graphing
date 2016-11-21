package visitors;

import model.Node;

import java.util.List;

/**
 * Created by Moritz on 11/18/2016.
 * <p></p>
 */
public interface Visitable {
    public List<List<Node>> accept(GraphVisitor visitor);
}
