package com.graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DFS {
  public static void main(String[] args) throws FileNotFoundException {
    InputStream src;
    Scanner scanner = new Scanner(new FileInputStream("g1.txt"));
    Graph graph = new Graph();
    graph.addEdge(scanner);
    graph.dfs(graph.getGraph(),0,new HashSet<>());
  }
}

class Graph {

  private Map<Integer, Node> graph;
  private Set<Integer> set;

  public Graph() {
    graph = new HashMap<>();
  }

  public void addEdge(Scanner scanner) {
    while (scanner.hasNext()) {
      String[] s = scanner.nextLine().split(",");
      String string;
      Integer src = Integer.valueOf(s[0]);
      Integer dest = Integer.valueOf(s[1]);
      addEdge(src, dest);
    }
  }

  private void addEdge(int src, int dest) {
    if (!graph.containsKey(src)) {
      Node n1 = createNode(dest, null);
      graph.put(src, n1);
    } else {
      Node n = graph.get(src);
      n = createNode(dest, n);
      graph.put(src, n);
    }
  }

  private Node createNode(int data, Node node) {
    Node n = new Node(data);
    if (node == null) {
      node = n;
      return node;
    }
    node.setNext(createNode(data, node.getNext()));
    return node;
  }

  public void dfs(Map<Integer,Node> map,int src,Set<Integer> set){
    set.add(src);
    System.out.println(src);
    Node n = map.get(src);
    while(n != null){
      if(!set.contains(n.getData())){
        //set.add(n.getData());
        dfs(map,n.getData(),set);
      }
      n = n.getNext();
    }
  }

  public void print(Node node) {
    if (node == null) {
      return;
    }
    System.out.print(node.getData() + " -> ");
    print(node.getNext());
  }

  public Map<Integer, Node> getGraph() {
    return graph;
  }
}

class Node {
  private Node next;
  private Integer data;

  public Node(Integer data) {
    this.data = data;
  }

  public Node getNext() {
    return next;
  }

  public void setNext(Node next) {
    this.next = next;
  }

  public Integer getData() {
    return data;
  }

  public void setData(Integer data) {
    this.data = data;
  }
}
