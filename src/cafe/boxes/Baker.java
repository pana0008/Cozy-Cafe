package cafe.boxes;

public class Baker {

    private BoxBuilder _builder;

    public Baker(BoxBuilder builder) {
        this._builder = builder;
    }

    public void prepareGiftBox() {
        this._builder.reset();
        this._builder.addDrink();
        this._builder.addPastry();
    }
}
