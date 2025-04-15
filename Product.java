package Shopping;

class Product {
    int id;
    String name;
    double price;
    int quantity;

    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String toFileString() {
        return id + "," + name + "," + price + "," + quantity;
    }

    public static Product fromFileString(String line) {
        String[] parts = line.split(",");
        return new Product(
                Integer.parseInt(parts[0]),
                parts[1],
                Double.parseDouble(parts[2]),
                Integer.parseInt(parts[3])
        );
    }
}
