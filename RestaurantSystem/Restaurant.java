import java.util.*;

public class Restaurant {
    private String name;
    private List<MenuItem> menu;
    private List<Order> orders;

    public Restaurant(String name) {
        this.name = name;
        this.menu = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addMenuItem(MenuItem item) {
        menu.add(item);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public MenuItem getMenuItemById(int id) {
        for (MenuItem item : menu) {
            if (item.getId() == id) return item;
        }
        return null;
    }

    public void displayMenu() {
        System.out.println("\n===== " + name + " Menu =====");
        System.out.println("ID  | Name                 | Category        | Price   | Description");
        System.out.println("------------------------------------------------------------------------");
        Map<String, List<MenuItem>> categorized = new HashMap<>();
        for (MenuItem item : menu) {
            categorized.computeIfAbsent(item.getCategory(), k -> new ArrayList<>()).add(item);
        }
        List<String> categories = Arrays.asList("Appetizer", "Main Course", "Side", "Dessert", "Beverage");
        for (String cat : categories) {
            List<MenuItem> items = categorized.get(cat);
            if (items != null && !items.isEmpty()) {
                System.out.println("\n" + cat + ":");
                for (MenuItem item : items) {
                    System.out.println(item);
                }
            }
        }
    }

    public void displayAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("\nNo orders available.");
            return;
        }
        System.out.println("\n===== All Orders =====");
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
