package legends.data;

import legends.character.*;
import legends.items.*;
import java.util.*;

public class GameDataLoader {

    // 🔧 创建平衡的英雄
    public static List<Hero> createBalancedHeroes() {
        List<Hero> heroes = new ArrayList<>();

        // 根据您的数据，但调整HP计算
        heroes.add(new Warrior("Gaerdal_Ironhand", 7, 700, 100, 700, 600, 500, 1354));
        heroes.add(new Paladin("Parzival", 7, 700, 300, 750, 700, 650, 2500));
        heroes.add(new Sorcerer("Rillifane_Rallathil", 9, 900, 1300, 750, 500, 450, 2500));

        return heroes;
    }

    // 🔧 创建平衡的怪物（降低防御力）
    public static List<Monster> createBalancedMonsters(int heroLevel) {
        List<Monster> monsters = new ArrayList<>();

        // 选择与英雄等级相近的怪物，并调整数值
        if (heroLevel <= 3) {
            monsters.add(new Dragon("Natsunomeryu", 1, 200, 100, 60, 10)); // 防御从200降到60
            monsters.add(new Spirit("Blinky", 1, 150, 450, 105, 35)); // 防御从350降到105
        } else if (heroLevel <= 6) {
            monsters.add(new Dragon("Chrysophylax", 2, 400, 200, 150, 20)); // 防御从500降到150
            monsters.add(new Exoskeleton("Brandobaris", 3, 500, 350, 135, 30)); // 防御从450降到135
        } else {
            monsters.add(new Dragon("Desghidorrah", 3, 600, 300, 120, 35)); // 防御从400降到120
            monsters.add(new Spirit("Chiang-shih", 4, 700, 700, 180, 40)); // 防御从600降到180
        }

        return monsters;
    }

    // 🔧 创建武器
    public static List<Weapon> createWeapons() {
        List<Weapon> weapons = new ArrayList<>();
        weapons.add(new Weapon("Dagger", 200, 1, 250, 1));
        weapons.add(new Weapon("Sword", 500, 1, 800, 1));
        weapons.add(new Weapon("Bow", 300, 2, 500, 2));
        weapons.add(new Weapon("Axe", 550, 5, 850, 1));
        weapons.add(new Weapon("Scythe", 1000, 6, 1100, 2));
        weapons.add(new Weapon("TSwords", 1400, 8, 1600, 2));
        return weapons;
    }
}