public class PrimMST {
    private static final double FLOATING_POINT_EPSILON = 1.0E-12;

    private Edge[] edgeTo;        // edgeTo[v] = Canh ngan nhat tu Dinh thuoc cay den Dinh v ko thuoc cay 
    private double[] distTo;      // distTo[v] = Do dai cua canh ngan nhat do (edgeTo[v])
    private boolean[] marked;     // marked[v] = True neu v thuoc cay, false nguoc lai
    private IndexMinPQ<Double> pq;      // pq: Hang doi uu tien luu Dinh v va distTo(v)

    /**
     * Compute a minimum spanning tree (or forest) of an edge-weighted graph.
     * @param G the edge-weighted graph
     */
    public PrimMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];       // G.V(): So dinh cua do thi vo huong G 
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        pq = new IndexMinPQ<Double>(G.V());
        
        // Khoi gan cac distTo(v) = oo (vo cung)
        for (int v = 0; v < G.V(); v++)         // Duyet moi dinh cua do thi G
            distTo[v] = Double.POSITIVE_INFINITY;

        int s = 0;              // Coi dinh bat dau la dinh s = 0
        distTo[s] = 0.0;        // Khoi gan lai distTo(s) = 0        
        pq.insert(s, distTo[s]);       // Them vao pq
        
        while (!pq.isEmpty()) {
            int v = pq.delMin();        // Tra ve dinh co do dai canh ngan nhat tuong ung (distTo[v])
            for (Edge e : G.adj(v))     // G.adj(): Cac canh ke voi dinh v o do thi G
                relax(e, v);    // Ktra canh e co tot hon khong
        }

        // check optimality conditions : ki?m tra ?i?u ki?n t?i ?u
        assert check(G);
    }

    // relax edge e and update pq if changed : th? giãn c?nh e và c?p nh?t pq n?u thay ??i
    private void relax(Edge e, int v) {
        int w = e.other(v);     // Dinh con lai cua canh e: e(v, w) 
        marked[v] = true;       // Danh dau dinh v dang quet thuoc cay
        
        // Ktra w thuoc cay chua?
        if (marked[w]) 
            return;
            
        if (e.weight() < distTo[w]) {       // Neu e(v, w) < distTo(w)
            distTo[w] = e.weight();     
            edgeTo[w] = e;              
                
            // Ktra dinh w da co trong pq chua?
            if (pq.contains(w)) 
                pq.decreaseKey(w, distTo[w]);       // Roi thi giam do dai canh xuong
            else                
                pq.insert(w, distTo[w]);        // Chua thi them vao dinh va do dai canh tuong ung
        }
    }

    public Iterable<Edge> edges() {
        Queue<Edge> mst = new Queue<Edge>();
        for (int v = 0; v < edgeTo.length; v++) {
            Edge e = edgeTo[v];
            if (e != null) {
                mst.enqueue(e);
            }
        }
        return mst;
    }

    public double weight() {
        double weight = 0.0;
        for (Edge e : edges())
            weight += e.weight();
        return weight;
    }


    // check optimality conditions (takes time proportional to E V lg* V) :
    // ki?m tra các ?i?u ki?n t?i ?u (m?t th?i gian t? l? v?i E V lg* V)
    private boolean check(EdgeWeightedGraph G) {

        // check weight : Ki?m tra tr?ng s?
        double totalWeight = 0.0;
        for (Edge e : edges()) {
            totalWeight += e.weight();
        }
        if (Math.abs(totalWeight - weight()) > FLOATING_POINT_EPSILON) {
            System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", totalWeight, weight());
            return false;
        }

        // check that it is acyclic :
        // Ki?m tra xem nó có tu?n hoàn không
        UF uf = new UF(G.V());
        for (Edge e : edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.find(v) == uf.find(w)) {
                System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }

        // check that it is a spanning forest : 
        //ki?m tra xem ?ó có ph?i là m?t cây bao trùm không
        for (Edge e : G.edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.find(v) != uf.find(w)) {
                System.err.println("Not a spanning forest");
                return false;
            }
        }

        // check that it is a minimal spanning forest (cut optimality conditions)
        // ki?m tra xem ?ó có ph?i là cây bao trùm t?i thi?u không (c?t ?i?u ki?n t?i ?u)
        for (Edge e : edges()) {

            // all edges in MST except e : 
            // t?t c? các c?nh trong MST ngo?i tr? e
            uf = new UF(G.V());
            for (Edge f : edges()) {
                int x = f.either(), y = f.other(x);
                if (f != e) uf.union(x, y);
            }

            // check that e is min weight edge in crossing cut
            // ki?m tra xem e có ph?i là c?nh tr?ng l??ng t?i thi?u trong ???ng c?t ngang không
            for (Edge f : G.edges()) {
                int x = f.either(), y = f.other(x);
                if (uf.find(x) != uf.find(y)) {
                    if (f.weight() < e.weight()) {
                        System.err.println("Edge " + f + " violates cut optimality conditions");
                        return false;
                    }
                }
            }

        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("_ _ _");
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        PrimMST mst = new PrimMST(G);
        
        for (Edge e : G.edges()) {
            StdOut.println(e);
        }
        StdOut.println("\nMinimal Spanning Tree");
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
    
}