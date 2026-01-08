package cafe.discount;

public class SeniorDiscountCalculator implements ICalculateDiscount {
    @Override
    public double getDiscountPrice(double totalPrice, int amountOfItems) {
        return totalPrice * 0.15;
    }
}