package cafe.boxes;

public class DessertGiftBoxBuilder implements BoxBuilder {
    private GiftBox box;

    @Override
    public void reset() {
        this.box = new GiftBox();
    }

    @Override
    public void addDrink() {
        this.box.addItem("Hot chocolate mix with marshmallows");
    }

    @Override
    public void addPastry() {
        this.box.addItem("Salted caramel brownies");
    }

    @Override
    public GiftBox getResult() {
        return this.box;
    }
}
