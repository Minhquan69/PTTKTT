
/**
 * Write a description of class BellmanFord_T here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BellmanFord_T
{
    //distFrom[v] là khoảng cách ngắn nhất từ đỉnh nguồn đến đỉnh v.
    private double[] distFrom;               // distFrom[v] = distance  of shortest v->s path
    private DirectedEdge[] edgeFrom;      //cạnh cuối cùng trên đường đi ngắn nhất từ đỉnh nguồn đến đỉnh v.
    private boolean[] onQueue;             // onQueue[v] = is v currently on the queue?
    private Queue<Integer> queue;          // queue of vertices to relax
    private int cost;                      // number of calls to relax(), có chu trình âm hay không
    private Iterable<DirectedEdge> cycle;  // Đường đi của chu trình âm

    /**
     * Computes a shortest paths tree from every other vertex to {code @s} in
     * the edge-weighted digraph_T {@code G}.
     * @param G the acyclic digraph
     * @param s the dist vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    //tính toán đường đi ngắn nhất từ mỗi đỉnh đến đỉnh nguồn s
    public BellmanFord_T(EdgeWeightedDigraph_T G, int s) {
        distFrom  = new double[G.V()];
        edgeFrom  = new DirectedEdge[G.V()];
        onQueue = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            distFrom[v] = Double.POSITIVE_INFINITY;
        distFrom[s] = 0.0;

        // Bellman-Ford algorithm
        queue = new Queue<Integer>();
        queue.enqueue(s);
        onQueue[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.dequeue();
            onQueue[v] = false;
            relax(G, v);
        }

        assert check(G, s);
    }

    // Thả lỏng đỉnh v và đặt các đỉnh kết thúc khác vào hàng đợi nếu có thay đổi.
    private void relax(EdgeWeightedDigraph_T G, int v) {
        for (DirectedEdge e : G.inward(v)) {
            int w = e.from();
            if (distFrom[w] > distFrom[v] + e.weight()) {
                distFrom[w] = distFrom[v] + e.weight();
                edgeFrom[w] = e;
                if (!onQueue[w]) {
                    queue.enqueue(w);
                    onQueue[w] = true;
                }
            }
            if (cost++ % G.V() == 0) {
                findNegativeCycle();
                if (hasNegativeCycle()) return;  // found a negative cycle
            }
        }
    }

    /**
     * Is there a negative cycle reachable from the source vertex {@code s}?
     * @return {@code true} if there is a negative cycle reachable from the
     *    source vertex {@code s}, and {@code false} otherwise
     */
    //Kiểm tra xem có chu trình âm
    public boolean hasNegativeCycle() {
        return cycle != null;
    }

    /**
     * Returns a negative cycle reachable from the source vertex {@code s}, or {@code null}
     * if there is no such cycle.
     * @return a negative cycle reachable from the soruce vertex {@code s} 
     *    as an iterable of edges, and {@code null} if there is no such cycle
     */
    public Iterable<DirectedEdge> negativeCycle() {
        return cycle;
    }

    // Tìm chu trình âm trong đồ thị bằng cách tạo một đồ thị con từ các cạnh edgeFrom 
    //và sử dụng một thuật toán phát hiện chu trình.
    private void findNegativeCycle() {
        int V = edgeFrom.length;
        EdgeWeightedDigraph_T spt = new EdgeWeightedDigraph_T(V);
        for (int v = 0; v < V; v++)
            if (edgeFrom[v] != null)
                spt.addEdge(edgeFrom[v]);

        EdgeWeightedDigraphCycle_T finder = new EdgeWeightedDigraphCycle_T(spt);
        cycle = finder.cycle();
    }

    /**
     * Returns the length of a shortest path from the source vertex {@code s} to vertex {@code v}.
     * @param  v the destination vertex
     * @return the length of a shortest path from the source vertex {@code s} to vertex {@code v};
     *         {@code Double.POSITIVE_INFINITY} if no such path
     * @throws UnsupportedOperationException if there is a negative cost cycle reachable
     *         from the source vertex {@code s}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public double distFrom(int v) {
        validateVertex(v);
        if (hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        return distFrom[v];
    }

    /**
     * Is there a path from the source {@code s} to vertex {@code v}?
     * @param  v the source vertex
     * @return {@code true} if there is a path from the source vertex
     *         {@code v} to vertex {@code s}, and {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathFrom(int v) {
        validateVertex(v);
        return distFrom[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * Returns a shortest path from the source {@code s} to vertex {@code v}.
     * @param  v the destination vertex
     * @return a shortest path from the source {@code s} to vertex {@code v}
     *         as an iterable of edges, and {@code null} if no such path
     * @throws UnsupportedOperationException if there is a negative cost cycle reachable
     *         from the source vertex {@code s}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<DirectedEdge> pathTo(int v) {
        validateVertex(v);
        if (hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        if (!hasPathFrom(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeFrom[v]; e != null; e = edgeFrom[e.to()]) {
            path.push(e);
        }
        return path;
    }

    // check optimality conditions: either 
    // (i) there exists a negative cycle reacheable from s
    //     or 
    // (ii)  for all edges e = v->w:            distTo[w] <= distTo[v] + e.weight()
    // (ii') for all edges e = v->w on the SPT: distTo[w] == distTo[v] + e.weight()
    //Kiểm tra các điều kiện tối ưu: có chu trình âm từ đỉnh nguồn 
    //hoặc mọi cạnh đều thoả mãn điều kiện đường đi ngắn nhất.
    private boolean check(EdgeWeightedDigraph_T G, int s) {

        // has a negative cycle
        if (hasNegativeCycle()) {
            double weight = 0.0;
            for (DirectedEdge e : negativeCycle()) {
                weight += e.weight();
            }
            if (weight >= 0.0) {
                System.err.println("error: weight of negative cycle = " + weight);
                return false;
            }
        }

        // no negative cycle reachable from source
        else {

            // check that distTo[v] and edgeTo[v] are consistent
            if (distFrom[s] != 0.0 || edgeFrom[s] != null) {
                System.err.println("distanceTo[s] and edgeTo[s] inconsistent");
                return false;
            }
            for (int v = 0; v < G.V(); v++) {
                if (v == s) continue;
                if (edgeFrom[v] == null && distFrom[v] != Double.POSITIVE_INFINITY) {
                    System.err.println("distTo[] and edgeTo[] inconsistent");
                    return false;
                }
            }

            // check that all edges e = v->w satisfy distTo[w] <= distTo[v] + e.weight()
            for (int v = 0; v < G.V(); v++) {
                for (DirectedEdge e : G.inward(v)) {
                    int w = e.from();
                    if (distFrom[v] + e.weight() < distFrom[w]) {
                        System.err.println("edge " + e + " not relaxed");
                        return false;
                    }
                }
            }

            // check that all edges e = v->w on SPT satisfy distTo[w] == distTo[v] + e.weight()
            for (int w = 0; w < G.V(); w++) {
                if (edgeFrom[w] == null) continue;
                DirectedEdge e = edgeFrom[w];
                int v = e.to();
                if (w != e.from()) return false;
                if (distFrom[v] + e.weight() != distFrom[w]) {
                    System.err.println("edge " + e + " on shortest path not tight");
                    return false;
                }
            }
        }

        StdOut.println("Satisfies optimality conditions");
        StdOut.println();
        return true;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = distFrom.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Unit tests the {@code BellmanFordSP} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        int s = Integer.parseInt(args[1]);
        EdgeWeightedDigraph_T G = new EdgeWeightedDigraph_T(in);

        BellmanFord_T sp = new BellmanFord_T(G, s);

        // print negative cycle
        if (sp.hasNegativeCycle()) {
            for (DirectedEdge e : sp.negativeCycle())
                StdOut.println(e);
        }

        // print shortest paths
        else {
            for (int v = 0; v < G.V(); v++) {
                if (sp.hasPathFrom(v)) {
                    StdOut.printf("%d to %d (%5.2f)  ", v, s, sp.distFrom(v));
                    for (DirectedEdge e : sp.pathTo(v)) {
                        StdOut.print("   " + e);
                    }
                    StdOut.println();
                }
                else {
                    StdOut.printf("%d to %d           no path\n", v, s);
                }
            }
        }

    }
}