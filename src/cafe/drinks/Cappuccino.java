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
}
