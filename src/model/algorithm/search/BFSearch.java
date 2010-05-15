package model.algorithm.search;

import model.GraphADT;
import model.graph.Graph;
import model.graph.WeightedGraph;
import model.algorithm.search.util.Queue;

import java.util.Vector;

public class BFSearch {

    public static int BFS(GraphADT graph, int v, int[] levelOrder) {
        Queue<Integer> toGrow = new Queue<Integer>();
        int visited = 0;
        toGrow.enqueue(v);
        levelOrder[v] = ++visited;
        while (!toGrow.isEmpty()) {
            int vertex = toGrow.dequeue();
            Vector<Integer> nbrs = graph.neighbors(vertex);
            for (int i = 0; i < nbrs.size(); i++) {
                int neighborVertex = nbrs.elementAt(i);
                if (levelOrder[neighborVertex] == 0) {
                    levelOrder[neighborVertex] = ++visited;
                    toGrow.enqueue(neighborVertex);
                }
            }
        }
        return visited;
    }

    public static boolean isConnected(GraphADT graph) {
        int n = graph.getNumVertices();
        GraphADT associatedGraph;
        if (graph.isDirected()) {
            if (graph.isWeighted()) {
                associatedGraph = new WeightedGraph(graph);
            } else {
                associatedGraph = new Graph(graph);
            }
        } else {
            associatedGraph = graph;
        }
        return BFS(associatedGraph, 0, new int[n]) == (graph.getNumVertices());
    }

    public static int getIsolatedVertices(GraphADT graph) {
        int n = graph.getNumVertices();
        GraphADT associatedGraph;
        if (graph.isDirected()) {
            if (graph.isWeighted()) {
                associatedGraph = new WeightedGraph(graph);
            } else {
                associatedGraph = new Graph(graph);
            }
        } else {
            associatedGraph = graph;
        }
        int[] visited = new int[n];
        int cnt = 0;
        int nextComponent = 0;
        while (nextComponent != -1) {
            if (BFS(associatedGraph, nextComponent, visited) == 1) {
                cnt++;
            }
            nextComponent = getNextComponent(visited);
        }
        return cnt;
    }

    public static int countConnectedComponents(GraphADT graph) {
        int n = graph.getNumVertices();
        if (n == 1) {
            return 1;
        }
        GraphADT associatedGraph;
        if (graph.isDirected()) {
            if (graph.isWeighted()) {
                associatedGraph = new WeightedGraph(graph);
            } else {
                associatedGraph = new Graph(graph);
            }
        } else {
            associatedGraph = graph;
        }
        int[] visited = new int[n];
        int cnt = 0;
        int nextComponent = 0;
        while (nextComponent != -1) {
            cnt++;
            BFS(associatedGraph, nextComponent, visited);
            nextComponent = getNextComponent(visited);
        }
        return cnt;
    }

    private static int getNextComponent(int[] visited) {
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static boolean existsPath(GraphADT graph, int source, int target) {
        int[] visited = new int[graph.getNumVertices()];
        BFS(graph, source, visited);
        return visited[target] != 0;
    }
}
