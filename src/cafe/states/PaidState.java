package cafe.states;

public class PaidState implements OrderState {
    @Override
    public void proceed(Order order) {
        System.out.println("\nThank you for visiting the Cozy Cafe!");
    }

    @Override
    public String getStatus() {
        return "Paid";
    }
}
