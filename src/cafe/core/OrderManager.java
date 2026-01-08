package cafe.core;

import cafe.boxes.*;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Supplier;

public class OrderManager {

    private final CafeMenu menu = CafeMenu.getInstance();
    private final Scanner scanner = new Scanner(System.in);

    public MenuItem buildCustomDrink() {
        MenuItem item = showMenuAndSelect("Drinks", menu.getDrinkMenu());

        while (true) {
            System.out.println("\nCurrent Item: " + item.getDescription() + " ($" +
                    String.format("%.2f", item.getPrice()) + ")\n");
            System.out.println("Select add-on (or 5 to finish):");

            menu.getAddonMenu().forEach((key, decorator) -> {
                MenuItem preview = decorator.apply(new MenuItem() {
                    public String getDescription() { return ""; }
                    public double getPrice() { return 0.0; }
                });

                System.out.println(key + ") " + preview.getDescription().replace(", ", "") + " ($" + String.format("%.2f", preview.getPrice()) + ")");
            });

            System.out.println("5) Finish Order");
            System.out.print("Choice: ");
            String choice = scanner.nextLine();

            if ("5".equals(choice)) break;

            Function<MenuItem, MenuItem> decorator = menu.getAddonMenu().get(choice);
            if (decorator != null) {
                item = decorator.apply(item);
            } else {
                System.out.println("Invalid add-on selection.");
            }
        }
        return item;
    }

    public GiftBox buildGiftBox() {
        System.out.println("\n--- SELECT BOX TO PREVIEW ---");
        System.out.println("1) Healthy");
        System.out.println("2) Dessert");
        System.out.print("Choice: ");

        String choice = scanner.nextLine();
        BoxBuilder builder = "1".equals(choice) ? new HealthyGiftBoxBuilder() : new DessertGiftBoxBuilder();

        Baker baker = new Baker(builder);
        baker.prepareGiftBox();
        GiftBox box = builder.getResult();

        System.out.println("\n--- PREVIEW ---");
        System.out.println(box.getContents());
        System.out.println("Price: $" + String.format("%.2f", box.getPrice()));
        System.out.print("Would you like to buy this box? (y/n): ");

        return scanner.nextLine().equalsIgnoreCase("y") ? box : null;
    }

    private MenuItem showMenuAndSelect(String title, Map<String, Supplier<MenuItem>> menuMap) {
        MenuItem item = null;

        while (item == null) {
            System.out.println("\n--- " + title + " ---");
            menuMap.forEach((key, supplier) -> {
                MenuItem sample = supplier.get();
                System.out.println(key + ") " + sample.getDescription() + " ($" + String.format("%.2f", sample.getPrice()) + ")");
            });

            System.out.print("Choice: ");
            Supplier<MenuItem> supplier = menuMap.get(scanner.nextLine());
            if (supplier != null) item = supplier.get();
            else System.out.println("Invalid selection. Please try again.");
        }
        return item;
    }

    public MenuItem selectItem() {
        String choice;
        do {
            System.out.println("\n--- WHAT WOULD YOU LIKE TO ORDER ---");
            System.out.println("1) Drink");
            System.out.println("2) Pastry");
            System.out.print("Choice: ");
            choice = scanner.nextLine();
        } while (!"1".equals(choice) && !"2".equals(choice));

        return "1".equals(choice) ? buildCustomDrink() : showMenuAndSelect("Pastries", menu.getPastryMenu());
    }
}
