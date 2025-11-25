package legends.market;

import legends.items.*;
import legends.character.Hero;

import java.util.*;

public class Market {
    private List<Item> stock = new ArrayList<>();

    public Market() {
        stock.add(new Weapon("Sword", 200, 1, 30, 1));
        stock.add(new Armor("Shield", 150, 1, 20));
        stock.add(new Potion("Healing Potion", 100, 1, StatType.HP, 50));
        stock.add(new Spell("Fireball", 250, 1, 40, 20, SpellType.FIRE));
    }

    public void run(Hero h, Scanner in) {
        while (true) {
            System.out.println("\n=== Market ===");
            for (int i = 0; i < stock.size(); i++)
                System.out.println("[" + i + "] " + stock.get(i));

            System.out.println("(B)uy, (S)ell, (Q)uit");
            String cmd = in.nextLine().toUpperCase();

            if (cmd.equals("Q")) break;

            if (cmd.equals("B")) {
                System.out.print("Which item? ");
                int idx = Integer.parseInt(in.nextLine());
                if (idx < 0 || idx >= stock.size()) continue;
                Item it = stock.get(idx);

                if (h.getGold() < it.getPrice()) {
                    System.out.println("Not enough gold.");
                    continue;
                }
                h.getInventory().addItem(it);
                stock.remove(it);
                System.out.println("Bought " + it.getName());
            }

            if (cmd.equals("S")) {
                List<Item> inv = h.getInventory().getAllItems();
                if (inv.isEmpty()) {
                    System.out.println("No items to sell.");
                    continue;
                }
                System.out.println("Inventory:");
                for (int i = 0; i < inv.size(); i++)
                    System.out.println("[" + i + "] " + inv.get(i));

                System.out.print("Sell which? ");
                int idx = Integer.parseInt(in.nextLine());
                if (idx < 0 || idx >= inv.size()) continue;
                Item it = inv.get(idx);
                stock.add(it);
                h.getInventory().removeItem(it);
                System.out.println("Sold " + it.getName());
            }
        }
    }
}
