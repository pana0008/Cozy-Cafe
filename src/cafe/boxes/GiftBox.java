package cafe.boxes;

import cafe.core.MenuItem;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GiftBox implements MenuItem {
    private List<String> items = new ArrayList<>();

    public void addItem(String item) {
        items.add(item);
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder("GiftBox:\n");
        for (String item : items) {
            sb.append(" - ").append(item).append("\n");
        }
        if (!sb.isEmpty()) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    @Override
    public double getPrice() {
        return 20.00;
    }
}
