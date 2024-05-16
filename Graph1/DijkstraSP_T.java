/******************************************************************************
 *  Compilation:  javac DijkstraSP_T.java
 *  Execution:    java DijkstraSP_T input.txt s
 *  Dependencies: EdgeWeightedDigraph.java IndexMinPQ.java Stack.java DirectedEdge.java
 *  Data files:   https://algs4.cs.princeton.edu/44sp/tinyEWD.txt
 *                https://algs4.cs.princeton.edu/44sp/mediumEWD.txt
 *                https://algs4.cs.princeton.edu/44sp/largeEWD.txt
 *
 *  Dijkstra's algorithm. Computes the shortest path tree.
 *  Assumes all weights are nonnegative.
 *
 *  % java DijkstraSP_T tinyEWD.txt 0
 *  0 to 0 (0.00)  
 *  0 to 1 (1.05)  0->4  0.38   4->5  0.35   5->1  0.32   
 *  0 to 2 (0.26)  0->2  0.26   
 *  0 to 3 (0.99)  0->2  0.26   2->7  0.34   7->3  0.39   
 *  0 to 4 (0.38)  0->4  0.38   
 *  0 to 5 (0.73)  0->4  0.38   4->5  0.35   
 *  0 to 6 (1.51)  0->2  0.26   2->7  0.34   7->3  0.39   3->6  0.52   
 *  0 to 7 (0.60)  0->2  0.26   2->7  0.34   
 *
 *  % java DijkstraSP_T mediumEWD.txt 0
 *  0 to 0 (0.00)  
 *  0 to 1 (0.71)  0->44  0.06   44->93  0.07   ...  107->1  0.07   
 *  0 to 2 (0.65)  0->44  0.06   44->231  0.10  ...  42->2  0.11   
 *  0 to 3 (0.46)  0->97  0.08   97->248  0.09  ...  45->3  0.12   
 *  0 to 4 (0.42)  0->44  0.06   44->93  0.07   ...  77->4  0.11   
 *  ...
 *
 ******************************************************************************/

 


