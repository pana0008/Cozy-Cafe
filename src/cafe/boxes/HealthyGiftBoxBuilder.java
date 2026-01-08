package cafe.boxes;

public class HealthyGiftBoxBuilder implements BoxBuilder{
    private GiftBox box;

    @Override
    public void reset() {
        this.box = new GiftBox();
    }

    @Override
    public void addDrink() {
        this.box.addItem("Iced green tea with lemon and honey");
    }

    @Override
    public void addPastry() {
        this.box.addItem("Apple cinnamon oatmeal cookies");
    }

    @Override
    public GiftBox getResult() {
        return this.box;
    }
}
