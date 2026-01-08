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
  
 - **Client**: `Order Manager` - uses the singleton instance
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
  
 - **Client**: `Order Manager` - associates one of the Builder object with the Director
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

 ##### One concrete builder - HealthyGiftBoxBuilder
  
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
  
### Structural Design Patterns
- ### Decorator
- ### Composite
### Behavioral Design Patterns
- ### Strategy
- ### State


