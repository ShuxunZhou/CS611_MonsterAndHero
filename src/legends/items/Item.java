
package legends.items;

public abstract class Item {
    protected String name;
    protected int price;
    protected int levelRequired;

    public Item(String name, int price, int levelRequired) {
        this.name = name;
        this.price = price;
        this.levelRequired = levelRequired;
    }

    // Getters
    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getLevelRequired() { return levelRequired; }

    @Override
    public String toString() {
        return name + " (Lv" + levelRequired + ", $" + price + ")";
    }
}