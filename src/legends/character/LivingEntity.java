
package legends.character;

public abstract class LivingEntity {
    protected String name;
    protected int level;
    protected int hp;
    protected int maxHp;

    public LivingEntity(String n, int lv, int hp) {
        this.name = n;
        this.level = lv;
        this.hp = hp;
        this.maxHp = hp; // 初始化最大生命值
    }

    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public boolean isAlive() { return hp > 0; }

    public void takeDamage(int dmg) {
        hp = Math.max(0, hp - dmg);
    }

    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
    }

    @Override
    public String toString() {
        return name + " (Lvl " + level + ", HP=" + hp + "/" + maxHp + ")";
    }
}