/**
 *  The {@code DijkstraSP_T} class represents a data type for solving the
 *  single-source shortest paths problem in edge-weighted digraphs
 *  where the edge weights are nonnegative.
 *  <p>
 *  This implementation uses Dijkstra's algorithm with a binary heap.
 *  The constructor takes time proportional to <em>E</em> log <em>V</em>,
 *  where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
 *  Afterwards, the {@code distTo()} and {@code hasPathTo()} methods take
 *  constant time and the {@code pathTo()} method takes time proportional to the
 *  number of edges in the shortest path returned.
 *  <p>
 *  For additional documentation,    
 *  see <a href="https://algs4.cs.princeton.edu/44sp">Section 4.4</a> of    
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne. 
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class DijkstraSP_T {
    private double[] distOut;          // distTo[v] = distance  of shortest s->v path
    // thay disTo -> distOut
    private DirectedEdge[] edgeOut;    // edgeTo[v] = last edge on shortest s->v path
    // thay edgeTo -> edgeOut
    private IndexMinPQ<Double> pq;    // priority queue of vertices

    /**
     * Computes a shortest-paths tree from the source vertex {@code s} to every other
     * vertex in the edge-weighted digraph {@code G}.
     *
     * @param  G the edge-weighted digraph
     * @param  s the source vertex
     * @throws IllegalArgumentException if an edge weight is negative
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public DijkstraSP_T(EdgeWeightedDigraph_T G, int s) {
        
        // tr?ng s? âm -> c?nh báo
        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");
        }

        distOut = new double[G.V()]; // truy??n kích th??c b?ng s? ??nh c?a V (t? 0->G.V())
        edgeOut = new DirectedEdge[G.V()];

        validateVertex(s); // m?nh ??? ki?m tra tính ?úng ??n c?a ký hi?u thì b?? qua

        for (int v = 0; v < G.V(); v++)
            distOut[v] = Double.POSITIVE_INFINITY; 
        distOut[s] = 0.0; // disOut[t];

        // relax vertices in order of distance from s
        pq = new IndexMinPQ<Double>(G.V());
        pq.insert(s, distOut[s]); // ch? s? và giá tr?

    // Bo sung vong while chon phan tu min trong PQ .......
        while(!pq.isEmpty()){
            int v = pq.delMin();
            for(DirectedEdge e : G.adj(v)) // l?y m??i c?nh ?i ra t? v
                relax(e);                  // ki?m tra c?nh e có t?t h?n không
        }

        // check optimality conditions
        assert check(G, s);
    }

    // relax edge e and update pq if changed
    private void relax(DirectedEdge e) {
        int v = e.to(), w = e.from(); // v là ??nh g?c, w là ??nh ng?n
        // v = e.to(), w = e.from()
        if (distOut[w] > distOut[v] + e.weight()) { 
            distOut[w] = distOut[v] + e.weight(); 
            edgeOut[w] = e; // l?u c?nh ?i vào v(w) là e
            if (pq.contains(w)) pq.decreaseKey(w, distOut[w]); // pq có nút ch?a, có r?i thì gi?m kho?ng cách xu?ng disTo[w]
            else                pq.insert(w, distOut[w]);      // ch?a có thì insert nó vào
        }
    }

    /**
     * Returns the length of a shortest path from the source vertex {@code s} to vertex {@code v}.
     * @param  v the destination vertex
     * @return the length of a shortest path from the source vertex {@code s} to vertex {@code v};
     *         {@code Double.POSITIVE_INFINITY} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    
    public double distOut(int v) { // tr? v? kho?ng cách
        validateVertex(v);
        return distOut[v];
    }

    /**
     * Returns true if there is a path from the source vertex {@code s} to vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return {@code true} if there is a path from the source vertex
     *         {@code s} to vertex {@code v}; {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathOut(int v) { // h?i có con ???ng t? s ??n v không
        validateVertex(v);
        return distOut[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * Returns a shortest path from the source vertex {@code s} to vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return a shortest path from the source vertex {@code s} to vertex {@code v}
     *         as an iterable of edges, and {@code null} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<DirectedEdge> pathOut(int v) {  // l?y con ???ng, tr? v? m?t b? duy?t các c?nh
        validateVertex(v);
        if (!hasPathOut(v)) return null; // PathOut
        Queue<DirectedEdge> path = new Queue<DirectedEdge>(); // t?o ng?n x?p ?? ??y các con ???ng
        // Stack -> Queue
        for (DirectedEdge e = edgeOut[v]; e != null; e = edgeOut[e.to()]) { 
            path.enqueue(e);
        }
        return path;
    }


    // check optimality conditions:
    // (i) for all edges e:            distTo[e.to()] <= distTo[e.from()] + e.weight()
    // (ii) for all edge e on the SPT: distTo[e.to()] == distTo[e.from()] + e.weight()
    private boolean check(EdgeWeightedDigraph_T G, int s) {

        // check that edge weights are nonnegative
        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0) {
                System.err.println("negative edge weight detected");
                return false;
            }
        }

        // check that distTo[v] and edgeTo[v] are consistent
        if (distOut[s] != 0.0 || edgeOut[s] != null) {
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
            return false;
        }
        for (int v = 0; v < G.V(); v++) {
            if (v == s) continue;
            if (edgeOut[v] == null && distOut[v] != Double.POSITIVE_INFINITY) {
                System.err.println("distOut[] and edgeOut[] inconsistent");
                return false;
            }
        }

        // check that all edges e = v->w satisfy distTo[w] <= distTo[v] + e.weight()
        for (int v = 0; v < G.V(); v++) {
            for (DirectedEdge e : G.adj(v)) {
                int w = e.to();
                if (distOut[v] + e.weight() < distOut[w]) {
                    System.err.println("edge " + e + " not relaxed");
                    return false;
                }
            }
        }

        // check that all edges e = v->w on SPT satisfy distTo[w] == distTo[v] + e.weight()
        for (int w = 0; w < G.V(); w++) {
            if (edgeOut[w] == null) continue;
            DirectedEdge e = edgeOut[w];
            int v = e.to();
            if (w != e.from()) return false;
            if (distOut[v] + e.weight() != distOut[w]) {
                System.err.println("edge " + e + " on shortest path not tight");
                return false;
            }
        }
        return true;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = distOut.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Unit tests the {@code DijkstraSP_T} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Do Xuan Trang");
        In in = new In(args[0]); // truy??n file (tinyEWD.txt, tham s? 0)
        EdgeWeightedDigraph_T G = new EdgeWeightedDigraph_T(in);    // kh?i t?o ??i t??ng ?? th? có h??ng
        int t = Integer.parseInt(args[1]); // truy??n vào ??nh g?c

        // compute shortest paths
        DijkstraSP_T sp = new DijkstraSP_T(G, t); // t?o ??i t??ng Dijkstra


        // print shortest path
        for (int s = 0; s < G.V(); s++) { // G.V(): s? ??nh
            if (sp.hasPathOut(s)) { // có con ????ng ??n t không
                StdOut.printf("%d to %d (%.2f)  ", s, t, sp.distOut(s)); // in ra ??nh ??u, ??nh cu?i, g??i ?? dài
                for (DirectedEdge e : sp.pathOut(s)) {
                    StdOut.print(e + "   ");
                }
                StdOut.println(); // in ra con ????ng ??y
            }
            else {
                StdOut.printf("%d to %d         no path\n", t, s);
            }
        }
    }

}

/******************************************************************************
 *  Copyright 2002-2016, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
