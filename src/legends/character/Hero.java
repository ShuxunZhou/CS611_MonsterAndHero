/**
 * Hero.java
 *
 * Abstract base class for all hero characters in the Legends game.
 * Heroes can attack, cast spells, equip items, and gain experience.
 *
 */
package legends.character;

import legends.items.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Hero extends LivingEntity {
    protected int mp;
    protected int maxMp;
    protected int strength, dexterity, agility;
    protected int gold, xp;

    protected Inventory inv = new Inventory();
    protected Weapon weapon;
    protected Armor armor;

    /**
     * Constructor for Hero
     * @param name The hero's name
     * @param lv The hero's level
     * @param hp The hero's hit points
     * @param mp The hero's mana points
     * @param str The hero's strength
     * @param dex The hero's dexterity
     * @param agi The hero's agility
     * @param gold The hero's starting gold
     */
    public Hero(String name, int lv, int hp, int mp,
                int str, int dex, int agi, int gold) {
        super(name, lv, hp);
        this.mp = mp;
        this.maxMp = mp; // Initialize maximum mana points
        this.strength = str;
        this.dexterity = dex;
        this.agility = agi;
        this.gold = gold;
        this.xp = 0;
    }

    // Getter methods for all attributes
    public int getMp() { return mp; }
    public int getMaxMp() { return maxMp; }
    public int getStrength() { return strength; }
    public int getDexterity() { return dexterity; }
    public int getAgility() { return agility; }
    public int getGold() { return gold; }
    public int getXp() { return xp; }

    // Utility methods
    public void gainGold(int amount) { gold += amount; }

    /**
     * Restore mana points
     * @param amount The amount of MP to restore
     */
    public void restoreMp(int amount) {
        mp = Math.min(maxMp, mp + amount);
    }

    /**
     * Gain experience points and level up if threshold is reached
     * @param x The amount of XP to gain
     */
    public void gainXp(int x) {
        xp += x;
        if (xp >= level * 10) {
            level++;
            xp = 0;
            // Update maximum values on level up
            maxHp = level * 100;
            hp = maxHp;
            maxMp = (int)(maxMp * 1.1);
            mp = maxMp;
        }
    }

    /**
     * Calculate dodge chance based on agility
     * @return The dodge chance as a percentage
     */
    public double getDodgeChance() {
        return agility * 0.002; // Dodge chance based on agility
    }

    public Inventory getInventory() { return inv; }

    /**
     * Perform a physical attack on a monster
     * @param m The target monster
     */
    public void attack(Monster m) {
        // New damage calculation formula - significantly enhanced attack power
        int baseDamage = level * 50; // Base damage per level
        int strengthBonus = (int)(strength * 1.2); // Strength coefficient increased to 1.2
        int weaponDamage = (weapon != null ? weapon.getDamage() : 0);
        int criticalChance = new Random().nextInt(100);

        int totalDamage = baseDamage + strengthBonus + weaponDamage;

        // 20% critical hit chance, double damage
        if (criticalChance < 20) {
            totalDamage *= 2;
            System.out.println("💥 CRITICAL HIT! 💥");
        }

        // Apply monster defense reduction (but guarantee minimum 10% damage)
        int finalDamage = Math.max((int)(totalDamage * 0.1), totalDamage - m.getDefense());

        System.out.println(name + " attacks " + m.getName() + " for " + finalDamage + " damage!");
        m.takeDamage(finalDamage);
    }
    /**
     * Spend gold
     * @param amount The amount of gold to spend
     */
    public void spendGold(int amount) {
        gold -= amount;
    }


    /**
     * Cast a spell on a target monster
     * @param spell The spell to cast
     * @param target The target monster
     * @return true if spell was successfully cast, false otherwise
     */
    public boolean castSpell(Spell spell, Monster target) {
        // Check if hero has enough mana
        if (mp < spell.getManaCost()) {
            System.out.println(name + " doesn't have enough mana! (Need " + spell.getManaCost() + ", have " + mp + ")");
            return false;
        }

        // Check if monster dodges the spell
        if (new Random().nextDouble() < target.getDodgeChance()) {
            System.out.println(target.getName() + " dodged " + name + "'s " + spell.getName() + "!");
            mp -= spell.getManaCost(); // Still consume mana
            return true;
        }

        // Consume mana
        mp -= spell.getManaCost();

        // Calculate spell damage
        int spellDamage = spell.castOn(target, this);

        // Apply defense reduction
        int finalDamage = Math.max(1, spellDamage - target.getDefense());
        target.takeDamage(finalDamage);

        System.out.println("✨ " + name + " casts " + spell.getName() + " on " + target.getName() + " for " + finalDamage + " damage!");

        return true;
    }

    /**
     * Get all spells available to this hero based on level requirement
     * @return List of available spells
     */
    public List<Spell> getAvailableSpells() {
        List<Spell> spells = new ArrayList<>();
        for (Item item : inv.getAllItems()) {
            if (item instanceof Spell && item.getLevelRequired() <= level) {
                spells.add((Spell) item);
            }
        }
        return spells;
    }

    /**
     * Equip a weapon
     * @param w The weapon to equip
     */
    public void equipWeapon(Weapon w) {
        this.weapon = w;
    }

    /**
     * Equip armor
     * @param a The armor to equip
     */
    public void equipArmor(Armor a) {
        this.armor = a;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ").append(name).append(" ===\n");
        sb.append("Level: ").append(level).append(" | XP: ").append(xp).append("/").append(level * 10).append("\n");
        sb.append("HP: ").append(hp).append("/").append(maxHp).append(" | MP: ").append(mp).append("/").append(maxMp).append("\n");
        sb.append("Strength: ").append(strength).append(" | Dexterity: ").append(dexterity).append(" | Agility: ").append(agility).append("\n");
        sb.append("Gold: ").append(gold).append("\n");
        sb.append("Weapon: ").append(weapon != null ? weapon.getName() : "None").append("\n");
        sb.append("Armor: ").append(armor != null ? armor.getName() : "None");
        return sb.toString();
    }
}