package model.algorithm;

import java.util.Vector;

import model.GraphADT;
import model.graph.WeightedGraph;

public class Prim {

	public WeightedGraph prim(WeightedGraph graph, int s) {
		int n = graph.getNumVertices();
		boolean directed = graph.isDirected();
		WeightedGraph newGraph = new WeightedGraph(directed);
		newGraph.addVertices(n);
		Vector<Integer> visitedVertices = new Vector<Integer>(n);
		visitedVertices.add(s);
		int source = s;
		while (visitedVertices.size() < n) {
			Integer target = visitedVertices.get(0);
			int minCost = GraphADT.INFINITY;
			for (Integer i : visitedVertices) {
				Vector<Integer> neighbors = graph.neighbors(i);
				neighbors.removeAll(visitedVertices);
				if (neighbors.isEmpty())
					continue;
				for (Integer neighbor : neighbors) {
					int cost = graph.getCost(i, neighbor);
					if (cost <= minCost) {
						minCost = cost;
						target = neighbor;
						source = i;
					}
				}
			}
			if (directed) {
				newGraph.addArc(source, target, graph.getCost(source, target));
			} else {
				newGraph.addEdge(source, target, graph.getCost(source, target));
			}
			visitedVertices.add(target);
		}
		return newGraph;
	}
}
