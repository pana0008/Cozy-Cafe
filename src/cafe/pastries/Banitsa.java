package cafe.pastries;

import cafe.core.MenuItem;

public class Banitsa implements MenuItem {
    @Override
    public String getDescription() {
        return "Bulgarian Homemade Banitsa";
    }

    @Override
    public double getPrice() {
        return 5.50;
    }
}
