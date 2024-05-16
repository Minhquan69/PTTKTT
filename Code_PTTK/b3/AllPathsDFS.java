import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AllPathsDFS {
    private List<List<DirectedEdge>> allPaths;
    private Stack<DirectedEdge> path;
    private boolean[] marked;

    public AllPathsDFS(EdgeWeightedDigraph G, int s, int t) {
        allPaths = new ArrayList<>();
        path = new Stack<>();
        marked = new boolean[G.V()];
        dfs(G, s, t);
    }

    private void dfs(EdgeWeightedDigraph G, int v, int t) {
        if (v == t) {
            allPaths.add(new ArrayList<>(path));
            return;
        }

        marked[v] = true;
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (!marked[w]) {
                path.push(e);
                dfs(G, w, t);
                path.pop();
            }
        }
        marked[v] = false;
    }

    public Iterable<List<DirectedEdge>> paths() {
        return allPaths;
    }

    public static void main(String[] args) {
        // read edge-weighted digraph
        In in = new In(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);

        int s = Integer.parseInt(args[1]);
        int t = Integer.parseInt(args[2]);

        AllPathsDFS allPathsDFS = new AllPathsDFS(G, s, t);

        // print all paths
        for (List<DirectedEdge> path : allPathsDFS.paths()) {
            for (DirectedEdge e : path) {
                StdOut.print(e + " ");
            }
            StdOut.println();
        }
    }
}
