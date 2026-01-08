package cafe.core;

import cafe.core.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<MenuItem> _items;

    public Basket() {
        this._items = new ArrayList<MenuItem>();
    }

    public void addProduct(MenuItem item) {
        this._items.add(item);
    }

    public int getTotalAmount() {
        return this._items.size();
    }

    public String getItemsDescription() {
        StringBuilder sb = new StringBuilder();
        for (MenuItem item : this._items) {
            sb.append("- ");
            sb.append(item.getDescription().replace("\n", "\n  "));
            sb.append("\n");
        }
        return sb.toString();
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for(MenuItem item: this._items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
}
