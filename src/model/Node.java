package model;

import java.util.HashSet;
import java.util.Set;

/**
 * Each Node represents a vertex in a graph. Each vertex keeps a record of its label and its associated
 * edges. (Digraph Nodes record only edges that originate in that node.)
 */
public class Node{
    //Structural fields
    private final String name;
    private Set<String> children = new HashSet<>();
    //Algorithmic fields
    private int distance, first, last;
    private int color;
    private String predecessor;

    public Node(String nodeName){
        this.name = nodeName;
        distance = -1;
        color = -1;
        predecessor = null;
    }

    /*STRUCTURAL METHODS----------------------------------------------------------------------------------------------*/
    public void addChild(String nodeName) {
        children.add(nodeName);
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }

    public boolean equals(Node otherNode){
        return name.equals(otherNode.name);
    }

    /*ALGORITHMIC METHODS---------------------------------------------------------------------------------------------*/
    public void setColor(int color){
        this.color = color;
    }

    public void setDistance(int distance){
        this.distance = distance;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public void setLast(int last) {
        this.last = last;
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

    public Set<String> getChildren(){
        return children;
    }

    /*UTILITY METHODS-------------------------------------------------------------------------------------------------*/
    public String getName(){
        return name;
    }

    //toString and getName are functionally identical, but implemented separately to preserve cohesion in naming
    public String toString(){
        return getName();
    }
}
