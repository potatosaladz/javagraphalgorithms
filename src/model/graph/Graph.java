package model.graph;

import java.util.Vector;

import model.GraphADT;

public class Graph extends GraphADT {

    public Graph(boolean directed) {
        numVertices = 0;
        this.directed = directed;
    }

    public Graph(GraphADT graph) {
        this(false);
        int n = graph.getNumVertices();
        if (n > 0) {
            addVertices(n);
        }
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == j) continue;
                if (graph.getAdj()[i][j] || graph.getAdj()[j][i])
                    addEdge(i, j);
            }
        }
    }

    public void addVertices(int n) {
        boolean matrix[][] = adj_allocate(numVertices + n);

        for (int i = 0; i < numVertices; i++) {
            System.arraycopy(adj[i], 0, matrix[i], 0, numVertices);
        }

        adj = matrix;
        numVertices += n;
    }

    public void addArc(int i, int j) {
        adj[i][j] = true;
    }

    public boolean isArc(int i, int j) {
        return adj[i][j];
    }


    public Vector<Integer> neighbors(int i) {
        Vector<Integer> nbrs = new Vector<Integer>();
        for (int j = 0; j < numVertices; j++) {
            if (i == j) continue;
            if (adj[i][j]) {
                nbrs.addElement(j);
            }
        }
        return nbrs;
    }

    public int size() {
        int sz = 0;
        for (int i = 0; i < numVertices; i++)
            for (int j = 0; j < numVertices; j++) {
                if (adj[i][j]) {
                    sz++;
                }
                if (adj[i][j] != adj[j][i]) {
                    directed = true;
                }
            }
        return !directed ? sz / 2 : sz;
    }
}

