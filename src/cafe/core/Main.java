package cafe.core;

import cafe.core.ConsoleReader;

import cafe.decorators.CaramelSyrup;
import cafe.decorators.ExtraShot;
import cafe.decorators.OatMilk;
import cafe.decorators.WhippedCream;
import cafe.drinks.Latte;

public class Main {
    public static void main(String[] args) {

        MenuItem latte = new Latte();
        ConsoleReader reader = new ConsoleReader();

        System.out.println("Welcome to the cozy cafe!");
        System.out.println("You ordered a Latte.");

        System.out.print("Add Oat Milk? (y/n): ");
        String input = reader.readLine();
        if (input.equalsIgnoreCase("y")) {
            latte = new OatMilk(latte);
        }

        System.out.print("Add Vanilla Syrup? (y/n): ");
        input = reader.readLine();
        if (input.equalsIgnoreCase("y")) {
            latte = new CaramelSyrup(latte);
        }

        System.out.print("Add Extra Shot? (y/n): ");
        input = reader.readLine();
        if (input.equalsIgnoreCase("y")) {
            latte = new ExtraShot(latte);
        }

        System.out.print("Add Whipped Cream? (y/n): ");
        input = reader.readLine();
        if (input.equalsIgnoreCase("y")) {
            latte = new WhippedCream(latte);
        }

        System.out.println("Your final drink: " + latte.getDescription());
        System.out.println("Total price: $" + latte.getPrice());

        reader.readLine();
    }
}
