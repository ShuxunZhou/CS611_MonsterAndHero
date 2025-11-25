package legends.items;

import java.util.*;

public class Inventory {
    private List<Item> list = new ArrayList<>();

    public void addItem(Item i) { list.add(i); }
    public void removeItem(Item i) { list.remove(i); }
    public List<Item> getAllItems() { return list; }
}
