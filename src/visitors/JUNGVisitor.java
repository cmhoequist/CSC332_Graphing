package visitors;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.UndirectedGraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.samples.SimpleGraphDraw;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import model.DGraph;
import model.Node;
import model.UGraph;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Moritz on 11/17/2016.
 * <p></p>
 */
public class JUNGVisitor implements GraphVisitor{

    @Override
    public List<List<Node>> visit(UGraph graph) {
        UndirectedGraph<String, String> jgraph = new UndirectedSparseGraph<String, String>();
        graph.getNodes().forEach((node)->{
            jgraph.addVertex(node.getName());
        });
        graph.getNodes().forEach(node ->{
            node.getChildren().forEach(child ->{
                jgraph.addEdge(node.getName()+child, node.getName(), child);
            });
        });

        SimpleGraphDraw sgv = new SimpleGraphDraw();
        Layout<String, String> layout = new CircleLayout<>(jgraph);
        layout.setSize(new Dimension(300, 300));
        BasicVisualizationServer<String, String> vis = new BasicVisualizationServer<>(layout);
        vis.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>(){
            @Override
            public String transform(String v){
                return v;
            }
        });

        JFrame frame = new JFrame("Simple Graph View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vis);
        frame.pack();
        frame.setVisible(true);


        return null;
    }

    @Override
    public List<List<Node>> visit(DGraph graph) {
        DirectedGraph<String, String> jgraph = new DirectedSparseGraph<>();
        graph.getNodes().forEach((node)->{
            jgraph.addVertex(node.getName());
        });
        graph.getNodes().forEach(node ->{
            node.getChildren().forEach(child ->{
                jgraph.addEdge(node.getName()+child, node.getName(), child);
            });
        });

        SimpleGraphDraw sgv = new SimpleGraphDraw();
        Layout<String, String> layout = new CircleLayout<>(jgraph);
        layout.setSize(new Dimension(300, 300));
        BasicVisualizationServer<String, String> vis = new BasicVisualizationServer<>(layout);
        vis.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>(){
            @Override
            public String transform(String v){
                return v;
            }
        });

        JFrame frame = new JFrame("Simple Graph View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vis);
        frame.pack();
        frame.setVisible(true);


        return null;
    }
}
