package legends.battle;

import legends.character.*;
import legends.items.Spell;
import java.util.*;

public class Battle {
    private List<Hero> heroes;
    private List<Monster> monsters;

    public Battle(List<Hero> h, List<Monster> m) {
        heroes = h;
        monsters = m;
    }

    public boolean start(Scanner in) {
        System.out.println("⚔️ Battle begins! ⚔️");
        showBattleStatus();

        while (alive(heroes) && alive(monsters)) {
            // 英雄回合
            for (Hero hero : heroes) {
                if (!hero.isAlive()) continue;

                System.out.println("\n" + hero.getName() + "'s turn:");
                showActionMenu();
                String choice = in.nextLine().trim().toUpperCase();

                Monster target = selectTarget(in);
                if (target == null) break;

                switch (choice) {
                    case "A":
                        hero.attack(target);
                        break;
                    case "S":
                        castSpell(hero, target, in);
                        break;
                    case "I":
                        showBattleInfo();
                        continue; // 不结束回合
                    default:
                        System.out.println(hero.getName() + " passes this turn.");
                        break;
                }

                if (!alive(monsters)) break;
            }

            if (!alive(monsters)) break;

            // 怪物回合
            System.out.println("\n--- Monster Turn ---");
            for (Monster monster : monsters) {
                if (!monster.isAlive()) continue;

                Hero target = selectRandomAliveHero();
                if (target != null) {
                    monster.attack(target);
                }
            }

            // 回合结束恢复
            endOfRoundRecovery();
            showBattleStatus();
        }

        // 战斗结束
        if (alive(heroes)) {
            System.out.println("\n🎉 Victory! The heroes win! 🎉");
            distributeRewards();
            return true;
        } else {
            System.out.println("\n💀 Defeat! The heroes have fallen... 💀");
            return false;
        }
    }

    private void showActionMenu() {
        System.out.println("Choose action:");
        System.out.println("(A)ttack | (S)pell | (I)nfo | (P)ass");
        System.out.print("Your choice: ");
    }

    private void castSpell(Hero hero, Monster target, Scanner in) {
        List<Spell> availableSpells = hero.getAvailableSpells();

        if (availableSpells.isEmpty()) {
            System.out.println(hero.getName() + " has no spells available!");
            return;
        }

        System.out.println("\n--- Available Spells ---");
        for (int i = 0; i < availableSpells.size(); i++) {
            Spell spell = availableSpells.get(i);
            System.out.println((i + 1) + ". " + spell);
        }
        System.out.println("0. Cancel");
        System.out.print("Choose spell: ");

        try {
            int choice = Integer.parseInt(in.nextLine().trim());
            if (choice == 0) {
                System.out.println("Spell casting cancelled.");
                return;
            }
            if (choice > 0 && choice <= availableSpells.size()) {
                Spell selectedSpell = availableSpells.get(choice - 1);
                hero.castSpell(selectedSpell, target);
            } else {
                System.out.println("Invalid choice!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }

    private Monster selectTarget(Scanner in) {
        List<Monster> aliveMonsters = new ArrayList<>();
        for (Monster m : monsters) {
            if (m.isAlive()) {
                aliveMonsters.add(m);
            }
        }

        if (aliveMonsters.isEmpty()) return null;
        if (aliveMonsters.size() == 1) return aliveMonsters.get(0);

        System.out.println("\nSelect target:");
        for (int i = 0; i < aliveMonsters.size(); i++) {
            System.out.println((i + 1) + ". " + aliveMonsters.get(i).getName() +
                    " (HP: " + aliveMonsters.get(i).getHp() + ")");
        }
        System.out.print("Choose target: ");

        try {
            int choice = Integer.parseInt(in.nextLine().trim());
            if (choice > 0 && choice <= aliveMonsters.size()) {
                return aliveMonsters.get(choice - 1);
            }
        } catch (NumberFormatException e) {
            // Fall through to return first monster
        }

        return aliveMonsters.get(0); // 默认选择第一个
    }

    private Hero selectRandomAliveHero() {
        List<Hero> aliveHeroes = new ArrayList<>();
        for (Hero h : heroes) {
            if (h.isAlive()) {
                aliveHeroes.add(h);
            }
        }
        if (aliveHeroes.isEmpty()) return null;
        return aliveHeroes.get(new Random().nextInt(aliveHeroes.size()));
    }

    private void endOfRoundRecovery() {
        for (Hero hero : heroes) {
            if (hero.isAlive()) {
                // 每回合恢复 10% HP 和 MP
                int hpRecover = (int)(hero.getMaxHp() * 0.1);
                int mpRecover = (int)(hero.getMaxMp() * 0.1);
                hero.heal(hpRecover);
                hero.restoreMp(mpRecover);
            }
        }
    }

    private void showBattleStatus() {
        System.out.println("\n=== Battle Status ===");
        System.out.println("Heroes:");
        for (Hero h : heroes) {
            if (h.isAlive()) {
                System.out.println("  " + h.getName() + " - HP:" + h.getHp() + "/" + h.getMaxHp() +
                        " MP:" + h.getMp() + "/" + h.getMaxMp());
            }
        }
        System.out.println("Monsters:");
        for (Monster m : monsters) {
            if (m.isAlive()) {
                System.out.println("  " + m.getName() + " - HP:" + m.getHp() + "/" + m.getMaxHp());
            }
        }
        System.out.println("===================");
    }

    private void showBattleInfo() {
        System.out.println("\n=== Detailed Battle Info ===");
        System.out.println("Your Heroes:");
        for (Hero h : heroes) {
            if (h.isAlive()) {
                System.out.println(h.toString());
                System.out.println();
            }
        }
        System.out.println("Enemy Monsters:");
        for (Monster m : monsters) {
            if (m.isAlive()) {
                System.out.println(m.toString());
                System.out.println();
            }
        }
    }

    private void distributeRewards() {
        int baseXp = 100;
        int baseGold = 200;

        for (Hero hero : heroes) {
            if (hero.isAlive()) {
                hero.gainXp(baseXp);
                hero.gainGold(baseGold);
                System.out.println(hero.getName() + " gains " + baseXp + " XP and " + baseGold + " gold!");
            }
        }
    }

    private boolean alive(List<? extends LivingEntity> entities) {
        for (LivingEntity e : entities) {
            if (e.isAlive()) return true;
        }
        return false;
    }
}