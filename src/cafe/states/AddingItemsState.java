package cafe.states;

import cafe.core.*;
import java.util.Scanner;

public class AddingItemsState implements OrderState {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void proceed(Order order) {
        OrderManager manager = order.getManager();

        while (true) {
            displayMenu();
            String choice = sc.nextLine();
            if (handleChoice(choice, order, manager)) {
                return;
            }
        }
    }

    @Override
    public String getStatus() {
        return "Adding Items";
    }

    private void displayMenu() {
        System.out.println("\n--- SELECT AN OPTION ---");
        System.out.println("1) Order Drink or Pastry");
        System.out.println("2) Order Gift Box");
        System.out.println("3) Checkout and Pay");
        System.out.print("Choice: ");
    }

    private boolean handleChoice(String choice, Order order, OrderManager manager) {
        switch (choice) {
            case "1" -> addDrinkOrPastry(order, manager);
            case "2" -> addGiftBox(order, manager);
            case "3" -> {
                order.setState(new CheckoutState());
                return true;
            }
            default -> System.out.println("Invalid option. Please enter 1, 2, or 3.");
        }
        return false;
    }

    private void addDrinkOrPastry(Order order, OrderManager manager) {
        MenuItem item = manager.selectItem();
        if (item != null) {
            order.getBasket().addProduct(item);
            System.out.println("Added " + item.getDescription() + " to basket.");
        }
    }

    private void addGiftBox(Order order, OrderManager manager) {
        MenuItem box = manager.buildGiftBox();
        if (box != null) {
            order.getBasket().addProduct(box);
            System.out.println("Added Gift Box to basket.");
        }
    }
}
