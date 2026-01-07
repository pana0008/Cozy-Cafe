package cafe.pastries;

import cafe.core.MenuItem;

public class CinnamonRoll implements MenuItem {
    @Override
    public String getDescription() {
        return "Cinnamon Roll";
    }

    @Override
    public double getPrice() {
        return 3.20;
    }
}
