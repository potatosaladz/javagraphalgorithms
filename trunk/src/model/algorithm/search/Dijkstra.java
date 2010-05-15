package model.algorithm.search;

import model.graph.WeightedGraph;
import model.GraphADT;

import java.util.Vector;

public class Dijkstra {

    public Integer[] dijkstra(WeightedGraph graph, int source) {
        int n = graph.size() + 1;
        Vector<Integer> vertices = new Vector<Integer>(n);
        Integer dist[] = new Integer[n];
        Integer previous[] = new Integer[n];
        initialization(n, vertices, dist, previous);
        dist[source] = 0;
        while (!vertices.isEmpty()) {
            Integer closestVertex = getClosestVertex(vertices, dist);
            if (dist[closestVertex] == GraphADT.INFINITY) break;
            vertices.remove(closestVertex);
            Vector<Integer> neighbors = graph.neighbors(closestVertex);
            for (Integer neighbor : neighbors) {
                int alt = dist[closestVertex] + graph.getCost(closestVertex, neighbor);
                if (alt < dist[neighbor]) {
                    dist[neighbor] = alt;
                    previous[neighbor] = closestVertex;
                }
            }
         }
        return previous;
    }

    private void initialization(int n, Vector<Integer> vertices, Integer[] dist, Integer[] previous) {
        for (int i = 0; i < n; i++) {
            vertices.add(i);
            dist[i] = GraphADT.INFINITY;
            previous[i] = null;
        }
    }

    private Integer getClosestVertex(Vector<Integer> vertices, Integer[] distance) {
        Integer closestVertex = null;
        int smallestCost = GraphADT.INFINITY;
        for (Integer vertex : vertices) {
            if (distance[vertex] <= smallestCost) {
                smallestCost = distance[vertex];
                closestVertex = vertex;
            }
        }
        return closestVertex;
    }
}
