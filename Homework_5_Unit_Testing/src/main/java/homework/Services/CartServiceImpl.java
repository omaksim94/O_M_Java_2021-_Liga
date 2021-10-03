package homework.Services;

import homework.Controllers.*;
import homework.Repositories.Repository;
import homework.Repositories.CartStatus;
import homework.Services.Interfaces.CartService;

import java.util.List;
import java.util.Map;

import static homework.Main.orderService;
import static homework.Repositories.CartStatus.*;

public class CartServiceImpl implements CartService {
    private final Repository repository;

    public CartServiceImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public List<Cart> addToCartList(Cart cart) {
        return repository.addCart(cart);
    }

    @Override
    public Map<Product, Integer> add(Map<Product, Integer> map, Product product, int qt) {
        if (qt <= 0) {
        } else if (map.containsKey(product)) {
            int prevQt = map.get(product);
            map.put(product, prevQt + qt);
        } else {
            map.put(product, qt);
        }
        return map;
    }

    @Override
    public Map<Product, Integer> remove(Map<Product, Integer> map, Product product) {
        map.remove(product);
        return map;
    }

    @Override
    public Map<Product, Integer> remove(Map<Product, Integer> map, Product product, int quantity) {
        if (map.containsKey(product)) {
            int prevQt = map.get(product);
            if (quantity <= 0) {
            } else if (prevQt - quantity <= 0) {
                map.remove(product);
            } else {
                map.replace(product, prevQt, prevQt - quantity);
            }
        }
        return map;
    }

    @Override
    public void clearCart(CartStatus state, Map<Product, Integer> map) {
        if (state != ORDERED) {
            map.clear();
        }
    }

    @Override
    public Integer makeOrder(int userId, int cartId) {
        return new Order(userId, cartId, orderService).getOrderId();
    }

    @Override
    public int cartPrice(Map<Product, Integer> map) {
        return map.entrySet().stream()
                .map(x -> x.getKey().getPrice() * x.getValue())
                .reduce(0, (a, b) -> a + b);
    }

    @Override
    public Cart getCart(int id) {
        for (Cart cart : repository.getCartList()) {
            if (cart.getCartId() == id) {
                return cart;
            }
        }
        return null;
    }

}

