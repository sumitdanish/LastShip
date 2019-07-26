package com.graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class DFSWIthDirectedGraph {
  public static void main(String[] args) throws FileNotFoundException {
    Graph1 graph1 = new Graph1();
    Scanner sc = new Scanner(new FileInputStream("g2.txt"));
    graph1.addEdge(sc);
    graph1.iterativeDFS(graph1.getGraph(), 4);
  }
}

class Graph1 {
  private Map<Integer, Node1> graph;

  public Graph1() {
    this.graph = new HashMap<>();
  }

  public void addEdge(Scanner sc) {
    while (sc.hasNext()) {
      String[] s = sc.nextLine().split(",");
      Integer src = Integer.valueOf(s[0]);
      Integer dest = Integer.valueOf(s[1]);
      addEdge(src, dest);
    }
  }

  private void addEdge(int src, int dest) {
    if (!graph.containsKey(src)) {
      Node1 n = createNode(null, dest);
      graph.put(src, n);
    } else {
      Node1 n = createNode(graph.get(src), dest);
      graph.put(src, n);
    }
  }

  private Node1 createNode(Node1 node, int data) {
    Node1 n = new Node1(data);
    if (node == null) {
      node = n;
      return node;
    }
    node.setNext(createNode(node.getNext(), data));
    return node;
  }

  public void dfs(Map<Integer, Node1> map, Integer src, Set<Integer> set) {
    set.add(src);
    System.out.print(src + " -> ");
    Node1 n = map.get(src);
    while (n != null) {
      if (!set.contains(n.getData())) {
        dfs(map, n.getData(), set);
      }
      n = n.getNext();
    }
  }

  private void iterativeDFS(Map<Integer, Node1> map, int src, Set<Integer> set) {
    Stack<Integer> stack = new Stack<>();
    stack.push(src);
    while (!stack.isEmpty()) {
      int s = stack.pop();
      if(!set.contains(s)){
        set.add(s);
        System.out.print(s + " -> ");
      }
      Node1 n = map.get(s);
      while (n != null) {
        if (!set.contains(n.getData())) {
          stack.push(n.getData());
        }
        n = n.getNext();
      }
    }
  }

  public Map<Integer, Node1> getGraph() {
    return graph;
  }

  public void iterativeDFS(Map<Integer, Node1> graph, int x) {
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < x; i++) {
      if (!set.contains(x)) {
        iterativeDFS(getGraph(), i, set);
      }
    }
  }
}

class Node1 {
  private Node1 next;
  private Integer data;

  public Node1(Integer data) {
    this.data = data;
  }

  public Node1 getNext() {
    return next;
  }

  public void setNext(Node1 next) {
    this.next = next;
  }

  public Integer getData() {
    return data;
  }

  public void setData(Integer data) {
    this.data = data;
  }
}
