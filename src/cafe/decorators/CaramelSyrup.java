package cafe.decorators;

import cafe.core.MenuItem;

public class CaramelSyrup extends AddOnDecorator {
    public CaramelSyrup(MenuItem item) {
        super(item);
    }

    @Override
    public String getDescription() {
        String base = super.getDescription();
        if (base.contains(" with ")) {
            return base + ", Caramel Syrup";
        } else {
            return base + " with Caramel Syrup";
        }
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 0.80;
    }
}
