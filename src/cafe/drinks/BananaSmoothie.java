package cafe.drinks;

import cafe.core.MenuItem;

public class BananaSmoothie implements MenuItem {
    @Override
    public String getDescription() {
        return "Banana Smoothie";
    }

    @Override
    public double getPrice() {
        return 5.50;
    }
}
