package project14.user.role.management;

public class User {
    // 1. Variables (Ingredients)
    private String username;
    private Role role;
    private String password; // Added for security

    // 2. Constructor (The Builder) when this is updated, update all others as well
    public User(String username, Role role, String password) {
        this.username = username;
        this.role = role;
        this.password = password;
    }

    // 3. Methods (Getters)
    public String getUsername() { return username; }
    public Role getRole() { return role; }
    public String getPassword() { return password; }
}



