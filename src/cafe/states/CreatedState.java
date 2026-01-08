package cafe.states;

import cafe.core.*;

public class CreatedState implements OrderState {
    @Override
    public void proceed(Order order) {
        System.out.println("What would you like to order?");
        order.setState(new AddingItemsState());
    }

    @Override
    public String getStatus() {
        return "Created";
    }
}
