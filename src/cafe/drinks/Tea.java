package cafe.drinks;

import cafe.core.MenuItem;

public class Tea implements MenuItem {
    @Override
    public String getDescription() {
        return "Tea";
    }

    @Override
    public double getPrice() {
        return 2.50;
    }
}
