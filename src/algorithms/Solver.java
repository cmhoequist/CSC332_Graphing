package algorithms;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Moritz on 11/16/2016.
 * <p></p>
 */
public class Solver {
    private Graph graph;

    public Solver(Graph graph){
        this.graph = graph;
    }

    public List<String> bfs(){
        /*
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
        List<String> visitOrder = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();
        Node s = graph.getStartingNode();
        s.setColor(0);
        s.setDistance(0);

        queue.add(s);
        visitOrder.add(s.getName());
        while(!queue.isEmpty()){
            Node v = queue.poll();
            v.getChildren().forEach(childName ->{
                Node u = graph.getNode(childName);
                if(u.getColor() < 0){
                    u.setColor(0);
                    u.setDistance(v.getDistance());
                    u.setPredecessor(v.getName());
                    queue.add(u);
                    visitOrder.add(u.getName());
                }
            });
            v.setColor(1);
        }
        return visitOrder;
    }

    public List<String> dfs(){
        return null;
    }

    public String topologicalOrder(){
        return null;
    }

    public List<Set<String>> sccs(){
        return null;
    }
}
