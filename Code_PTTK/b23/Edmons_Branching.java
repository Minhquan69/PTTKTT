/**
 * Write a description of class Edmons_Branching here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Edmons_Branching {
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private boolean[] onCycle;
    private int cost;
    private Iterable<DirectedEdge> cycle;

    public Edmons_Branching(EdgeWeightedDigraph_T G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onCycle = new boolean[G.V()]; // Khởi tạo mảng onCycle
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0; // Đặt khoảng cách từ s đến chính nó là 0

        for (int v = 0; v < G.V(); v++) {
            for (DirectedEdge e : G.adj(v)) {
                if (distTo[e.from()] + e.weight() < distTo[e.to()]) {
                    distTo[e.to()] = distTo[e.from()] + e.weight();
                    edgeTo[e.to()] = e;
                }
            }
        }

        cycle = findCycle();
        while (cycle != null) {
            extract(G, cycle);
            cycle = findCycle();
        }
    }

    private void extract(EdgeWeightedDigraph_T G, Iterable<DirectedEdge> supernode) {
        double minEdge = Double.POSITIVE_INFINITY;
        DirectedEdge ME = null;
        int node = -1;
        for (DirectedEdge e : supernode) {
            int w = e.to();
            onCycle[w] = true; // Đánh dấu đỉnh w nằm trên chu trình
            for (DirectedEdge c : G.adj(w)) {
                if (!onCycle[c.to()] && distTo[c.to()] > distTo[w] + c.weight()) {
                    distTo[c.to()] = distTo[w] + c.weight();
                    edgeTo[c.to()] = c;
                    if (distTo[c.to()] < minEdge) {
                        minEdge = distTo[c.to()];
                        ME = c;
                        node = c.to();
                    }
                }
            }
        }
        for (DirectedEdge e : supernode) {
            onCycle[e.to()] = false; // Đánh dấu đỉnh không nằm trên chu trình
        }
    }

    private Iterable<DirectedEdge> findCycle() {
        EdgeWeightedDigraph_T spt = new EdgeWeightedDigraph_T(edgeTo, distTo);
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
        cycle = finder.cycle();
        return cycle;
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public DirectedEdge edgeTo(int v) {
        return edgeTo[v];
    }
 
    public static void main(String[] args) {
        In in = new In(args[0]);
        int s = Integer.parseInt(args[1]);
        EdgeWeightedDigraph_T G = new EdgeWeightedDigraph_T(in);

        Edmons_Branching sp = new Edmons_Branching(G, s);
        StdOut.printf("nut goc: %d%n", s);
        for (int v = 0; v < G.V(); v++) {
            StdOut.printf("%d  (%.2f)  ", v, sp.distTo(v));
            if (sp.edgeTo(v) != null) {
                StdOut.println(sp.edgeTo(v));
            } else {
                StdOut.println();
            }
        }
    }
}