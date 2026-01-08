package cafe.core;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        OrderManager orderManager = new OrderManager();
        Basket basket = new Basket();
        Scanner sc = new Scanner(System.in);

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
                        basket.addProduct(item);
                        System.out.println("Added " + item.getDescription() + " to basket.");
                    }
                }

                case "2" -> {
                    MenuItem box = orderManager.buildGiftBox();
                    if (box != null) {
                        basket.addProduct(box);
                        System.out.println("Added Gift Box to basket.");
                    }
                }

                case "3" -> {
                    break mainLoop;
                }

                default -> System.out.println("Invalid option. Please enter 1, 2, or 3.");
            }
        }

        System.out.println("\n=== RECEIPT ===");

        if (basket.getTotalAmount() == 0) {
            System.out.println("Your basket is empty.");
        } else {
            System.out.println("Amount of ordered items: " + basket.getTotalAmount());
            System.out.println("Items ordered:\n" + basket.getItemsDescription());
            System.out.printf("TOTAL: $%.2f%n", basket.getTotalPrice());
        }

        System.out.println("\nThank you for visiting the Cozy Cafe!");
        sc.close();
    }
}