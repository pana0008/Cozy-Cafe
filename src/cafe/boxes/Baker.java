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

    public void makeCustomBox(int drinks, int pastries) {
        this._builder.reset();
        for (int i = 0; i < drinks; i++) {
            this._builder.addDrink();
        }
        for (int i = 0; i < pastries; i++) {
            this._builder.addPastry();
        }
    }
}
