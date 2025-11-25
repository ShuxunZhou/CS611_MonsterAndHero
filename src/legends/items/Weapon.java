package legends.items;

public class Weapon extends Item {
    private int dmg;
    private int hands;

    public Weapon(String n, int p, int lv, int d, int h) {
        super(n, p, lv);
        dmg = d;
        hands = h;
    }

    public int getDamage() { return dmg; }
}
