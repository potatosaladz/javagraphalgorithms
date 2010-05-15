package model;

import java.util.Vector;

public abstract class GraphADT {

    public static final int INFINITY = 99999999;

    protected boolean directed;
    protected boolean weighted;
    protected int numVertices;

    protected boolean adj[][];

    protected GraphADT() {
    }

    public abstract void addVertices(int i);

    public abstract void addArc(int i, int j);

    public void removeArc(int i, int j) {
        adj[i][j] = false;
    }

    public void addEdge(int i, int j) {
        addArc(i, j);
        addArc(j, i);
    }

    public abstract boolean isArc(int i, int j);

    public boolean isEdge(int i, int j) {
        return isArc(i, j) && isArc(j, i);
    }

    public abstract Vector<Integer> neighbors(int i);

    public int getNumVertices() {
        return numVertices;
    }

    public abstract int size();


    public boolean isDirected() {
        return directed;
    }

    protected static boolean adj_allocate(int n)[][] {
        return new boolean[n][n];
    }

    public boolean isWeighted() {
        return weighted;
    }

    public boolean[][] getAdj() {
        return adj;
    }
}
