package cafe.states;

import cafe.core.*;

public class Order {
    private OrderState _state;
    private Basket _basket;
    private OrderManager _manager;
    private Checkout _checkout;

    public Order(OrderManager manager, Basket basket, Checkout checkout) {
        this._manager = manager;
        this._basket = basket;
        this._checkout = checkout;
        this._state = new CreatedState();
    }

    public void setState(OrderState state) {
        this._state = state;
    }

    public OrderState getState() {
        return this._state;
    }

    public Basket getBasket() {
        return this._basket;
    }

    public OrderManager getManager() {
        return this._manager;
    }

    public Checkout getCheckout() {
        return this._checkout;
    }

    public void proceed() {
        this._state.proceed(this);
    }

    public String getStatus() {
        return this._state.getStatus();
    }
}
