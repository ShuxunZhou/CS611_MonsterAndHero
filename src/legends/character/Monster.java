/**
 * Monster.java
 *
 * Abstract base class for all monster types in the Legends game.
 * Monsters have damage, defense, and dodge capabilities.
 *
 */
package legends.character;

import java.util.Random;

public abstract class Monster extends LivingEntity {
    protected int damage;
    protected int defense;
    protected double dodge;

    /**
     * Constructor for Monster
     * @param n The monster's name
     * @param lv The monster's level
     * @param hp The monster's hit points
     * @param dmg The monster's damage value
     * @param def The monster's defense value
     * @param dg The monster's dodge percentage
     */
    public Monster(String n, int lv, int hp, int dmg, int def, double dg) {
        super(n, lv, hp);
        this.damage = dmg;
        this.defense = (int)(def * 0.3); // Reduce defense to 30% for balance
        this.dodge = dg;
    }

    // Getter methods
    public int getDamage() { return damage; }
    public int getDefense() { return defense; }
    public double getDodgeChance() { return dodge * 0.01; }

    /**
     * Attack a hero with calculated damage
     * @param h The target hero
     */
    public void attack(Hero h) {
        int baseDamage = (int)(damage * 0.8); // Reduce to 80% for balance
        int levelBonus = level * 10; // Level bonus
        int randomFactor = new Random().nextInt(21) - 10; // Random factor -10 to +10

        int totalDamage = baseDamage + levelBonus + randomFactor;
        totalDamage = Math.max(10, totalDamage); // Minimum 10 damage

        if (new Random().nextDouble() < h.getDodgeChance()) {
            System.out.println(h.getName() + " dodged!");
            return;
        }

        h.takeDamage(totalDamage);
        System.out.println(name + " hits " + h.getName() + " for " + totalDamage + " damage!");
    }

    // Spell effect methods for debuffs
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