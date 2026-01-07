package cafe.pastries;

import cafe.core.MenuItem;

public class BananaBread implements MenuItem {
    @Override
    public String getDescription() {
        return "Banana Bread";
    }

    @Override
    public double getPrice() {
        return 5.00;
    }
}
