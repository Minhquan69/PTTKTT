
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String name;
    private int weight;
    private int value;

    /**
     * Constructor for objects of class Item
     */
    public Item()
    {
    }
    
    public Item(String name,int weight,int value){
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String getName()
    {
        return name;
    }
    public int getWeight(){
        return weight;
    }
    public int getValue(){
        return value;
    }
}