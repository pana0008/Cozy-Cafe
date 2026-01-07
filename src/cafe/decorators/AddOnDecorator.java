package cafe.decorators;

import cafe.core.MenuItem;

public abstract class AddOnDecorator implements MenuItem {
    private MenuItem _item;

    public AddOnDecorator(MenuItem item) {
        this._item = item;
    }

    @Override
    public String getDescription() {
        return _item.getDescription();
    }

    @Override
    public double getPrice() {
        return _item.getPrice();
    }
}
