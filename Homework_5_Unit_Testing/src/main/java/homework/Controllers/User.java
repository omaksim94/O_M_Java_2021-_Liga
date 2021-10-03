package homework.Controllers;

import homework.Services.Interfaces.UserService;

import java.util.List;

public class User {
    private static int counter = 0;
    private static UserService userService;

    private final int userId;
    private String name;
    private String address;

    public User(UserService service, String name, String address) {
        userId = ++counter;
        this.name = name;
        this.address = address;
        this.userService = service;
        userService.addToUserList(this);
    }

    public List<Order> userOrders() {
        return userService.getUserOrders(this);
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public static User getUser(int id) {
        return userService.getUser(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
