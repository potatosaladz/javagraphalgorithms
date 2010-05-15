package model.algorithm;

import model.graph.Graph;

public class Warshall {

    public void warshall(Graph g) {
        int n = g.getNumVertices();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (g.isDirected()) {
                        if (g.isArc(i, j) && g.isArc(j, k)) {
                            g.addArc(i, k);
                        }
                    } else {
                        if (g.isEdge(i, j) && g.isEdge(j, k)) {
                            g.addEdge(i, k);
                        }
                    }
                }
            }
        }
    }
}
