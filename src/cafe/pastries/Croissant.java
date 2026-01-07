package cafe.pastries;

import cafe.core.MenuItem;

public class Croissant implements MenuItem {
    @Override
    public String getDescription() {
        return "Croissant";
    }

    @Override
    public double getPrice() {
        return 2.40;
    }
}
