package algorithms;

import java.util.List;

/**
 * Created by Moritz on 11/16/2016.
 * <p></p>
 */
public interface GraphVisitor {
    public List<String> visit(Graph node);
}
