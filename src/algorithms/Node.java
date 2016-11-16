package algorithms;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Moritz on 11/16/2016.
 * <p></p>
 */
public class Node{
    //Structural fields
    private final String name;
    private Set<String> children = new HashSet<>();
    //Algorithmic fields
    private int distance;
    private int color;
    private String predecessor;

    public Node(String nodeName){
        this.name = nodeName;
        distance = -1;
        color = -1;
        predecessor = null;
    }

    public void addChild(String nodeName) {
        children.add(nodeName);
    }

    public void setDistance(int distance){
        this.distance = distance;
    }

    public void setColor(int color){
        this.color = color;
    }

    public void setPredecessor(String predecessor){
        this.predecessor = predecessor;
    }

    public int getColor(){
        return color;
    }

    public int getDistance(){
        return distance;
    }

    public String getPredecessor(){
        return predecessor;
    }

    public String getName(){
        return name;
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }

    public boolean equals(Node otherNode){
        return name.equals(otherNode.name);
    }

    public Set<String> getChildren(){
        return children;
    }
}
