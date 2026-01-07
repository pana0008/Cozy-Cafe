package cafe.decorators;

import cafe.core.MenuItem;

public class CaramelSyrup extends AddOnDecorator {
    public CaramelSyrup(MenuItem item) {
        super(item);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Caramel Syrup";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 0.80;
    }
}
