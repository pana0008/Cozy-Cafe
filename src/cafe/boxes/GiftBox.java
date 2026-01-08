package cafe.boxes;

import cafe.core.MenuItem;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GiftBox implements MenuItem {
    private List<MenuItem> items = new ArrayList<>();

    public void addItem(MenuItem item) {
        items.add(item);
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder("GiftBox:\n");

        for (MenuItem item : items) {
            sb.append(" - ").append(item.getDescription()).append("\n");
        }

        if (!items.isEmpty()) {
            sb.setLength(sb.length() - 1);
        }

        return sb.toString();
    }

    @Override
    public double getPrice() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return total;
    }
}