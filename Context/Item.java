public class Item {
    private int weight; // Trong luong cua vat
    private int profit; // Gia tri cua vat
    private String name; // Ten cua vat
    
    public Item(String name, int weight, int profit) {
        this.name = name;
        this.weight = weight;
        this.profit = profit;
    }
    
    public int getWeight() {
        return weight;
    }
    
    public int getProfit() {
        return profit;
    }
    
    public String getName() {
        return name;
    }
    
    public int getRealWeight() {
        // Trong truong hop ?? v?t khong co trong luong (weight = 0), 
    	// tra ve 1 de tranh chia cho 0
        return weight == 0 ? 1 : weight;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
