package cafe.drinks;

import cafe.core.MenuItem;

public class Latte implements MenuItem {

    @Override
    public String getDescription() {
        return "Latte";
    }

    @Override
    public double getPrice() {
        return 3.50;
    }
}
