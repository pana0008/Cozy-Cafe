package cafe.states;

import cafe.discount.*;
import java.util.Scanner;

public class CheckoutState implements OrderState {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void proceed(Order order) {
        applyDiscount(order);
        printReceipt(order);
        order.setState(new PaidState());
    }

    @Override
    public String getStatus() {
        return "Checkout";
    }

    private void applyDiscount(Order order) {
        String discountType = askDiscountType();
        switch (discountType) {
            case "s", "student" -> order.getCheckout().setDiscountCalculator(new StudentDiscountCalculator());
            case "r", "senior" -> order.getCheckout().setDiscountCalculator(new SeniorDiscountCalculator());
            default -> order.getCheckout().setDiscountCalculator(new NullDiscountCalculator());
        }
    }

    private String askDiscountType() {
        System.out.println("\nAre you a student, senior, or none of these? (Enter s for student, r for senior, n for none):");
        return sc.nextLine().toLowerCase();
    }

    private void printReceipt(Order order) {
        double totalAfterDiscount = order.getCheckout().calculateTotal(order.getBasket());
        System.out.println("\n=== RECEIPT ===\n");
        if (order.getBasket().getTotalAmount() == 0) {
            System.out.println("Your basket is empty.");
        } else {
            System.out.println("Amount of ordered items: " + order.getBasket().getTotalAmount());
            System.out.println("Items ordered:\n" + order.getBasket().getItemsDescription());
            System.out.println("***************************");
            System.out.printf("TOTAL: $%.2f%n", totalAfterDiscount);
        }
    }
}
