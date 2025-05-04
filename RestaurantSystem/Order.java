import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Order {
    private static int nextOrderId = 1001;

    private int orderId;
    private String customerName;
    private int tableNumber;
    private List<OrderItem> items;
    private LocalDateTime orderTime;
    private final double TAX_RATE = 0.08;

    public Order(String customerName, int tableNumber) {
        this.orderId = nextOrderId++;
        this.customerName = customerName;
        this.tableNumber = tableNumber;
        this.items = new ArrayList<>();
        this.orderTime = LocalDateTime.now();
    }

    public void addItem(MenuItem item, int quantity) {
        items.add(new OrderItem(item, quantity));
    }

    public double calculateSubtotal() {
        return items.stream().mapToDouble(OrderItem::getSubtotal).sum();
    }

    public double calculateTax() {
        return calculateSubtotal() * TAX_RATE;
    }

    public double calculateTotal() {
        return calculateSubtotal() + calculateTax();
    }

    public int getOrderId() { return orderId; }

    public void printReceipt(String restaurantName) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("\n==================================================");
        System.out.println("                " + restaurantName);
        System.out.println("==================================================");
        System.out.println("Order #: " + orderId);
        System.out.println("Date: " + orderTime.format(fmt));
        System.out.println("Customer: " + customerName);
        System.out.println("Table #: " + tableNumber);
        System.out.println("--------------------------------------------------");
        for (OrderItem item : items) {
            System.out.printf("%-25s $%.2f x %d = $%.2f\n",
                    item.getItem().getName(), item.getItem().getPrice(),
                    item.getQuantity(), item.getSubtotal());
        }
        System.out.println("--------------------------------------------------");
        System.out.printf("Subtotal:                             $%.2f\n", calculateSubtotal());
        System.out.printf("Tax (8%%):                             $%.2f\n", calculateTax());
        System.out.printf("TOTAL:                                $%.2f\n", calculateTotal());
        System.out.println("==================================================");
        System.out.println("          Thank you for dining with us!");
        System.out.println("==================================================");
    }

    @Override
    public String toString() {
        return String.format("Order #%d - Table %d - %s - Total: $%.2f",
                orderId, tableNumber, customerName, calculateTotal());
    }
}
