package cafe.decorators;

import cafe.core.MenuItem;

public class WhippedCream extends AddOnDecorator{
    public WhippedCream(MenuItem item) {
        super(item);
    }

    @Override
    public String getDescription() {
        String base = super.getDescription();
        if (base.contains(" with ")) {
            return base + ", Whipped Cream";
        } else {
            return base + " with Whipped Cream";
        }
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 0.50;
    }
}
