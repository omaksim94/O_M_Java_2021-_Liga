package homework.Services.Interfaces;

import homework.Controllers.Cart;
import homework.Controllers.Product;
import homework.Repositories.CartStatus;

import java.util.List;
import java.util.Map;

public interface CartService {
    List<Cart> addToCartList(Cart cart);
    Map<Product, Integer> add(Map<Product, Integer> map, Product product, int quantity);
    Map<Product, Integer> remove(Map<Product, Integer> map, Product product);
    Map<Product, Integer> remove(Map<Product, Integer> map, Product product, int quantity);
    void clearCart(CartStatus state, Map<Product, Integer> map);
    Integer makeOrder(int userId, int cartId);
    Cart getCart(int id);
    int cartPrice(Map<Product, Integer> map);
}
