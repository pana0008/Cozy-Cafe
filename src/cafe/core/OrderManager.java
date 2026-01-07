package cafe.core;

import cafe.drinks.*;
import cafe.pastries.*;
import cafe.decorators.*;
import cafe.boxes.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Supplier;

public class OrderManager {

    private final Map<String, Supplier<MenuItem>> drinkMenu = new HashMap<>();
    private final Map<String, Supplier<MenuItem>> pastryMenu = new HashMap<>();
    private final Map<String, Function<MenuItem, MenuItem>> addonMenu = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    public OrderManager() {
        drinkMenu.put("1", Cappuccino::new);
        drinkMenu.put("2", BananaSmoothie::new);
        drinkMenu.put("3", HotChocolate::new);
        drinkMenu.put("4", Latte::new);
        drinkMenu.put("5", Tea::new);

        pastryMenu.put("1", BananaBread::new);
        pastryMenu.put("2", BlueberryOatMuffin::new);
        pastryMenu.put("3", Banitsa::new);
        pastryMenu.put("4", CinnamonRoll::new);
        pastryMenu.put("5", Croissant::new);

        addonMenu.put("1", CaramelSyrup::new);
        addonMenu.put("2", ExtraShot::new);
        addonMenu.put("3", OatMilk::new);
        addonMenu.put("4", WhippedCream::new);
    }

    public MenuItem buildCustomDrink() {
        MenuItem item = showMenuAndSelect("Drinks", drinkMenu);
        while (true) {
            System.out.println("\n--- Current Item: " + item.getDescription() + " ($" + String.format("%.2f", item.getPrice()) + ") ---");
            System.out.println("Select add-on for your drink (or choose '5' to finish):");
            addonMenu.forEach((key, decoratorFunc) -> {
                MenuItem temp = decoratorFunc.apply(new MenuItem() {
                    public String getDescription() { return ""; }
                    public double getPrice() { return 0.0; }
                });
                System.out.println(key + ") " + temp.getDescription().replace(", ", "") + " ($" + String.format("%.2f", temp.getPrice()) + ")");
            });
            System.out.println("5) Finish Order");
            System.out.print("Choice: ");
            String choice = scanner.nextLine();
            if ("5".equals(choice)) break;

            if (addonMenu.containsKey(choice)) {
                item = addonMenu.get(choice).apply(item);
            } else {
                System.out.println("Invalid add-on selection");
            }
        }
        return item;
    }

    public GiftBox buildGiftBox() {
        System.out.println("\n--- SELECT BOX TO PREVIEW ---");
        System.out.println("1) Healthy");
        System.out.println("2) Dessert");

        String choice = scanner.nextLine();
        BoxBuilder builder = "1".equals(choice) ? new HealthyGiftBoxBuilder() : new DessertGiftBoxBuilder();

        Baker baker = new Baker(builder);
        baker.prepareGiftBox();
        GiftBox previewBox = builder.getResult();

        System.out.println("\n--- PREVIEW ---");
        System.out.println(previewBox.getContents());
        System.out.println("Price: $" + String.format("%.2f", previewBox.getPrice()));
        System.out.println("Would you like to buy this box? (y/n)");

        String confirm = scanner.nextLine();
        return "y".equalsIgnoreCase(confirm) ? previewBox : null;
    }

    private MenuItem showMenuAndSelect(String title, Map<String, Supplier<MenuItem>> menu) {
        MenuItem item = null;
        while (item == null) {
            System.out.println("\n--- " + title + " ---");
            menu.forEach((key, supplier) -> {
                MenuItem sample = supplier.get();
                System.out.println(key + ") " + sample.getDescription() + " ($" + String.format("%.2f", sample.getPrice()) + ")");
            });
            System.out.print("Choice: ");
            String choice = scanner.nextLine();
            Supplier<MenuItem> supplier = menu.get(choice);
            if (supplier != null) item = supplier.get();
            else System.out.println("Invalid selection. Please try again.");
        }
        return item;
    }

    public MenuItem selectItem() {
        String category = "";
        while (!"1".equals(category) && !"2".equals(category)) {
            System.out.println("\n--- WHAT WOULD YOU LIKE TO ORDER ---");
            System.out.println("1) Drink");
            System.out.println("2) Pastry");
            System.out.print("Choice: ");
            category = scanner.nextLine();

            if (!"1".equals(category) && !"2".equals(category)) {
                System.out.println("Invalid category. Please choose 1 or 2.");
            }
        }
        return "1".equals(category) ? buildCustomDrink() : showMenuAndSelect("Pastries", pastryMenu);
    }
}