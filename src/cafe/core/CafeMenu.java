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