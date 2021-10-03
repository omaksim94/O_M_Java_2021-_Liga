package homework.Repositories;

import homework.Controllers.Cart;
import homework.Controllers.Order;
import homework.Controllers.Product;
import homework.Controllers.User;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private final List<Cart> cartList = new ArrayList<>();
    private final List<Order> orderList = new ArrayList<>();
    private final List<Product> productList = new ArrayList<>();
    private final List<User> userList = new ArrayList<>();

    public List<Cart> getCartList() {
        return new ArrayList<>(cartList);
    }

    public List<Order> getOrderList() {
        return new ArrayList<>(orderList);
    }

    public List<Product> getProductList() {
        return new ArrayList<>(productList);
    }

    public List<User> getUserList() {
        return new ArrayList<>(userList);
    }

    public void addOrder(Order order) {
        orderList.add(order);
    }

    public List<Cart> addCart(Cart cart) {
        cartList.add(cart);
        return new ArrayList<>(cartList);
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void addUser(User user) {
        userList.add(user);
    }
}
