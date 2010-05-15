package model.graph;

import java.util.Vector;

import model.GraphADT;

public class WeightedGraph extends GraphADT {

    private int costs[][];

    public WeightedGraph(boolean directed) {
        numVertices = 0;
        weighted = true;
        this.directed = directed;
    }

    public WeightedGraph(GraphADT graph) {
        this(false);
        int n = graph.getNumVertices();
        if (n > 0) {
            addVertices(n);
        }
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == j) continue;
                if (graph.getAdj()[i][j] || graph.getAdj()[j][i])
                    addEdge(i, j, getCost(i, j));
            }
        }
    }

    private static int costs_allocate(int n)[][] {
        int[][] costs = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    costs[i][j] = GraphADT.INFINITY;
                }
            }
        }
        return costs;
    }

    public void addVertices(int n) {
        boolean matrix[][] = adj_allocate(numVertices + n);
        for (int i = 0; i < numVertices; i++) {
            System.arraycopy(adj[i], 0, matrix[i], 0, numVertices);
        }
        adj = matrix;

        int costsMatrix[][] = costs_allocate(numVertices + n);

        for (int i = 0; i < numVertices; i++) {
            System.arraycopy(costs[i], 0, costsMatrix[i], 0, numVertices);
        }
        costs = costsMatrix;

        numVertices += n;
    }

    public void addArc(int i, int j, int cost) {
        costs[i][j] = cost;
        addArc(i, j);
    }

    public void addArc(int i, int j) {
        adj[i][j] = true;
    }

    public void addEdge(int i, int j, int cost) {
        costs[i][j] = cost;
        costs[j][i] = cost;

        addEdge(i, j);
    }

    public boolean isArc(int i, int j) {
        return adj[i][j];
    }

    public Vector<Integer> neighbors(int i) {
        Vector<Integer> nbrs = new Vector<Integer>();
        for (int j = 0; j < numVertices; j++) {
            if (j == i) continue;
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

    public void setCost(int i, int j, int val) {
        costs[i][j] = val;
    }

    public int getCost(int i, int j) {
        return costs[i][j];
    }
}
