package cafe.decorators;

import cafe.core.MenuItem;

public class ExtraShot extends AddOnDecorator{
    public ExtraShot(MenuItem item) {
        super(item);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Extra Espresso Shot";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 1.50;
    }
}
