package cafe.boxes;

import cafe.drinks.Tea;
import cafe.pastries.BlueberryOatMuffin;

public class HealthyGiftBoxBuilder implements BoxBuilder{
    private GiftBox box;

    @Override
    public void reset() {
        this.box = new GiftBox();
    }

    @Override
    public void addDrink() {
        this.box.addItem(new Tea());
    }

    @Override
    public void addPastry() {
        this.box.addItem(new BlueberryOatMuffin());
    }

    @Override
    public GiftBox getResult() {
        return this.box;
    }
}
