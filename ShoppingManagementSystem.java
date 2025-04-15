package Shopping;
import java.io.*;
import java.util.*;

public class ShoppingManagementSystem {

//colour_style
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String RED = "\u001B[31m";
    public static final String CYAN = "\u001B[36m";
    public static final String YELLOW = "\u001B[33m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
	
    
 // Main- 28
  //mainMenue- 39
  //registerUser- 68
  //loginUser-88
  //registerAdmin- 133
  //loginAdmin- 140
  //adminPanel- 160
  //customerPanel- 210
  //view products- 273
  //getProduct- 288
  //loadProducts- 300
  //saveProducts- 316
  //loadUser- 331
  //PrintTittle – 347
  //print Main_menue- 361
  //animeted_print- 387
  //OrderCart- 403
  //PaymentMethods- 450
  //view Orders – 474
  //UpdateProduct – 498
  //updateStock – 549
  //Tittle3- 563
  //clear_consol- 590
  //title2()-600
  //saveUser - 642

	
    static Scanner scanner = new Scanner(System.in);
    static List<Product> products = new ArrayList<>();
    static List<User> users = new ArrayList<>();
    static final String PRODUCT_FILE = "products.txt";
    static final String USER_FILE = "users.txt";

    
    
    public static void main(String[] args) {
    	//printTitle() ;
    	Title3();
        loadProducts();
        loadUsers();
        mainMenu();
       /// Main_Menu();
    }

    
    
