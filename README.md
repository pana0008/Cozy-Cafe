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

---

### 2. Builder

  #### _Description_
 
  
 - **Client**: `Order Manager` - associates one of the Builder object with the Director
 - **Builder interface**: `BoxBuilder` - declares box construction steps that are common to all types of builders
 - **Concrete Builder1**: `HealthyGiftBoxBuilder` - 
 - **Concrete Builder2**: `DessertGiftBoxBuilder` -  

  #### _Structure of the design pattern_
<img width="2588" height="1736" alt="Builder-CozyCafe" src="https://github.com/user-attachments/assets/5a77b5cd-a9b6-4f9c-8b21-5bc4734e4391" />

---
  
### Structural Design Patterns
- ### Decorator
- ### Composite
### Behavioral Design Patterns
- ### Strategy
- ### State


