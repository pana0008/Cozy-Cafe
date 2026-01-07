package cafe.decorators;

import cafe.core.MenuItem;

public class WhippedCream extends AddOnDecorator{
    public WhippedCream(MenuItem item) {
        super(item);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Whipped Cream";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 0.50;
    }
}
