package com.graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class BFS {
  public static void main(String[] args) throws FileNotFoundException {
    InputStream src;
    Scanner scanner = new Scanner(new FileInputStream("bfs.txt"));
    Graph2 graph2 = new Graph2();
    graph2.addEdge(scanner);
    graph2.bfs(graph2.getGraph());
  }
}

class Graph2 {
  private Map<Integer, Node2> graph;

  public Graph2() {
    this.graph = new HashMap<>();
  }

  public void addEdge(Scanner scanner) {
    while (scanner.hasNext()) {
      String[] s = scanner.nextLine().split(",");
      Integer src = Integer.valueOf(s[0]);
      Integer dest = Integer.parseInt(s[1]);
      addEdge(src, dest);
    }
  }

  private void addEdge(Integer src, Integer dest) {
    if (!graph.containsKey(src)) {
      graph.put(src, createNode(dest, null));
    } else {
      graph.put(src, createNode(dest, graph.get(src)));
    }
  }

  private Node2 createNode(Integer data, Node2 node) {
    Node2 n = new Node2(data);
    if (node == null) {
      node = n;
      return node;
    }
    node.setNext(createNode(data, node.getNext()));
    return node;
  }

  public void bfs(Map<Integer, Node2> graph, int src,Set<Integer> set) {
    Queue<Integer> queue = new LinkedList<>();
    queue.add(src);
    while (!queue.isEmpty()) {
      Integer s = queue.poll();
      if (!set.contains(s)) {
        set.add(s);
        System.out.println(s);
      }
      Node2 n = graph.get(s);
      while (n != null) {
        if (!set.contains(n.getData())) {
          queue.add(n.getData());
        }
        n = n.getNext();
      }
    }
  }

  public void bfs(Map<Integer, Node2> map) {
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < 4; i++) {
      if (!set.contains(i)) {
        bfs(map, i,set);
      }
    }
  }

  public Map<Integer, Node2> getGraph() {
    return graph;
  }
}

class Node2 {
  private Node2 next;
  private Integer data;

  public Node2(Integer data) {
    this.data = data;
  }

  public Node2 getNext() {
    return next;
  }

  public void setNext(Node2 next) {
    this.next = next;
  }

  public Integer getData() {
    return data;
  }

  public void setData(Integer data) {
    this.data = data;
  }
}
