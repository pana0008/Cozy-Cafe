package cafe.decorators;

import cafe.core.MenuItem;

public class OatMilk extends AddOnDecorator{
    public OatMilk(MenuItem item) {
        super(item);
    }

    @Override
    public String getDescription() {
        String base = super.getDescription();
        if (base.contains(" with ")) {
            return base + ", Oat Milk";
        } else {
            return base + " with Oat Milk";
        }
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 1.20;
    }
}
