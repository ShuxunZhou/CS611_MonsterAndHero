
/**
 * LivingEntity.java
 *
 * Abstract base class for all living entities in the Legends game.
 * This class defines common attributes and behaviors for heroes and monsters.
 *
 */
package legends.character;

public abstract class LivingEntity {
    protected String name;
    protected int level;
    protected int hp;
    protected int maxHp;

    /**
     * Constructor for LivingEntity
     * @param n The name of the entity
     * @param lv The level of the entity
     * @param hp The hit points of the entity
     */
    public LivingEntity(String n, int lv, int hp) {
        this.name = n;
        this.level = lv;
        this.hp = hp;
        this.maxHp = hp; // Initialize maximum health points
    }

    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public boolean isAlive() { return hp > 0; }

    /**
     * Reduce HP by damage amount
     * @param dmg The amount of damage to take
     */
    public void takeDamage(int dmg) {
        hp = Math.max(0, hp - dmg);
    }

    /**
     * Restore HP by healing amount
     * @param amount The amount of HP to restore
     */
    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
    }

    @Override
    public String toString() {
        return name + " (Lvl " + level + ", HP=" + hp + "/" + maxHp + ")";
    }
}