
/**
 * Exoskeleton.java
 *
 * Exoskeleton monsters are balanced creatures with moderate stats.
 * They represent armored creatures in the Legends game world.
 *
 */
package legends.character;

public class Exoskeleton extends Monster {

    /**
     * Constructor for Exoskeleton monster
     * @param n The monster's name
     * @param lv The monster's level
     * @param hp The monster's hit points
     * @param dmg The monster's damage value
     * @param def The monster's defense value
     * @param dg The monster's dodge percentage
     */
    public Exoskeleton(String n, int lv, int hp, int dmg, int def, double dg) {
        super(n, lv, hp, dmg, def, dg);
    }
}