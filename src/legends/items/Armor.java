package legends.items;

public class Armor extends Item {
    private int block;

    public Armor(String n, int p, int lv, int b) {
        super(n, p, lv);
        block = b;
    }

    public int getBlock() { return block; }
}
