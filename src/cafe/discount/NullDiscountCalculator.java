package cafe.discount;

public class NullDiscountCalculator implements ICalculateDiscount {
    @Override
    public double getDiscountPrice(double totalPrice, int amountOfItems) {
        return 0;
    }
}
