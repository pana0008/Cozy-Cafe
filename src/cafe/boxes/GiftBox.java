package cafe.boxes;

import java.util.ArrayList;
import java.util.List;

public class GiftBox {
    private final List<String> items = new ArrayList<>();

    public void addItem(String item) {
        items.add(item);
    }

    public String getContents() {
        StringBuilder sb = new StringBuilder("Contains:\n");
        for (String item : items) {
            sb.append(" - ").append(item).append("\n");
        }
        return sb.toString();
    }

    public double getPrice() {
        return 20.00;
    }
}
