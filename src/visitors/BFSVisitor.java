package visitors;

import model.Graph;
import model.Node;

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

    @Override
    public List<Node> visit(Graph graph) {
        List<Node> visitOrder = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        Node s = graph.getStartingNode();
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
            v.setColor(1);
        }
        return visitOrder;
    }
}
