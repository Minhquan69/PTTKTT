
/**
 * Write a description of class Edmons_Branching here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Edmons_Branching
{
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private boolean[] onCycle;
    private int cost;
    private Iterable<DirectedEdge> cycle;
    
    public Edmons_Branching(EdgeWeightedDigraph_T G, int s){
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        for(int v=0; v<G.V();v++){
           distTo[v] = Double.POSITIVE_INFINITY;
           for(DirectedEdge e : G.adj(v)){
               if(distTo[v] > e.weight()){
                   distTo[v] = e.weight();
                   edgeTo[v] = e;
               }
           }
           for (v = 0; v < G.V(); v++) {
               if(edgeTo[v] != null)
                    edgeTo[v].used();
        } 
           distTo[s] = 0.0;
           edgeTo[s] = null;
           cycle = findCycle();
           while(!(cycle == null)){
                etract(G,cycle);
                cycle = findCycle();
            }
        }
    }
    private void etract(EdgeWeightedDigraph_T G, Iterable<DirectedEdge> supernode){
    double minEdge = Double.POSITIVE_INFINITY;
    DirectedEdge ME = null;
    int node = 0;
    while(!(supernode == null)){
        for(DirectedEdge e: supernode){
            int w = e.to();
            for(DirectedEdge c : G.adj(w)){
                if(!(c==e) && minEdge > c.weight()){
                    minEdge = c.weight();
                    ME = c;
                   node = w;
                }
            }
        }
    }
    distTo[node] = minEdge;
    edgeTo[node] = ME;
    supernode = findCycle();
    
}




    public Iterable<DirectedEdge> Cycle(){
        return cycle;
    }
      private Iterable<DirectedEdge> findCycle(){
          cycle = new Stack<DirectedEdge>();
          int V = edgeTo.length;
          EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
          for(int v =0;v<V; v++)
              if(edgeTo[v] != null)
                  spt.addEdge(edgeTo[v]);
          EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
          cycle = finder.cycle();
          return cycle;
      }
      public double distTo(int v){
          return distTo[v];
      }
      public DirectedEdge edgeTo(int v){
          return edgeTo[v];
      }
      
      public static void main(String[] args){
          StdOut.println("Do Xuan Trang");
          In in = new In(args[0]);
          int s = Integer.parseInt(args[1]);
          EdgeWeightedDigraph_T G = new EdgeWeightedDigraph_T(in);
          
          Edmons_Branching sp = new Edmons_Branching(G,s);
          StdOut.printf("nut goc: "+s);
          StdOut.println();
          for(int v=0;v<G.V();v++){
               StdOut.printf("%d  (%5.2f)", v, sp.distTo(v));
            StdOut.println(sp.edgeTo(v));
          }
      }
      
}
