package cafe.boxes;

public interface BoxBuilder {
    void reset();
    void addDrink();
    void addPastry();
    GiftBox getResult();
}
