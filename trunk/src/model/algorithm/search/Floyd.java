package model.algorithm.search;

import model.graph.WeightedGraph;

public class Floyd {

    public int[][] floyd(WeightedGraph graph) {
        int n = graph.getNumVertices();
        int[][] path = initPathMatrix(graph, n);
        for (int v = 0; v < n; v++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    path[i][j] = Math.min(path[i][j], path[i][v] + path[v][j]);
                }
            }
        }
        return path;
    }

    private int[][] initPathMatrix(WeightedGraph graph, int n) {
        int path[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                path[i][j] = graph.getCost(i, j);
            }
        }
        return path;
    }
}
