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