    static void mainMenu() {
        while (true) {
        	//printTitle() ;
        	printMainMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> registerUser();
                case 2 -> loginUser();
                case 3 -> registerAdmin();
                case 4 -> loginAdmin();
                
                case 5 -> {
                    saveProducts();
                    saveUsers();
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
    

    
    

    static void registerUser() {
        scanner.nextLine(); 

        System.out.print(CYAN + "👤 Enter username: " + RESET);
        String user = scanner.nextLine();

        System.out.print(CYAN + "🔐 Enter password: " + RESET);
        String pass = scanner.nextLine();

        System.out.print(YELLOW + "✅ Enter Phone: " + RESET);
        String role = scanner.nextLine();

        users.add(new User(user, pass, role));
        saveUsers();

        System.out.println(GREEN + "✔ User registered successfully!" + RESET);
    }
    
    

    static void loginUser() {
        scanner.nextLine(); // Clear buffer

        System.out.print(CYAN + "👤 Enter username: " + RESET);
        String user = scanner.nextLine();

        System.out.print(CYAN + "🔐 Enter password: " + RESET);
        String pass = scanner.nextLine();

        for (User u : users) {
            if (u.username.equals(user) && u.password.equals(pass)) {
                System.out.println(GREEN + "✔ Login successful as " + u.role + RESET);
                // if (u.role.equalsIgnoreCase("admin")) adminPanel();
                customerPanel();
                return;
            }
        }

        System.out.println(RED + "✖ Invalid credentials. Please try again!" + RESET);
    }
    
    

    public static void registerAdmin() {
        System.out.println(RED + BOLD + "⚠ You can't directly register as Admin." + RESET);
        System.out.println(YELLOW + "📞 Please contact the main administrator for access." + RESET);
    }

    
    
    public static void loginAdmin() {
        System.out.print(CYAN + "👤 Enter admin username: " + RESET);
        String username = scanner.nextLine();

        System.out.print(CYAN + "🔐 Enter admin password: " + RESET);
        String password = scanner.nextLine();

        if (username.equals("shovon") && password.equals("shovon")) {
            System.out.println(GREEN + "✔ Admin login successful!" + RESET);
            adminPanel();
            return;
        }

        System.out.println(RED + "✖ Invalid admin credentials." + RESET);
    }


    
    

    static void adminPanel() {
        while (true) {
            System.out.println(BLUE + "\n--- Admin Panel ---" + RESET);
            System.out.println(CYAN + "1. Add Product" + RESET);
            System.out.println(CYAN + "2. View Products" + RESET);
            System.out.println(CYAN + "3. Delete Product" + RESET);
            System.out.println(CYAN + "4. View All Orders" + RESET);
           
            System.out.println(CYAN + "5. Update Product" + RESET);
            System.out.println(CYAN + "6. Logout" + RESET);
            System.out.print(YELLOW + "Choice: " + RESET);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    scanner.nextLine();
                    System.out.print(PURPLE + "Name: " + RESET);
                    String name = scanner.nextLine();
                    System.out.print(PURPLE + "Price: " + RESET);
                    double price = scanner.nextDouble();
                    System.out.print(PURPLE + "Quantity: " + RESET);
                    int qty = scanner.nextInt();
                    int id = products.size() + 1;
                    products.add(new Product(id, name, price, qty));
                    saveProducts();
                    System.out.println(GREEN + "Product added." + RESET);
                }
                case 2 -> viewProducts();
                case 3 -> {
                    System.out.print(RED + "Enter product ID to delete: " + RESET);
                    int id = scanner.nextInt();
                    products.removeIf(p -> p.id == id);
                    saveProducts();
                    System.out.println(GREEN + "Deleted." + RESET);
                }
                case 4 -> viewOrders();
                case 5 ->  updateProduct();
                case 6 -> {
                    System.out.println(YELLOW + "Logging out from Admin Panel..." + RESET);
                    title2();
                    return;
                }
                default -> System.out.println(RED + "Invalid option." + RESET);
            }
        }
    }

    
    
    
    
    static void customerPanel() {
        List<Product> cart = new ArrayList<>();

        while (true) {
            System.out.println(CYAN + "\n--- Customer Panel ---" + RESET);
            System.out.println(YELLOW + "1. View Products" + RESET);
            System.out.println(YELLOW + "2. Add to Cart" + RESET);
            System.out.println(YELLOW + "3. View Cart" + RESET);
            System.out.println(YELLOW + "4. Place Order" + RESET);
            System.out.println(YELLOW + "5. Logout" + RESET);
            System.out.print(MAGENTA + "Choice: " + RESET);

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> viewProducts();
                case 2 -> {
                    System.out.print(BLUE + "Enter product ID: " + RESET);
                    int id = scanner.nextInt();
                    Product prod = getProductById(id);
                    if (prod != null) {
                        System.out.print(BLUE + "Enter quantity: " + RESET);
                        int qty = scanner.nextInt();
                        if (qty <= prod.quantity) {
                            cart.add(new Product(prod.id, prod.name, prod.price, qty));
                            System.out.println(GREEN + "✔ Added to cart." + RESET);
                        } else {
                            System.out.println(RED + "✖ Not enough stock." + RESET);
                        }
                    } else {
                        System.out.println(RED + "✖ Product not found." + RESET);
                    }
                }
                case 3 -> {
                    double total = 0;
                    System.out.println(BOLD + "\n🛒 Cart Items:" + RESET);
                    for (Product p : cart) {
                        System.out.printf(YELLOW + "%s x%d = $%.2f\n" + RESET, p.name, p.quantity, p.price * p.quantity);
                        total += p.price * p.quantity;
                    }
                    System.out.println(GREEN + "Total: $" + total + RESET);
                }
                
                case 4 -> {
                	orderCart(cart);
                    
                    cart.clear();
                }
                case 5 -> {
                    System.out.println(CYAN + "Logging out..." + RESET);
                    title2();
                    return;
                }
                default -> System.out.println(RED + "Invalid choice." + RESET);
            }
        }
    }



    
    
    

