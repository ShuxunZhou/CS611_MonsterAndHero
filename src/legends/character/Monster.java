package legends.character;

import java.util.Random;

public abstract class Monster extends LivingEntity {
    protected int damage;
    protected int defense;
    protected double dodge;

    public Monster(String n, int lv, int hp, int dmg, int def, double dg) {
        super(n, lv, hp);
        this.damage = dmg;
        this.defense = (int)(def * 0.3);
        this.dodge = dg;
    }


    // Getters
    public int getDamage() { return damage; }
    public int getDefense() { return defense; }
    public double getDodgeChance() { return dodge * 0.01; }

    public void attack(Hero h) {
        int baseDamage = (int)(damage * 0.8);
        int levelBonus = level * 10;
        int randomFactor = new Random().nextInt(21) - 10;

        int totalDamage = baseDamage + levelBonus + randomFactor;
        totalDamage = Math.max(10, totalDamage);

        if (new Random().nextDouble() < h.getDodgeChance()) {
            System.out.println(h.getName() + " dodged!");
            return;
        }

        h.takeDamage(totalDamage);
        System.out.println(name + " hits " + h.getName() + " for " + totalDamage + " damage!");
    }


    // Spell Effects and Methods
    public void reduceDamage(int reduction) {
        damage = Math.max(1, damage - reduction);
    }

    public void reduceDefense(int reduction) {
        defense = Math.max(0, defense - reduction);
    }

    public void reduceDodge(double reduction) {
        dodge = Math.max(0, dodge - reduction);
    }

    @Override
    public String toString() {
        return super.toString() + " [Dmg:" + damage + ", Def:" + defense + ", Dodge:" + String.format("%.1f%%", getDodgeChance() * 100) + "]";
    }
}