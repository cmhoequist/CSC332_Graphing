package visitors;

import model.Graph;
import model.Node;

import java.util.*;

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
    public <T extends Graph> List<List<Node>> visit(T graph) {
        Queue<Node> queue = new LinkedList<>();
        List<List<Node>> components = new ArrayList<>();
        List<Node> allNodes = graph.getNodes();

        //Continue searching the graph until all nodes (all components) have been completed
        while(!allNodes.isEmpty()){
            //Initialize search through a connected component
            Set<Node> weak = new HashSet<>();
            List<Node> visitOrder = new ArrayList<>();
            Node s = allNodes.get(0);
            s.setColor(0);
            s.setDistance(0);
            queue.add(s);
            visitOrder.add(s);
            weak.add(s);
            //Apply BFS to all nodes in a weakly connected component
            while(!queue.isEmpty()){
                Node v = queue.poll();
                v.getChildren().forEach(childName ->{
                    Node u = graph.getNode(childName);
                    weak.add(u);
                    if(u.getColor() == -1){                 //-1 represents white
                        u.setColor(0);                      //0 represents gray
                        u.setDistance(v.getDistance());
                        u.setPredecessor(v.getName());
                        queue.add(u);
                        visitOrder.add(u);
                    }
                });
                allNodes.remove(v);                         //Equivalent to marking the node black (completed)
            }
            components.add(new ArrayList<>(weak));
        }
        return components;
    }
}
