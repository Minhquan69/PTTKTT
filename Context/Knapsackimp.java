import java.util.ArrayList;
import java.util.List;


public class Knapsackimp
{
   private List<Item> items;
   private int maxWeight;
   private int bagWeight = 0;
   private int tongValue = 0;
   private Bag<Item> bag;
   
   public Knapsackimp(List<Item> items,int maxWeght ) {
       this.items = items;
       this.maxWeight = maxWeght;
       this.bag = new Bag<Item>();
       
       int N = items.size();
       int W = maxWeght;
       
       int[][] opt = new int[N+1][W+1]; // luu tru cac gia tri toi uu
       boolean[][] sol = new boolean[N+1][W+1]; // luu tru cac lua chon toi uu
       
       for(int w=0;w<=W;w++) {
           opt[0][w]=0; // khong co gi duoc chon het
       }
       for(int n=1;n<N;n++) { // duyet cac vat tu 1->N
           for(int w=0;w<=W;w++) { // trong luong tu 0->W
               // khong lay do vat n
               int luaChon1 = opt[n-1][w];
               
               // lay do vat n
               int luaChon2 = Integer.MIN_VALUE;
               if(items.get(n).getWeight()<=w) {
                   luaChon2 = items.get(n).getProfit()+opt[n-1][w-items.get(n).getWeight()];
               }
               
               // lua chon tot nhat
               opt[n][w] = Math.max(luaChon1, luaChon2);
               sol[n][w] = luaChon2 > luaChon1; // gan gia tri vao lua chon toi uu nhat trong ma tran sol
           }
       }
       // xac dinh do vat can lay
       for(int n=N-1,w=W; n>=0; n--) {
           if(sol[n][w]) {
               bag.add(items.get(n));
               tongValue += items.get(n).getProfit();
               w = w - items.get(n).getWeight();
           }
       }
   }

   public List<Item> getItems() {
       return items;
   }
   public int getMaxWeight() {
       return maxWeight;
   }
   public int getBagWeight() {
       return bagWeight;
   }
   public int getTongValue() {
       return tongValue;
   }
   public Bag<Item> getBag() {
       return bag;
   }
   
    
}

