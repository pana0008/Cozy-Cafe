package cafe.core;

import cafe.discount.*;
import cafe.states.Order;


public class Main {
    public static void main(String[] args) {
        OrderManager orderManager = new OrderManager();
        Basket basket = new Basket();
        Checkout checkout = new Checkout();
        Order order = new Order(orderManager, basket, checkout);

        System.out.println("=== WELCOME TO THE COZY CAFE! ===");
        System.out.println("       ( (");
        System.out.println("        ) )");
        System.out.println("      ........  What would");
        System.out.println("      |      |]   you like");
        System.out.println("      \\      /   to order?");
        System.out.println("       `----'");

        while (!order.getState().getStatus().equals("Paid")) {
            order.proceed();
        }
    }
}