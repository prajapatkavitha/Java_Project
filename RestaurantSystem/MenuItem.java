import java.util.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RestaurantSystem {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant("Delicious Bites");
        
        // Add some menu items
        restaurant.addMenuItem(new MenuItem(1, "Margherita Pizza", "Classic cheese and tomato pizza", 12.99, "Main Course"));
        restaurant.addMenuItem(new MenuItem(2, "Caesar Salad", "Fresh romaine lettuce with Caesar dressing", 8.99, "Appetizer"));
        restaurant.addMenuItem(new MenuItem(3, "Spaghetti Carbonara", "Creamy pasta with bacon", 14.99, "Main Course"));
        restaurant.addMenuItem(new MenuItem(4, "Chocolate Cake", "Rich chocolate layer cake", 6.99, "Dessert"));
        restaurant.addMenuItem(new MenuItem(5, "Garlic Bread", "Toasted bread with garlic butter", 4.99, "Side"));
        restaurant.addMenuItem(new MenuItem(6, "Soft Drink", "Cola, Sprite, or Fanta", 2.99, "Beverage"));
        restaurant.addMenuItem(new MenuItem(7, "Tiramisu", "Italian coffee-flavored dessert", 7.99, "Dessert"));
        restaurant.addMenuItem(new MenuItem(8, "Chicken Wings", "Spicy buffalo wings", 9.99, "Appetizer"));
        
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            System.out.println("\n===== " + restaurant.getName() + " Management System =====");
            System.out.println("1. View Menu");
            System.out.println("2. Add New Menu Item");
            System.out.println("3. Update Menu Item");
            System.out.println("4. Create New Order");
            System.out.println("5. View All Orders");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput(scanner);
            
            switch (choice) {
                case 1:
                    restaurant.displayMenu();
                    break;
                case 2:
                    addNewMenuItem(scanner, restaurant);
                    break;
                case 3:
                    updateMenuItem(scanner, restaurant);
                    break;
                case 4:
                    createNewOrder(scanner, restaurant);
                    break;
                case 5:
                    restaurant.displayAllOrders();
                    break;
                case 6:
                    running = false;
                    System.out.println("Thank you for using the Restaurant Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
    
    private static int getIntInput(Scanner scanner) {
        int input = 0;
        try {
            input = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear the buffer
            return -1; // Invalid input
        }
        return input;
    }
    
    private static void addNewMenuItem(Scanner scanner, Restaurant restaurant) {
        System.out.println("\n===== Add New Menu Item =====");
        
        System.out.print("Enter item ID: ");
        int id = getIntInput(scanner);
        
        // Check if ID already exists
        if (restaurant.getMenuItemById(id) != null) {
            System.out.println("An item with this ID already exists. Please choose a different ID.");
            return;
        }
        
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter item description: ");
        String description = scanner.nextLine();
        
        System.out.print("Enter item price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Clear the buffer
        
        System.out.print("Enter item category (Appetizer, Main Course, Dessert, Beverage, Side): ");
        String category = scanner.nextLine();
        
        MenuItem newItem = new MenuItem(id, name, description, price, category);
        restaurant.addMenuItem(newItem);
        System.out.println("Item added successfully!");
    }
    
    private static void updateMenuItem(Scanner scanner, Restaurant restaurant) {
        System.out.println("\n===== Update Menu Item =====");
        restaurant.displayMenu();
        
        System.out.print("Enter the ID of the item you want to update: ");
        int id = getIntInput(scanner);
        
        MenuItem item = restaurant.getMenuItemById(id);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        
        System.out.println("Current item details:");
        System.out.println(item);
        
        System.out.println("\nWhat would you like to update?");
        System.out.println("1. Name");
        System.out.println("2. Description");
        System.out.println("3. Price");
        System.out.println("4. Category");
        System.out.print("Enter your choice (1-4): ");
        
        int choice = getIntInput(scanner);
        
        switch (choice) {
            case 1:
                System.out.print("Enter new name: ");
                item.setName(scanner.nextLine());
                break;
            case 2:
                System.out.print("Enter new description: ");
                item.setDescription(scanner.nextLine());
                break;
            case 3:
                System.out.print("Enter new price: ");
                item.setPrice(scanner.nextDouble());
                scanner.nextLine(); // Clear the buffer
                break;
            case 4:
                System.out.print("Enter new category: ");
                item.setCategory(scanner.nextLine());
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        
        System.out.println("Item updated successfully!");
    }
    
    private static void createNewOrder(Scanner scanner, Restaurant restaurant) {
        System.out.println("\n===== Create New Order =====");
        
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        
        System.out.print("Enter table number: ");
        int tableNumber = getIntInput(scanner);
        
        Order order = new Order(customerName, tableNumber);
        
        boolean addingItems = true;
        while (addingItems) {
            restaurant.displayMenu();
            
            System.out.print("Enter the ID of item to add (0 to finish): ");
            int itemId = getIntInput(scanner);
            
            if (itemId == 0) {
                addingItems = false;
                continue;
            }
            
            MenuItem item = restaurant.getMenuItemById(itemId);
            if (item == null) {
                System.out.println("Item not found. Please try again.");
                continue;
            }
            
            System.out.print("Enter quantity: ");
            int quantity = getIntInput(scanner);
            
            if (quantity <= 0) {
                System.out.println("Quantity must be greater than 0.");
                continue;
            }
            
            order.addItem(item, quantity);
            System.out.println(quantity + " x " + item.getName() + " added to order.");
            
            System.out.print("Add another item? (Y/N): ");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("Y")) {
                addingItems = false;
            }
        }
        
        restaurant.addOrder(order);
        System.out.println("\nOrder created successfully!");
        System.out.println("Order #" + order.getOrderId() + " - Total: $" + String.format("%.2f", order.calculateTotal()));
        
        // Print receipt
        System.out.println("\nWould you like to print the receipt? (Y/N): ");
        String printReceipt = scanner.nextLine();
        if (printReceipt.equalsIgnoreCase("Y")) {
            order.printReceipt(restaurant.getName());
        }
    }
}

class MenuItem {
    private int id;
    private String name;
    private String description;
    private double price;
    private String category;
    
    public MenuItem(int id, String name, String description, double price, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
    
    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    
    // Setters
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }
    public void setCategory(String category) { this.category = category; }
    
    @Override
    public String toString() {
        return String.format("%-3d | %-20s | %-15s | $%-6.2f | %s", 
                id, name, category, price, description);
    }
}

class OrderItem {
    private MenuItem item;
    private int quantity;
    
    public OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
    
    public MenuItem getItem() { return item; }
    public int getQuantity() { return quantity; }
    public double getSubtotal() { return item.getPrice() * quantity; }
    
    @Override
    public String toString() {
        return String.format("%-20s x %d = $%.2f", item.getName(), quantity, getSubtotal());
    }
}

class Order {
    private static int nextOrderId = 1001;
    
    private int orderId;
    private String customerName;
    private int tableNumber;
    private List<OrderItem> items;
    private LocalDateTime orderTime;
    private final double TAX_RATE = 0.08; // 8% tax
    
    public Order(String customerName, int tableNumber) {
        this.orderId = nextOrderId++;
        this.customerName = customerName;
        this.tableNumber = tableNumber;
        this.items = new ArrayList<>();
        this.orderTime = LocalDateTime.now();
    }
    
    public void addItem(MenuItem item, int quantity) {
        OrderItem orderItem = new OrderItem(item, quantity);
        items.add(orderItem);
    }
    
    public double calculateSubtotal() {
        double subtotal = 0;
        for (OrderItem item : items) {
            subtotal += item.getSubtotal();
        }
        return subtotal;
    }
    
    public double calculateTax() {
        return calculateSubtotal() * TAX_RATE;
    }
    
    public double calculateTotal() {
        return calculateSubtotal() + calculateTax();
    }
    
    public int getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public int getTableNumber() { return tableNumber; }
    public List<OrderItem> getItems() { return items; }
    public LocalDateTime getOrderTime() { return orderTime; }
    
    public void printReceipt(String restaurantName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        System.out.println("\n==================================================");
        System.out.println("                " + restaurantName);
        System.out.println("==================================================");
        System.out.println("Order #: " + orderId);
        System.out.println("Date: " + orderTime.format(formatter));
        System.out.println("Customer: " + customerName);
        System.out.println("Table #: " + tableNumber);
        System.out.println("--------------------------------------------------");
        System.out.println("ITEMS");
        System.out.println("--------------------------------------------------");
        
        for (OrderItem item : items) {
            MenuItem menuItem = item.getItem();
            System.out.printf("%-25s $%.2f x %d = $%.2f\n", 
                   menuItem.getName(), menuItem.getPrice(), item.getQuantity(), item.getSubtotal());
        }
        
        System.out.println("--------------------------------------------------");
        System.out.printf("Subtotal:                             $%.2f\n", calculateSubtotal());
        System.out.printf("Tax (%.0f%%):                            $%.2f\n", TAX_RATE * 100, calculateTax());
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

class Restaurant {
    private String name;
    private List<MenuItem> menu;
    private List<Order> orders;
    
    public Restaurant(String name) {
        this.name = name;
        this.menu = new ArrayList<>();
        this.orders = new ArrayList<>();
    }
    
    public void addMenuItem(MenuItem item) {
        menu.add(item);
    }
    
    public void addOrder(Order order) {
        orders.add(order);
    }
    
    public void displayMenu() {
        System.out.println("\n===== " + name + " Menu =====");
        System.out.println("ID  | Name                 | Category        | Price   | Description");
        System.out.println("------------------------------------------------------------------------");
        
        // Group items by category
        Map<String, List<MenuItem>> itemsByCategory = new HashMap<>();
        for (MenuItem item : menu) {
            String category = item.getCategory();
            if (!itemsByCategory.containsKey(category)) {
                itemsByCategory.put(category, new ArrayList<>());
            }
            itemsByCategory.get(category).add(item);
        }
        
        // Display items by category
        List<String> categories = Arrays.asList("Appetizer", "Main Course", "Side", "Dessert", "Beverage");
        
        for (String category : categories) {
            List<MenuItem> items = itemsByCategory.get(category);
            if (items != null && !items.isEmpty()) {
                System.out.println("\n" + category + ":");
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
    
    public MenuItem getMenuItemById(int id) {
        for (MenuItem item : menu) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
    
    public String getName() {
        return name;
    }
}
