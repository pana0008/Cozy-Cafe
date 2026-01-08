package cafe.states;

public interface OrderState {
    void proceed(Order order);
    String getStatus();
}
