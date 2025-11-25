/**
 * Weapon.java
 *
 * Represents weapons that heroes can equip to increase their damage.
 * Each weapon has damage value and hand requirement.
 *
 */
package legends.items;

public class Weapon extends Item {
    private int dmg;        // Damage value of the weapon
    private int hands;      // Number of hands required to wield

    /**
     * Constructor for Weapon
     * @param n The weapon's name
     * @param p The weapon's price
     * @param lv The level required to use this weapon
     * @param d The damage value of the weapon
     * @param h The number of hands required to wield
     */
    public Weapon(String n, int p, int lv, int d, int h) {
        super(n, p, lv);
        dmg = d;
        hands = h;
    }

    /**
     * Get the damage value of this weapon
     * @return The weapon's damage value
     */
    public int getDamage() { return dmg; }

    /**
     * Get the hand requirement of this weapon
     * @return The number of hands required
     */
    public int getHandsRequired() { return hands; }
}