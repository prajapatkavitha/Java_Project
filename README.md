🍽️ Restaurant Menu and Bill Management System (Java)

A simple, modular restaurant management system built in Java. This application lets users manage a menu, place customer orders, and print itemized receipts. Ideal for small restaurants or as a learning project for Java OOP and CLI interaction.

💡 Features

- 🧾 View categorized restaurant menu (Appetizers, Main Courses, etc.)
- ➕ Add new menu items with description, price, and category
- ✏️ Update existing menu items
- 🛒 Create new customer orders with multiple items and quantities
- 🧾 Print detailed, formatted receipts
- 📄 Track all orders placed during the session
- ✅ Uses object-oriented design: `MenuItem`, `Order`, `Restaurant`, etc.

📁 File Structure

├── RestaurantSystem.java    # Main class - entry point of the app
├── MenuItem.java            # Represents individual menu items
├── OrderItem.java           # Represents each line item in an order
├── Order.java               # Represents a full customer order
├── Restaurant.java          # Manages menu and order collections
└── README.md                # Project documentation

🧑‍💻 Technologies Used

- ✅ Java (JDK 8 or higher)
- ✅ Java Collections API (`ArrayList`, `HashMap`)
- ✅ Java Time API (`LocalDateTime`, `DateTimeFormatter`)
- ✅ Console Input/Output
- ✅ OOP: Classes, Objects, Encapsulation

📷 Console Demo (Example)

===== Delicious Bites Management System =====
1. View Menu
2. Add New Menu Item
3. Update Menu Item
4. Create New Order
5. View All Orders
6. Exit

Receipt Sample:
==================================================
                🍽️ Delicious Bites
==================================================
Order #: 1003
Date: 2025-05-04 20:10:22
Customer Name: Sarah Khan
Table #: 7
--------------------------------------------------
Items Ordered:
--------------------------------------------------
1. Chicken Alfredo Pasta     $13.99 x 2 = $27.98
2. Garlic Bread              $4.50  x 1 = $4.50
3. Lemonade                  $3.25  x 2 = $6.50
--------------------------------------------------
Subtotal:                               $38.98
Tax (8%):                               $3.12
--------------------------------------------------
TOTAL:                                  $42.10
==================================================
         ❤️ Thank you for dining with us!
       Visit again for more delicious bites!
==================================================
