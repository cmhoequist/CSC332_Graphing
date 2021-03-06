package visitors;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Moritz on 11/17/2016.
 * <p></p>
 */
public class JUNGVisitor implements GraphVisitor{
    private JPanel graphPanel;

    private <T extends model.Graph> void buildGraph(Graph<String, String> graph, T model){
        model.getNodes().forEach((node)-> graph.addVertex(node.getName()));
        model.getNodes().forEach(
                node -> node.getChildren().forEach(
                    child -> graph.addEdge(node.getName()+child, node.getName(), child)
                )
        );

        Layout<String, String> layout = new CircleLayout<>(graph);
        layout.setSize(new Dimension(300, 300));
        BasicVisualizationServer<String, String> graphPanel = new BasicVisualizationServer<>(layout);
        graphPanel.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>(){
            @Override
            public String transform(String v){
                return v;
            }
        });
        this.graphPanel = graphPanel;
    }

    public JPanel getGraphPanel(){
        return graphPanel;
    }

    public List<List<Node>> visit(UGraph graph) {
        UndirectedGraph<String, String> jgraph = new UndirectedSparseGraph<>();
        buildGraph(jgraph, graph);
        return null;
    }


    public List<List<Node>> visit(DGraph graph) {
        DirectedGraph<String, String> jgraph = new DirectedSparseGraph<>();
        buildGraph(jgraph, graph);
        return null;
    }

    @Override
    public <T extends model.Graph> List<List<Node>> visit(T graph) {
        if(graph instanceof DGraph){
            visit((DGraph)graph);
        }
        else if(graph instanceof UGraph){
            visit((UGraph)graph);
        }
        return null;
    }
}
