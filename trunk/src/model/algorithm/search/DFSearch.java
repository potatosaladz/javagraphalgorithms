package model.algorithm.search;

import model.GraphADT;

import java.util.Vector;

public class DFSearch {

    public static int DFS(GraphADT graph, int v, int[] pre, int[] post) {
        int n = graph.getNumVertices();
        for (int i = 0; i < n; i++) {
            pre[i] = post[i] = 0;
        }
        CountPair cnt = new CountPair();
        doDFS(graph, v, pre, post, cnt);
        return post[v];
    }

    private static void doDFS(GraphADT G, int v, int[] PreOrder, int[] PostOrder, CountPair cnt) {
        PreOrder[v] = cnt.inc1();
        Vector<Integer> nbrs = G.neighbors(v);
        for (int i = 0; i < nbrs.size(); i++) {
            int u = (Integer) nbrs.elementAt(i);
            if (PreOrder[u] == 0) {
                doDFS(G, u, PreOrder, PostOrder, cnt);
            }
        }
        PostOrder[v] = cnt.inc2();
    }

    private static class CountPair {
        int cnt1, cnt2;

        int inc1() {
            return ++cnt1;
        }

        int inc2() {
            return ++cnt2;
        }
    }
}
