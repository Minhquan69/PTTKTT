import java.util.ArrayList;
import java.util.List;

public class KnapsackApp {

    public static void main(String[] args) {
        List<Item> it = new ArrayList<>();
        it.add(new Item("vat1", 1, 2)); // name - weight - value
        it.add(new Item("vat2", 3, 4));
        it.add(new Item("vat3", 2, 6));
        it.add(new Item("vat4", 5, 8));
        it.add(new Item("vat5", 4, 5));
        
        System.out.println("Do Xuan Trang");
        Knapsackimp caiTui = new Knapsackimp(it, 10);
        System.out.println("Tong gia tri: "+caiTui.getTongValue()+ " | Max Weight: "+caiTui.getMaxWeight());
        System.out.println("Nhung vat can lay la: ");
        for(Item i: caiTui.getBag()) {
        	System.out.println(" "+i.getName()+" | "+i.getProfit()+" | "+i.getWeight());
        }
    }

}