    static void viewProducts() {
        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }
        System.out.printf("%-5s %-20s %-10s %-10s\n", "ID", "Name", "Price", "Qty");
        for (Product p : products) {
            System.out.printf("%-5d %-20s %-10.2f %-10d\n", p.id, p.name, p.price, p.quantity);
        }
    }
    
    
    
    

    static Product getProductById(int id) {
        for (Product p : products) {
            if (p.id == id) return p;
        }
        return null;
    }

    
    
    
    
    
    static void loadProducts() {
        try (BufferedReader br = new BufferedReader(new FileReader(PRODUCT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                products.add(Product.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("No product file found. Starting fresh.");
        }
    }

    
    
    
    
    
    static void saveProducts() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(PRODUCT_FILE))) {
            for (Product p : products) {
                pw.println(p.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving products.");
        }
    }
    
    
    
    
    

    static void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                users.add(User.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("No user file found. Starting fresh.");
        }
    }
    
 
    
    
    
    
    public static void title2() {
        System.out.println("\u001B[34m=============================================\u001B[0m");
        System.out.println("\u001B[32m|                                           |\u001B[0m");
        System.out.println("\u001B[33m|                  Thank You                |\u001B[0m");
        System.out.println("\u001B[31m|               Come Back Again             |\u001B[0m");
        System.out.println("\u001B[32m|                                           |\u001B[0m");
        System.out.println("\u001B[34m=============================================\u001B[0m");
       
        System.out.println();
    }
 
    
    
    
    public static void printMainMenu() {
    
        System.out.print(BOLD);
        animatedPrint("                        MAIN MENU", RED);

    
        animatedPrint("            ______________________________S", CYAN);

    
        System.out.printf("%s           |                               |%s\n", YELLOW, RESET);
        System.out.printf("%s           |   %s1. Register as Customer    %s |\n", YELLOW, MAGENTA, YELLOW);
        System.out.printf("           |   %s2. Login as Customer       %s |\n", MAGENTA, YELLOW);
        System.out.printf("           |                               |\n");
        System.out.printf("           |   %s3. Register as Admin       %s |\n", CYAN, YELLOW);
        System.out.printf("           |   %s4. Login as Admin           %s|\n", CYAN, YELLOW);
        System.out.printf("           |                               |\n");
        System.out.printf("           |   %s5. Exit                    %s |\n", RED, YELLOW);
        System.out.printf("           |_______________________________|%s\n", RESET);
        System.out.printf("     %s Enter your Choice:%s ", CYAN, RESET);
    }

    
    


    
    public static void animatedPrint(String text, String color) {
        for (int i = 0; i < text.length(); i++) {
            System.out.print(color + text.charAt(i));
            try {
                Thread.sleep(20); // Simulate animation with a small delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(RESET); // Reset the color after printing
    }
    
    
    
    
    
    static void orderCart(List<Product> cart) {
        if (cart.isEmpty()) {
            System.out.println(RED + "✖ Your cart is empty! Add items before ordering." + RESET);
            return;
        }

        scanner.nextLine(); // Clear newline buffer

        System.out.print(CYAN + "Enter your name: " + RESET);
        String name = scanner.nextLine();

        System.out.print(CYAN + "Enter your address: " + RESET);
        String address = scanner.nextLine();

        System.out.print(CYAN + "Enter your phone number: " + RESET);
        String phone = scanner.nextLine();

        String transactionId = paymentMethod(); // This should return the transaction ID

        try (PrintWriter writer = new PrintWriter(new FileWriter("orders.txt", true))) {
            writer.printf("Name: %s | Address: %s | Phone: %s | Transaction ID: %s\n", name, address, phone, transactionId);
            writer.println("Ordered Products:");
            double total = 0;
            for (Product p : cart) {
                double lineTotal = p.price * p.quantity;
                writer.printf("%-10d %-20s %-10.2f %-10d %-10.2f\n", p.id, p.name, p.price, p.quantity, lineTotal);
                
                // ✅ Update stock here
                updateStock(p.id, p.quantity);

                total += lineTotal;
            }
            writer.printf("Grand Total: %.2f\n", total);
            writer.println("-----------------------------------------------------\n");
        } catch (IOException e) {
            System.out.println(RED + "✖ Failed to save order to file." + RESET);
            return;
        }

        System.out.println(GREEN + "✔ Order placed successfully!" + RESET);
        cart.clear();
    }

    

    
    
    static String paymentMethod() {
        System.out.println(YELLOW + BOLD + "Choose Payment Method:" + RESET);
        System.out.println(CYAN + "1. Bkash\n2. Nagad\n3. Bank\n4. Cash on Delivery" + RESET);
        System.out.print(MAGENTA + "Enter Choice: " + RESET);

        int method = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        System.out.println(BLUE);

        switch (method) {
            case 1 -> System.out.println("Bkash : 01777..19");
            case 2 -> System.out.println("Nagad : 01772..09");
            case 3 -> System.out.println("Bank: Islami Bank Bangladesh LTD.\nAccount No: 2050....1");
            case 4 -> System.out.println("Please pay in cash.\nEnter 'Pay_In_Cash' as Transaction ID.");
            default -> System.out.println("Invalid. Defaulting to Cash on Delivery.");
        }
        System.out.println(YELLOW + "Please Pay the amount and " + RESET);
        System.out.print(YELLOW + "Enter payment transaction ID: " + RESET);
        return scanner.nextLine();
    }

    
    
    
    static void viewOrders() {
        File file = new File("orders.txt");
        if (!file.exists()) {
            System.out.println(RED + "No orders found!" + RESET);
            return;
        }

        System.out.println(BOLD + RED + "                    ALL ORDERS    " + RESET);
        System.out.println(BLUE + "|______________________________________________________|" + RESET);
        System.out.println(CYAN);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(RED + "Error reading orders file." + RESET);
        }
    }

    
    
    
    static void updateProduct() {
        System.out.print(YELLOW + "Enter product ID to update: " + RESET);
        int id = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        Product productToUpdate = null;
        for (Product p : products) {
            if (p.id == id) {
                productToUpdate = p;
                break;
            }
        }

        if (productToUpdate == null) {
            System.out.println(RED + "✖ Product not found!" + RESET);
            return;
        }

        System.out.println(CYAN + "Leave input empty to keep current value." + RESET);

        System.out.print(PURPLE + "New Name (" + productToUpdate.name + "): " + RESET);
        String newName = scanner.nextLine();
        if (!newName.isBlank()) {
            productToUpdate.name = newName;
        }

        System.out.print(PURPLE + "New Price (" + productToUpdate.price + "): " + RESET);
        String priceInput = scanner.nextLine();
        if (!priceInput.isBlank()) {
            try {
                productToUpdate.price = Double.parseDouble(priceInput);
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid price input. Skipping update." + RESET);
            }
        }

        System.out.print(PURPLE + "New Quantity (" + productToUpdate.quantity + "): " + RESET);
        String qtyInput = scanner.nextLine();
        if (!qtyInput.isBlank()) {
            try {
                productToUpdate.quantity = Integer.parseInt(qtyInput);
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid quantity input. Skipping update." + RESET);
            }
        }

        saveProducts();
        System.out.println(GREEN + "✔ Product updated successfully!" + RESET);
    }
 
    
    static void updateStock(int id, int purchasedQty) {
        for (Product p : products) {
            if (p.id == id) {
                p.quantity -= purchasedQty;
                break;
            }
        }
        saveProducts(); // Update the products file with the new quantities
    }

  
    
    
    
  static void Title3() {
	    clearConsole();

	    System.out.print(BOLD + GREEN);
	    animatedPrint("  ******   **       **  ********   **          **  ********   ****       **   ", GREEN);
	    animatedPrint(" **        **       ** **      **   **        **  **      **  ** **      **       ", GREEN);
	    animatedPrint(" **        **       ** **      **    **      **   **      **  **  **     **         ", GREEN);
	    animatedPrint("***********************************************************************************          ", CYAN);

	    System.out.print(BOLD + GREEN + "* ");
	    System.out.print(BOLD + BLUE + "s ");
	    System.out.print(BOLD + GREEN + "***   ");
	    System.out.print(BOLD + BLUE + "                    SHOVON ");
	    System.out.print(BOLD + RED + "ONLINE STORE.     ");
	    System.out.print(BOLD + GREEN + "                     *** ");
	    System.out.print(BOLD + BLUE + "s ");
	    System.out.println(BOLD + GREEN + "*");

	    animatedPrint("***********************************************************************************", CYAN);
	    animatedPrint("       **  **       ** **      **      **  **    **      **  **     **  **                  ", PURPLE);
	    animatedPrint("       **  **       ** **      **       ****     **      **  **      ** **               ", PURPLE);
	    animatedPrint("  ******   **       **  ********         **       ********   **       ****         ", PURPLE);
	    System.out.print(RESET);
	}



	static void clearConsole() {
	    System.out.print("\033[H\033[2J");
	    System.out.flush();
	}
  

	
	
	
	
	
	
	
	
	
	

    static void saveUsers() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(USER_FILE))) {
            for (User u : users) {
                pw.println(u.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving users.");
        }
    }
}




