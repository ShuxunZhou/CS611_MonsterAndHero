
package legends.items;

import legends.character.Monster;
import legends.character.Hero;

public class Spell extends Item {
    private int baseDamage;
    private int manaCost;
    private SpellType spellType;

    public Spell(String name, int price, int levelRequired, int baseDamage, int manaCost, SpellType type) {
        super(name, price, levelRequired);
        this.baseDamage = baseDamage;
        this.manaCost = manaCost;
        this.spellType = type;
    }

    public int getBaseDamage() { return baseDamage; }
    public int getManaCost() { return manaCost; }
    public SpellType getSpellType() { return spellType; }

    /**
     * Cast spells on monsters
     */
    public int castOn(Monster target, Hero caster) {
        int spellDamage = baseDamage + (int)(caster.getDexterity() * 0.5); // Improve Agility
        int levelBonus = caster.getLevel() * 20; // Level bonus

        // Spell type bonus
        double typeMultiplier = 1.0;
        switch (spellType) {
            case FIRE: typeMultiplier = 1.2; break;    // Fire damage +20%
            case ICE: typeMultiplier = 1.0; break;     // Ice-type standard damage
            case LIGHTNING: typeMultiplier = 1.1; break; // Ice-type standard damage
        }

        int totalDamage = (int)((spellDamage + levelBonus) * typeMultiplier);

        applySpellEffect(target);

        return totalDamage;
    }

    /**
     * Apply spell special effects
     */
    private void applySpellEffect(Monster target) {
        switch (spellType) {
            case FIRE:
                // Fire spells: Reduce monster defense.
                int defenseReduction = (int)(target.getDefense() * 0.1);
                target.reduceDefense(defenseReduction);
                System.out.println("🔥 " + target.getName() + "'s defense reduced by " + defenseReduction + "!");
                break;

            case ICE:
                // Ice spells: Reduce monster attack power
                int damageReduction = (int)(target.getDamage() * 0.1);
                target.reduceDamage(damageReduction);
                System.out.println("❄️ " + target.getName() + "'s damage reduced by " + damageReduction + "!");
                break;

            case LIGHTNING:
                // Lightning spells: Reduce monster evasion rate
                double dodgeReduction = 10.0;
                target.reduceDodge(dodgeReduction);
                System.out.println("⚡ " + target.getName() + "'s dodge chance reduced by " + dodgeReduction + "%!");
                break;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " [" + spellType + ", Dmg:" + baseDamage + ", Mana:" + manaCost + "]";
    }
}