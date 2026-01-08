package cafe.discount;

public class StudentDiscountCalculator implements ICalculateDiscount {
    @Override
    public double getDiscountPrice(double totalPrice, int amountOfItems) {
        return totalPrice * 0.10;
    }
}
