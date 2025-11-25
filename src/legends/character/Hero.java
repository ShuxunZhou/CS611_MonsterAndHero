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

    public Hero(String name, int lv, int hp, int mp,
                int str, int dex, int agi, int gold) {
        super(name, lv, hp);
        this.mp = mp;
        this.maxMp = mp; // 初始化最大法力值
        this.strength = str;
        this.dexterity = dex;
        this.agility = agi;
        this.gold = gold;
        this.xp = 0;
    }

    // 添加缺少的getter方法
    public int getMp() { return mp; }
    public int getMaxMp() { return maxMp; }
    public int getStrength() { return strength; }
    public int getDexterity() { return dexterity; }
    public int getAgility() { return agility; }
    public int getGold() { return gold; }
    public int getXp() { return xp; }

    // 添加其他有用的方法
    public void gainGold(int amount) { gold += amount; }
    public void restoreMp(int amount) {
        mp = Math.min(maxMp, mp + amount);
    }

    public void gainXp(int x) {
        xp += x;
        if (xp >= level * 10) {
            level++;
            xp = 0;
            // 升级时更新最大值
            maxHp = level * 100;
            hp = maxHp;
            maxMp = (int)(maxMp * 1.1);
            mp = maxMp;
        }
    }

    public double getDodgeChance() {
        return agility * 0.002; // 基于敏捷度计算闪避
    }

    public Inventory getInventory() { return inv; }

    public void attack(Monster m) {
        // 🔧 New damage calculation formula - significantly increases attack power
        int baseDamage = level * 50; // Base damage per level
        int strengthBonus = (int)(strength * 1.2); // The strength coefficient is 1.2.
        int weaponDamage = (weapon != null ? weapon.getDamage() : 0);
        int criticalChance = new Random().nextInt(100);

        int totalDamage = baseDamage + strengthBonus + weaponDamage;

        // 20% critical hit chance, double damage.
        if (criticalChance < 20) {
            totalDamage *= 2;
            System.out.println("💥 CRITICAL HIT! 💥");
        }

        // Apply monster defense reduction (but guarantee a minimum of 10% damage reduction).
        int finalDamage = Math.max((int)(totalDamage * 0.1), totalDamage - m.getDefense());

        System.out.println(name + " attacks " + m.getName() + " for " + finalDamage + " damage!");
        m.takeDamage(finalDamage);
    }


    public boolean castSpell(Spell spell, Monster target) {
        // Check if you have enough mana.
        if (mp < spell.getManaCost()) {
            System.out.println(name + " doesn't have enough mana! (Need " + spell.getManaCost() + ", have " + mp + ")");
            return false;
        }

        // Check if the monster dodges.
        if (new Random().nextDouble() < target.getDodgeChance()) {
            System.out.println(target.getName() + " dodged " + name + "'s " + spell.getName() + "!");
            mp -= spell.getManaCost();
            return true;
        }

        // Consume mana
        mp -= spell.getManaCost();

        // Calculate spell damage
        int spellDamage = spell.castOn(target, this);

        // Application defense reduction
        int finalDamage = Math.max(1, spellDamage - target.getDefense());
        target.takeDamage(finalDamage);

        System.out.println("✨ " + name + " casts " + spell.getName() + " on " + target.getName() + " for " + finalDamage + " damage!");

        return true;
    }

    public List<Spell> getAvailableSpells() {
        List<Spell> spells = new ArrayList<>();
        for (Item item : inv.getAllItems()) {
            if (item instanceof Spell && item.getLevelRequired() <= level) {
                spells.add((Spell) item);
            }
        }
        return spells;
    }

    public void equipWeapon(Weapon w) {
        this.weapon = w;
    }

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