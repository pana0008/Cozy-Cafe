package cafe.core;

import cafe.discount.ICalculateDiscount;
import cafe.discount.NullDiscountCalculator;

public class Checkout {
    private ICalculateDiscount _discountCalculator;

    public Checkout() {
        _discountCalculator = new NullDiscountCalculator();
    }

    public void setDiscountCalculator(ICalculateDiscount discountCalc) {
        this._discountCalculator = discountCalc;
    }

    public double calculateTotal(Basket basket) {
        double priceToBePaid = basket.getTotalPrice();
        int amountOfItems = basket.getTotalAmount();
        double discountPrice = this._discountCalculator.getDiscountPrice(priceToBePaid, amountOfItems);
        return priceToBePaid - discountPrice;
    }
}
