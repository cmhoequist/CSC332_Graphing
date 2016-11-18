package visitors;

/**
 * Created by Moritz on 11/18/2016.
 * <p></p>
 */
public interface Visitable {
    public void accept(GraphVisitor visitor);
}
