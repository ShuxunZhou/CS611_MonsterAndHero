package legends.game;

import legends.world.*;
import legends.character.*;
import legends.battle.Battle;
import legends.market.Market;

import java.util.*;

public class Game {
    private World world;
    private List<Hero> party = new ArrayList<>();
    private Scanner in = new Scanner(System.in);

    public Game() {
        world = new World(8);
        initParty();
    }

    private void initParty() {
        System.out.println("Choose your hero:");
        System.out.println("[1] Warrior");
        System.out.println("[2] Sorcerer");
        System.out.println("[3] Paladin");
        System.out.print("Enter choice: ");
        int c = Integer.parseInt(in.nextLine());

        Hero h;
        if (c == 1)
            h = new Warrior("Achilles", 1, 100, 100, 50, 30, 40, 500);
        else if (c == 2)
            h = new Sorcerer("Gandalf", 1, 100, 120, 30, 50, 30, 500);
        else
            h = new Paladin("Arthur", 1, 100, 110, 40, 40, 40, 500);

        party.add(h);
    }

    public void run() {
        printHelp();
        while (true) {
            world.printWorld();
            System.out.print("Command (W/A/S/D move, I info, Q quit): ");
            String cmd = in.nextLine().trim().toUpperCase();

            switch (cmd) {
                case "W":
                    moveAndCheck(world.moveUp());
                    break;
                case "A":
                    moveAndCheck(world.moveLeft());
                    break;
                case "S":
                    moveAndCheck(world.moveDown());
                    break;
                case "D":
                    moveAndCheck(world.moveRight());
                    break;
                case "I":
                    showInfo();
                    break;
                case "Q":
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Unknown command.");
            }
        }
    }

    private void moveAndCheck(boolean moved) {
        if (!moved) return;
        Cell cell = world.getCell(world.getPartyPosition());

        if (cell.getType() == TileType.MARKET) {
            enterMarket();
        } else if (cell.getType() == TileType.COMMON) {
            if (new Random().nextDouble() < 0.35) {
                startBattle();
            }
        }
    }

    private void enterMarket() {
        System.out.println("You entered a market.");
        Market market = new Market();
        market.run(party.get(0), in);
    }

    private void startBattle() {
        System.out.println("\nA battle begins!");

        Monster m = new Dragon("Firedrake",
                party.get(0).getLevel(),
                party.get(0).getLevel() * 100,
                50,
                10,
                0.1);

        Battle battle = new Battle(party, Collections.singletonList(m));
        boolean win = battle.start(in);

        if (!win) {
            System.out.println("You died. Game over.");
            System.exit(0);
        }
    }

    private void showInfo() {
        System.out.println("=== Party ===");
        for (Hero h : party)
            System.out.println(h);
    }

    private void printHelp() {
        System.out.println(
                "Welcome to Legends: Heroes and Monsters\n" +
                        "W/A/S/D : Move\n" +
                        "I       : Show hero info\n" +
                        "Q       : Quit\n"
        );
    }

    public static void main(String[] args) {
        new Game().run();
    }
}
