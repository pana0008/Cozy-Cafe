package cafe.core;

import java.util.*;
import cafe.core.OrderManager;
import cafe.core.MenuItem;
import cafe.boxes.GiftBox;

public class Main {
    public static void main(String[] args) {
        OrderManager orderManager = new OrderManager();
        List<String> cart = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        double total = 0;

        System.out.println("=== WELCOME TO THE COZY CAFE! ===");

        mainLoop:
        while (true) {
            System.out.println("\n--- SELECT AN OPTION ---");
            System.out.println("1) Order Drink or Pastry");
            System.out.println("2) Order Gift Box");
            System.out.println("3) Checkout and Pay");
            System.out.print("Choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> {
                    MenuItem item = orderManager.selectItem();
                    if (item != null) {
                        cart.add(item.getDescription() + " - $" + String.format("%.2f", item.getPrice()));
                        total += item.getPrice();
                        System.out.println("Added to cart: " + item.getDescription());
                    }
                }
                case "2" -> {
                    GiftBox box = orderManager.buildGiftBox();
                    if (box != null) {
                        cart.add("Gift Box - $" + String.format("%.2f", box.getPrice()));
                        total += box.getPrice();
                        System.out.println("Added Gift Box to cart.");
                    }
                }
                case "3" -> {
                    break mainLoop;
                }
                default -> System.out.println("Invalid option. Please enter 1, 2, or 3.");
            }
        }

        System.out.println("\n=== RECEIPT ===");
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            for (String item : cart) {
                System.out.println(item);
            }
            System.out.printf("TOTAL: $%.2f%n", total);
        }

        System.out.println("\nThank you for visiting the Cozy Cafe!");
    }
}
