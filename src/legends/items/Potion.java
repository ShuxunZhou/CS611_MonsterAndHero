package legends.items;

import legends.character.Hero;

public class Potion extends Item {
    private StatType stat;
    private int amt;

    public Potion(String n, int p, int lv, StatType s, int a) {
        super(n, p, lv);
        stat = s;
        amt = a;
    }
}
