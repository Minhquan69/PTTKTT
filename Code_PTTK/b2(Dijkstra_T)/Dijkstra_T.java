public class Dijkstra_T
{
    private double[] distTo;          // distTo[v] = distance  of shortest s->v path
    private DirectedEdge[] edgeTo;    // edgeTo[v] = last edge on shortest s->v path
    private IndexMinPQ<Double> pq;    // priority queue of vertices

    public Dijkstra_T(EdgeWeightedDigraph_T G, int s) {
        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");
        }

        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        validateVertex(s);

        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        // relax vertices in order of distance from s
        pq = new IndexMinPQ<Double>(G.V());
        pq.insert(s, distTo[s]);

        // Bo sung vong while chon phan tu min trong PQ .......
        while(!pq.isEmpty()){
            int v = pq.delMin();
            for(DirectedEdge e : G.inward(v)){
                relax(e);
            }
        }
        // check optimality conditions
        assert check(G, s);
    }

    // relax edge e and update pq if changed
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[v] > distTo[w] + e.weight()) {
            distTo[v] = distTo[w] + e.weight();
            edgeTo[v] = e;
            if (pq.contains(v)) pq.decreaseKey(v, distTo[v]);
            else                pq.insert(v, distTo[v]);
        }
    }

    public double distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.to()]) {
            path.push(e);
        }
        return path;
    }


    // check optimality conditions:
    // (i) for all edges e:            distTo[e.from()] <= distTo[e.to()] + e.weight()
    // (ii) for all edge e on the SPT: distTo[e.from()] == distTo[e.to()] + e.weight()
    private boolean check(EdgeWeightedDigraph_T G, int s) {

        // check that edge weights are nonnegative
        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0) {
                System.err.println("negative edge weight detected");
                return false;
            }
        }

        // check that distTo[v] and edgeTo[v] are consistent
        if (distTo[s] != 0.0 || edgeTo[s] != null) {
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
            return false;
        }
        for (int v = 0; v < G.V(); v++) {
            if (v == s) continue;
            if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
                System.err.println("distTo[] and edgeTo[] inconsistent");
                return false;
            }
        }

        // check that all edges e = w->v satisfy distTo[v] <= distTo[w] + e.weight()
        for (int v = 0; v < G.V(); v++) {
            for (DirectedEdge e : G.inward(v)) {
                int w = e.from();
                if (distTo[v] + e.weight() < distTo[w]) {
                    System.err.println("edge " + e + " not relaxed");
                    return false;
                }
            }
        }

        // check that all edges e = w->v on SPT satisfy distTo[v] == distTo[w] + e.weight()
        for (int w = 0; w < G.V(); w++) {
            if (edgeTo[w] == null) continue;
            DirectedEdge e = edgeTo[w];
            int v = e.to();
            if (w != e.from()) return false;
            if (distTo[v] + e.weight() != distTo[w]) {
                System.err.println("edge " + e + " on shortest path not tight");
                return false;
            }
        }
        return true;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public static void main(String[] args) {
        In in = new In("tinyEWD.txt");
        EdgeWeightedDigraph_T G = new EdgeWeightedDigraph_T(in);
        int s = Integer.parseInt("0");

        // compute shortest paths
        Dijkstra_T sp = new Dijkstra_T(G, s);


        // print shortest path
        for (int t = 0; t < G.V(); t++) {
            if (sp.hasPathTo(t)) {
                Stack<DirectedEdge> st = new Stack();
                StdOut.printf("%d to %d (%.2f)  ", t, s, sp.distTo(t));
                for (DirectedEdge e : sp.pathTo(t)) {
                    st.push(e);
                    //StdOut.print(e + "   ");
                }
                while(!st.isEmpty()){
                    DirectedEdge e = st.pop();
                    StdOut.print(e + "   ");
                }
                StdOut.println();
            }
            else {
                StdOut.printf("%d to %d         no path\n", t, s);
            }
        }
    }
}