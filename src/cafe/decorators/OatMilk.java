package cafe.decorators;

import cafe.core.MenuItem;

public class OatMilk extends AddOnDecorator{
    public OatMilk(MenuItem item) {
        super(item);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Oat Milk";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 1.20;
    }
}
