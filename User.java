package Shopping;

class User {
    String username;
    String password;
    String role;

    User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String toFileString() {
        return username + "," + password + "," + role;
    }

    public static User fromFileString(String line) {
        String[] parts = line.split(",");
        return new User(parts[0], parts[1], parts[2]);
    }
}
