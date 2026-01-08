package cafe.core;

import cafe.boxes.*;
import cafe.decorators.*;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class OrderManager {

    private final CafeMenu menu = CafeMenu.getInstance();
    private final Scanner scanner = new Scanner(System.in);

    public MenuItem buildCustomDrink() {
        MenuItem item = showMenuAndSelect("Drinks", menu.getDrinkMenu());
        Set<String> appliedAddOns = new HashSet<>();
        while (true) {
            displayCurrentItem(item);
            displayAddonMenu();
            MenuItem updatedItem = applyAddon(item, appliedAddOns);
            if (updatedItem == null) break;
            item = updatedItem;
        }
        return item;
    }

    public GiftBox buildGiftBox() {
        BoxBuilder builder = selectGiftBoxBuilder();
        Baker baker = new Baker(builder);
        baker.prepareGiftBox();
        GiftBox box = builder.getResult();
        System.out.println("\n--- PREVIEW ---");
        System.out.println(box.getDescription());
        System.out.println("Price: $" + String.format("%.2f", box.getPrice()));
        while (true) {
            System.out.print("Would you like to buy this box? (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y")) {
                return box;
            } else if (input.equals("n")) {
                return null;
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }
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
        return "1".equals(choice) ? buildCustomDrink()
                : showMenuAndSelect("Pastries", menu.getPastryMenu());
    }

    private void displayCurrentItem(MenuItem item) {
        System.out.println("\nCurrent Item: " + item.getDescription() + " ($" + String.format("%.2f", item.getPrice()) + ")\n");
    }

    private void displayAddonMenu() {
        System.out.println("Select add-on for your drink (or 5 to finish):");
        menu.getAddonMenu().forEach((key, decorator) -> {
            MenuItem temp = decorator.apply(new MenuItem() {
                @Override public String getDescription() { return "Base"; }
                @Override public double getPrice() { return 0.0; }
            });
            String addOnName = temp.getDescription().replace("Base with ", "").trim();
            System.out.println(key + ") " + addOnName + " ($" + String.format("%.2f", temp.getPrice()) + ")");
        });
        System.out.println("5) Finish Order");
        System.out.print("Choice: ");
    }

    private MenuItem applyAddon(MenuItem item, Set<String> appliedAddOns) {
        String choice = scanner.nextLine();
        if ("5".equals(choice)) return null;

        Function<MenuItem, MenuItem> decorator = menu.getAddonMenu().get(choice);
        if (decorator != null) {
            MenuItem temp = decorator.apply(item);
            String addOnName = temp.getDescription()
                    .replace(item.getDescription(), "")
                    .replace(" with ", "")
                    .replace(", ", "")
                    .trim();

            if (appliedAddOns.contains(addOnName)) {
                System.out.println("This add-on is already added!");
                return item;
            } else {
                appliedAddOns.add(addOnName);
                return decorator.apply(item);
            }
        } else {
            System.out.println("Invalid add-on selection.");
            return item;
        }
    }

    private BoxBuilder selectGiftBoxBuilder() {
        BoxBuilder builder = null;
        while (builder == null) {
            System.out.println("\n--- SELECT BOX TO PREVIEW ---");
            System.out.println("1) Healthy");
            System.out.println("2) Dessert");
            System.out.print("Choice: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> builder = new HealthyGiftBoxBuilder();
                case "2" -> builder = new DessertGiftBoxBuilder();
                default -> System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }
        return builder;
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
}
