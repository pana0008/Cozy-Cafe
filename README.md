# ˙✧˖°☕ ༘ ⋆｡˚ Zari and Gabby's Cozy Cafe ˙✧˖°☕ ༘ ⋆｡˚
This is a ☕︎ Java console-based application that simulates a cafe ordering system.<br>

The user takes on the role of a customer. They can browse the menu, choose drinks and pastries, customize the drinks with add-ons, select gift boxes and complete an order through a simple text-based interface.

## View the GitHub Repository here!

[Cozy Café Repository](https://github.com/pana0008/Cozy-Cafe)

## The Baristas

This project was brewed by:
- **Svetlozara Kirova** - https://github.com/SvKirova
- **Gabriela Panayotova** - https://github.com/pana0008

## Design Patterns implemented in this project
### _Creational Design Patterns_

### 1. Singleton

  #### _Description_
  The `CafeMenu` class implements the Singleton design pattern. This ensures that the class has only one instance. It provides a global access point to this instance through a private constructor and a static `getInstance()` method. The constructor of the `CafeMenu` class is hidden from the client code ( `OrderManager` class) - calling the `getInstance` method is the only way to get the CafeMenu object. 
  
  This way, all parts of the program work with a consistent, centralized menu - the list of drinks, pastries and addons remains the same everywhere in the application and all orders share the same menu. The class includes the private fields `drinkMenu`, `pastryMenu` and `addonMenu`, which are maps of drink, pastry and add-on options that the customer can choose from. Getters (`getDrinkMenu`, `getPastryMenu`, `getAddonMenu`) are implemented for each map in order to provide controlled access to the menu items (Encapsulation).
  
 - **Client**: `OrderManager` - uses the singleton instance
 - **Singleton**: `CafeMenu` - the centralized menu

  #### _Structure of the design pattern_
<img width="3004" height="1050" alt="Singleton-CozyCafe" src="https://github.com/user-attachments/assets/7c1fc849-0c5a-45c3-a158-4568e5050ddb" />

  #### _Implementation_
  ```java
  package cafe.core;

import cafe.drinks.*;
import cafe.pastries.*;
import cafe.decorators.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class CafeMenu {

    private static CafeMenu instance;

    private final Map<String, Supplier<MenuItem>> drinkMenu = new HashMap<>();
    private final Map<String, Supplier<MenuItem>> pastryMenu = new HashMap<>();
    private final Map<String, Function<MenuItem, MenuItem>> addonMenu = new HashMap<>();

    private CafeMenu() {
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

    public static CafeMenu getInstance() {
        if (instance == null) {
            instance = new CafeMenu();
        }
        return instance;
    }

    public Map<String, Supplier<MenuItem>> getDrinkMenu() {
        return drinkMenu;
    }

    public Map<String, Supplier<MenuItem>> getPastryMenu() {
        return pastryMenu;
    }

    public Map<String, Function<MenuItem, MenuItem>> getAddonMenu() {
        return addonMenu;
    }
}
  ```
---

### 2. Builder

  #### _Description_
  The Builder design pattern allows us to construct complex objects - in our case - `GiftBox` objects. The pattern was used to create different variations of gift boxes (_Dessert box_ and _Healthy Box_), using the same construction steps - `reset()`, `addDrink()`, `addPastry()` and `getResult()`. The `BoxBuilder` interface defines these construction steps and the two concrete builders `HealthyGiftBoxBuilder` and `DessertGiftBoxBuilder` implement the steps for different types of boxes. The `OrderManager` class uses the director and the builder to create a `GiftBox` object. The `Baker` class acts as a Director - it defines the order in which to execute the building steps. This way, we can create different gift box variations.
  
 - **Client**: `OrderManager` - associates one of the Builder object with the Director
 - **Builder interface**: `BoxBuilder` - declares box construction steps that are common to all types of builders
 - **Concrete Builder1**: `HealthyGiftBoxBuilder` - implements the construction steps to create a healthy gift box (includes Tea and Blueberry Oat Muffin)
 - **Concrete Builder2**: `DessertGiftBoxBuilder` -  implements the construction steps to create a dessert gift box (includes Hot Chocolate and Cinnamon Roll)

  #### _Structure of the design pattern_
<img width="2588" height="1736" alt="Builder-CozyCafe" src="https://github.com/user-attachments/assets/5a77b5cd-a9b6-4f9c-8b21-5bc4734e4391" />

  #### _Implementation_
  ##### The Builder interface - BoxBuilder interface
  ```java
package cafe.boxes;

public interface BoxBuilder {
    void reset();
    void addDrink();
    void addPastry();
    GiftBox getResult();
}
  ```
  ##### The Director - Baker
  
  ```java
  package cafe.boxes;

public class Baker {

    private BoxBuilder _builder;

    public Baker(BoxBuilder builder) {
        this._builder = builder;
    }

    public void prepareGiftBox() {
        this._builder.reset();
        this._builder.addDrink();
        this._builder.addPastry();
    }
}
  ```

 ##### One of the Concrete Builders - HealthyGiftBoxBuilder
  
  ```java
package cafe.boxes;

import cafe.drinks.Tea;
import cafe.pastries.BlueberryOatMuffin;

public class HealthyGiftBoxBuilder implements BoxBuilder{
    private GiftBox box;

    @Override
    public void reset() {
        this.box = new GiftBox();
    }

    @Override
    public void addDrink() {
        this.box.addItem(new Tea());
    }

    @Override
    public void addPastry() {
        this.box.addItem(new BlueberryOatMuffin());
    }

    @Override
    public GiftBox getResult() {
        return this.box;
    }
}
```
---
  
### _Structural Design Patterns_
### 1. Decorator

  #### _Description_
  The Decorator design pattern lets us wrap a base object with additional behavior. All the classes `BananaSmoothie`, `Tea`, `HotChocolate`, `Cappuccino` and `Latte` are Concrete Components that implement the Component interface (in our case, the `MenuItem` interface). These are the classes of objects being wrapped by the decorators. 
  
The `AddOnDecorator` class is the Base Decorator - it has a field for referencing a wrapped object of type `MenuItem`. This Base Decorator delegates all operations to the wrapped object. We created four Concrete Decorators - `Caramel Syrup`, `ExtraShot`, `OatMilk` and `WhippedCream`. They override the methods of the Base Decorator (`AddOnDecorator`) in order to add their own behavior (modifying the description and price). This way, the customer can order drinks with different combinations of add-ons, wihtout creating a separate class for each combination.

 - **Client**: `OrderManager` - applies decorators to `MenuItem` objects based on the customer choices
 - **Component interface**: `MenuItem` - declares the common interface for both wrappers and wrapped objects
 - **Concrete Components**: `BananaSmoothie`, `Tea`, `HotChocolate`, `Cappuccino`, `Latte` - drink objects that implement `MenuItem` and can be wrapped by the decorators
 - **Base Decorator**: `AddOnDecorator` - provides the base functionality for all add-on decorators
 - **Concrete Decorators**: `Caramel Syrup`, `ExtraShot`, `OatMilk`, `WhippedCream` - add extra description and cost to a drink
  
  #### _Structure of the design pattern_
<img width="6221" height="2629" alt="Decorator-CozyCafe" src="https://github.com/user-attachments/assets/e0d38ef6-9fcb-48ef-87b1-b9731528b5e0" />

  #### _Implementation_
  ##### Component interface - MenuItem interface
  ```java
package cafe.core;

public interface MenuItem {
    String getDescription();
    double getPrice();
}
  ```

  ##### Base Decorator - AddOnDecorator
  ```java
package cafe.decorators;

import cafe.core.MenuItem;

public abstract class AddOnDecorator implements MenuItem {
    private MenuItem _item;

    public AddOnDecorator(MenuItem item) {
        this._item = item;
    }

    @Override
    public String getDescription() {
        return _item.getDescription();
    }

    @Override
    public double getPrice() {
        return _item.getPrice();
    }
}
  ```

  ##### One of the Concrete Decorators - AddOnDecorator
  ```java
package cafe.decorators;

import cafe.core.MenuItem;

public class CaramelSyrup extends AddOnDecorator {
    public CaramelSyrup(MenuItem item) {
        super(item);
    }

    @Override
    public String getDescription() {
        String base = super.getDescription();
        if (base.contains(" with ")) {
            return base + ", Caramel Syrup";
        } else {
            return base + " with Caramel Syrup";
        }
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 0.80;
    }
}
  ```

##### One of the Concrete Components - HotChocolate
  ```java
package cafe.drinks;

import cafe.core.MenuItem;

public class HotChocolate implements MenuItem {
    @Override
    public String getDescription() {
        return "Hot Chocolate";
    }

    @Override
    public double getPrice() {
        return 3.20;
    }
}
  ```
### 2. Composite

  #### _Description_
 In this project, both individual menu items (drinks and pastries) and composite objects (`GiftBox`) implement the `MenuItem` interface. This allows us to work with an item or a gift box including multiple items using the same operations (`getDescription()` and `getPrice()`). The `GiftBox` class acts as a Composite that groups menu items together. All drinks and pastries (`BananaSmoothie`, `Tea`, `HotChocolate`, `Cappuccino`, `Latte`, `BananaBread`, `CinnamonRoll`, `Banitsa`, `BlueberryOatMuffin` and `Croissant`) act as leaves. The main benefit of this approach is that we don't need to care about the concrete classes of objects that compose the tree. We don't need to know whether an object is a simple menu item or a complex gift box - we treat them all the same through the common interface. When we call a method, the objects themselves pass the request down the tree.

 - **Client**: `OrderManager` - creates and works with individual menu items and composite gift boxes; interacts with all elements through the component interface
 - **Component interface**: `MenuItem` - describes operations that are common to both simple (the leaves) and complex elements (the composite gift boxes) of the tree
 - **Composite**: `GiftBox` - the element that has sub-elements (leaves); groups multiple `MenuItem` objects
 - **Leaves**: `BananaSmoothie`, `Tea`, `HotChocolate`, `Cappuccino`, `Latte`, `BananaBread`, `CinnamonRoll`, `Banitsa`, `BlueberryOatMuffin`, `Croissant` - individual menu items that have no sub-elements; basic elements of the tree

  #### _Structure of the design pattern_
  <img width="4838" height="1841" alt="Composite-CozyCafe" src="https://github.com/user-attachments/assets/2d7b46dc-daa4-468d-9c16-a64509db63c3" />

  #### _Implementation_
  ##### Component interface - MenuItem interface
  ```java
package cafe.core;

public interface MenuItem {
    String getDescription();
    double getPrice();
}
  ```
##### Composite - GiftBox
  ```java
package cafe.boxes;

import cafe.core.MenuItem;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GiftBox implements MenuItem {
    private List<MenuItem> items = new ArrayList<>();

    public void addItem(MenuItem item) {
        items.add(item);
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder("GiftBox:\n");

        for (MenuItem item : items) {
            sb.append(" - ").append(item.getDescription()).append("\n");
        }

        if (!items.isEmpty()) {
            sb.setLength(sb.length() - 1);
        }

        return sb.toString();
    }

    @Override
    public double getPrice() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return total;
    }
}
  ```
##### One of the leaves - Cappuccino
  ```java
package cafe.drinks;

import cafe.core.MenuItem;

public class Cappuccino implements MenuItem {
    @Override
    public String getDescription() {
        return "Cappuccino";
    }

    @Override
    public double getPrice() {
        return 3.00;
    }
  ```

  ---
  
### Behavioral Design Patterns
### 1. Strategy

  #### _Description_
The discount system in our application implements the Strategy design pattern. This allows the program to apply different discount calculations based on the customer type.

The `ICalculateDiscount` interface defines the operation `getDiscountPrice()`, which calculates a  discount based on the total price and the number of items. The three concrete strategies - `NullDiscountCalculator`, `StudentDiscountCalculator` and `SeniorDiscountCalculator`, implement this interface to provide no discount, a 10% student discount or a 15% senior discount. The `Checkout` class acts as the Context. It holds a reference to an `ICalculateDiscount` strategy  and delegates the discount calculation to it. The strategy can be changed dynamically using the `setDiscountCalculator()` method. The `CheckoutState` class acts as the Client. It asks the customer for their discount type and selects the appropriate strategy, passing it to the `Checkout` context.

This design allows adding new discount types in the future without changing the `Checkout` class. We can just implement a new strategy and pass it to the context.

 - **Client**: `CheckoutState` - selects and sets the discount strategy
 - **Strategy interface**: `ICalculateDiscount` - declares the discount calculation method
 - **Concrete Strategy1**: `NullDiscountCalculator` - no discount applied 
 - **Concrete Strategy2**: `StudentDiscountCalculator` - applies 10% discount
 - **Concrete Strategy3**: `SeniorDiscountCalculator` - applies 15% discount  
 - **Context**: `Checkout` - uses the selected strategy to calculate the final total  

  #### _Structure of the design pattern_
  <img width="4575" height="1504" alt="Strategy-CozyCafe" src="https://github.com/user-attachments/assets/faeb7071-af44-469c-a83d-f36175d40262" />

  #### _Implementation_
  ##### Strategy interface - ICalculateDiscount interface
  ```java
package cafe.discount;

public interface ICalculateDiscount {
    double getDiscountPrice(double totalPrice, int amountOfItems);
}

  ```
  ##### Context - Checkout
  ```java
package cafe.core;

import cafe.discount.ICalculateDiscount;
import cafe.discount.NullDiscountCalculator;

public class Checkout {
    private ICalculateDiscount _discountCalculator;

    public Checkout() {
        _discountCalculator = new NullDiscountCalculator();
    }

    public void setDiscountCalculator(ICalculateDiscount discountCalc) {
        this._discountCalculator = discountCalc;
    }

    public double calculateTotal(Basket basket) {
        double priceToBePaid = basket.getTotalPrice();
        int amountOfItems = basket.getTotalAmount();
        double discountPrice = this._discountCalculator.getDiscountPrice(priceToBePaid, amountOfItems);
        return priceToBePaid - discountPrice;
    }
}

  ```

  ##### Client - CheckoutState
  ```java
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

  ```

  ##### One of the Concrete Strategies - StudentDiscountCalculator
  ```java
package cafe.discount;

public class StudentDiscountCalculator implements ICalculateDiscount {
    @Override
    public double getDiscountPrice(double totalPrice, int amountOfItems) {
        return totalPrice * 0.10;
    }
}

  ```
### 2. State

  #### _Description_
The order process in our application implements the State design pattern. This pattern allows an object to change its behaviour when its internal state changes. In our case, the behaviour of an order depends on its current state in the ordering process.

The `OrderState` interface defines the common methods that all order states must have: proceed() and getStatus(). Each concrete state represents one step of the order process and decides what happens during that step. The `Order` class acts as the Context. It keeps track of the current state of the order and calls the `proceed()` method on that state. The `Order` class does not need to know which state is active - it simply delegates the work to the current state. The concrete states `CreatedState`, `AddingItemsState`, `CheckoutState` and `PaidState` each implement the `OrderState` interface. Each state controls the behaviour of the order for that step and decides when to move the order to the next state. The `Main` class acts as the Client. It creates the `Order` object and repeatedly calls `proceed()` until the order reaches the final `Paid` state.

The design makes the order process to change and extend. New states can be added without changing the existing order logic or the client code.

 **Client**: `CheckoutState` - creates the order and triggers state transitions
 - **State interface**: `OrderState` - declares the common state operations
 - **Concrete State1**: `CreatedState` - starts the order
 - **Concrete State2**: `AddingItemsState` - allows items and gift boxes to be added
 - **Concrete State3**: `CheckoutState` - applies discounts and prints the receipt 
 - **Concrete State4**: `PaidState` - final state that completes the order  
 - **Context**: `Order` - maintains the current state and delegates behaviour to it  

  #### _Structure of the design pattern_
<img width="6338" height="2066" alt="State-CozyCafe" src="https://github.com/user-attachments/assets/18e00314-f000-451b-ac5c-49acaac352a0" />

  #### _Implementation_
  ##### State interface - OrderState 
  ```java
  package cafe.states;

public interface OrderState {
    void proceed(Order order);
    String getStatus();
}
  ```
  ##### Context - Order
  ```java
package cafe.states;

import cafe.core.*;

public class Order {
    private OrderState _state;
    private Basket _basket;
    private OrderManager _manager;
    private Checkout _checkout;

    public Order(OrderManager manager, Basket basket, Checkout checkout) {
        this._manager = manager;
        this._basket = basket;
        this._checkout = checkout;
        this._state = new CreatedState();
    }

    public void setState(OrderState state) {
        this._state = state;
    }

    public OrderState getState() {
        return this._state;
    }

    public Basket getBasket() {
        return this._basket;
    }

    public OrderManager getManager() {
        return this._manager;
    }

    public Checkout getCheckout() {
        return this._checkout;
    }

    public void proceed() {
        this._state.proceed(this);
    }

    public String getStatus() {
        return this._state.getStatus();
    }
}

  ```
  ##### Client - Main
  ```java
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
  ```
  ##### One of the Concrete States - AddingItemsState
  ```java
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
  ```
