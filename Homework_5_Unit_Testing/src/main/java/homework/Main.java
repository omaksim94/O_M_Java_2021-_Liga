package homework;

import homework.Controllers.*;
import homework.Repositories.Repository;
import homework.Services.*;
import homework.Services.Interfaces.*;

import java.util.List;

public class Main {
    public static final Repository allObjects = new Repository();
    public static final CartService cartService = new CartServiceImpl(allObjects);
    public static final OrderService orderService = new OrderServiceImpl(allObjects);
    public static final ProductService productService = new ProductServiceImpl(allObjects);
    public static final UserService userService = new UserServiceImpl(allObjects);

    public static void main(String[] args) {
        User user1 = new User(userService, "Bob Ross", "SPB");

        Product prod1 = new Product(productService, "Apple", 30);
        Product prod2 = new Product(productService, "Banana", 40);
        Product prod3 = new Product(productService, "Orange", 50);
        Cart cart1 = new Cart(user1, cartService);
        Cart cart3 = new Cart(user1, cartService);

        cart1.add(prod1, 10);
        cart1.add(prod2,10);
        cart1.remove(prod2, 5);
        cart1.makeOrder();
        cart3.makeOrder();
        cart3.add(prod1, 20);
        cart3.add(prod3, 20);
        cart3.remove(prod1);
        cart3.makeOrder();

        List<Order> user1Orders =  user1.userOrders();
        for (Order order : user1Orders){
            System.out.printf("Order ID: %s\n" +
                            "Order date: %s\n" +
                            "Username: %s\n" +
                            "User address: %s\n" +
                            "Bought products: %s\n" +
                            "Total price: %s\n\n",
                    order.getOrderId(),
                    order.getOrderDateTime(),
                    user1.getName(),
                    user1.getAddress(),
                    Cart.getCart(order.getCartId()).getCartProducts(),
                    order.getOrderPrice());
        }

    }
}
