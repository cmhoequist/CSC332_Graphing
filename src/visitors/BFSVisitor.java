package visitors;

import model.DGraph;
import model.Graph;
import model.Node;
import model.UGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Returns a list of nodes in the order in which they are visited by a
 * breadth-first search.
 */
public class BFSVisitor implements GraphVisitor{
    /* BFS:
        Given some discovery queue Q
        Place a starting vertex s into Q
        color(s) = gray
        distance(s) = 0
        While Q not empty
            v = Q.head
            FOR each vertex u adjacent to v
                IF u.color == white
                    u.color = gray
                    u.distance = v.distance+1
                    u.predecessor = v
                    Q.append(u)
            v.color = black
            Q.pop
         */

    private <T extends Graph> List<List<Node>> getWeaklyConnectedComponents(T graph){
        Queue<Node> queue = new LinkedList<>();

        List<List<Node>> components = new ArrayList<>();
        List<Node> allNodes = graph.getNodes();
        while(!allNodes.isEmpty()){
            List<Node> visitOrder = new ArrayList<>();
            Node s = allNodes.get(0);
            s.setColor(0);
            s.setDistance(0);
            queue.add(s);
            visitOrder.add(s);

            while(!queue.isEmpty()){
                Node v = queue.poll();
                v.getChildren().forEach(childName ->{
                    Node u = graph.getNode(childName);
                    if(u.getColor() < 0){
                        u.setColor(0);
                        u.setDistance(v.getDistance());
                        u.setPredecessor(v.getName());
                        queue.add(u);
                        visitOrder.add(u);
                    }
                });
                allNodes.remove(v); //Equivalent to v.setColor(1)
            }
            components.add(visitOrder);
        }
        return components;
    }

    @Override
    public List<List<Node>> visit(DGraph graph){
       return getWeaklyConnectedComponents(graph);
    }


    @Override
    public List<List<Node>> visit(UGraph graph) {
        return getWeaklyConnectedComponents(graph);
    }
}
