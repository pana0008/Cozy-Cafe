package cafe.pastries;

import cafe.core.MenuItem;

public class BlueberryOatMuffin implements MenuItem {
    @Override
    public String getDescription() {
        return "Blueberry Oat Muffin";
    }

    @Override
    public double getPrice() {
        return 4.30;
    }
}
