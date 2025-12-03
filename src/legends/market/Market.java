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
            System.out.println("Gold: " + h.getGold());
            for (int i = 0; i < stock.size(); i++)
                System.out.println("[" + i + "] " + stock.get(i));

            System.out.println("(B)uy, (S)ell, (Q)uit");
            System.out.print("Command: ");
            String cmd = in.nextLine().toUpperCase().trim();

            if (cmd.equals("Q")) break;

            if (cmd.equals("B")) {
                System.out.print("Which item? ");
                try {
                    int idx = Integer.parseInt(in.nextLine());
                    if (idx < 0 || idx >= stock.size()) {
                        System.out.println("Invalid selection.");
                        continue;
                    }
                    Item it = stock.get(idx);

                    if (h.getGold() < it.getPrice()) {
                        System.out.println("Not enough gold.");
                        continue;
                    }
                    h.getInventory().addItem(it);
                    h.spendGold(it.getPrice());
                    stock.remove(it);
                    System.out.println("Bought " + it.getName() + " for " + it.getPrice() + " gold!");
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
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
                try {
                    int idx = Integer.parseInt(in.nextLine());
                    if (idx < 0 || idx >= inv.size()) {
                        System.out.println("Invalid selection.");
                        continue;
                    }
                    Item it = inv.get(idx);
                    stock.add(it);
                    h.getInventory().removeItem(it);
                    h.gainGold(it.getPrice());
                    System.out.println("Sold " + it.getName() + " for " + it.getPrice() + " gold!");
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            }
        }
    }
}
