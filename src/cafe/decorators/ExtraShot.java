package cafe.decorators;

import cafe.core.MenuItem;

public class ExtraShot extends AddOnDecorator{
    public ExtraShot(MenuItem item) {
        super(item);
    }

    @Override
    public String getDescription() {
        String base = super.getDescription();
        if (base.contains(" with ")) {
            return base + ", Extra Espresso Shot";
        } else {
            return base + " with Extra Espresso Shot";
        }
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 1.50;
    }
}
