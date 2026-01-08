package cafe.boxes;

import cafe.drinks.HotChocolate;
import cafe.pastries.CinnamonRoll;

public class DessertGiftBoxBuilder implements BoxBuilder {
    private GiftBox box;

    @Override
    public void reset() {
        this.box = new GiftBox();
    }

    @Override
    public void addDrink() {
        this.box.addItem(new HotChocolate());
    }

    @Override
    public void addPastry() {
        this.box.addItem(new CinnamonRoll());
    }

    @Override
    public GiftBox getResult() {
        return this.box;
    }
}
