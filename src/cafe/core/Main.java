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

        while (!order.getState().getStatus().equals("Paid")) {
            order.proceed();
        }
    }
}