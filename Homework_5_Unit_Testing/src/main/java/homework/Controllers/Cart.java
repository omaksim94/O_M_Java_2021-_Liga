package homework.Controllers;

import homework.Repositories.CartStatus;
import homework.Services.Interfaces.CartService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static homework.Repositories.CartStatus.*;

public class Cart {
    private static CartService cartService;
    private static int counter = 0;

    private final int cartId;
    private final int userId;
    private Integer orderId;
    private CartStatus status = CREATED;
    private Map<Product, Integer> map;

    public Cart(User user, CartService service) {
        cartId = ++counter;
        this.userId = user.getUserId();
        this.cartService = service;
        this.map = new HashMap<>();
        cartService.addToCartList(this);
    }

    public Map<Product, Integer> add(Product product, Integer quantity) {
        if (status != ORDERED) {
            map = cartService.add(map, product, quantity);
        }
        return this.getCartProducts();
    }

    public Map<Product, Integer> remove(Product product) {
        if (status != ORDERED) {
            map = cartService.remove(map, product);
        }
        return this.getCartProducts();
    }

    public Map<Product, Integer> remove(Product product, int quantity) {
        if (status != ORDERED) {
            map = cartService.remove(map, product, quantity);
        }
        return this.getCartProducts();
    }

    public Integer makeOrder() {
        if (status == ORDERED) {
            return orderId;
        }
        if (!map.isEmpty()) {
            orderId = cartService.makeOrder(userId, cartId);
            if (orderId != null) {
                status = ORDERED;
            }
        }
        return orderId;
    }

    public int cartPrice() {
        return cartService.cartPrice(map);
    }

    public static Cart getCart(int id) {
        return cartService.getCart(id);
    }

    public CartStatus getStatus() {
        return status;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public int getCartId() {
        return cartId;
    }

    public Map<Product, Integer> getCartProducts() {
        return map.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", userId=" + userId +
                ", orderId=" + orderId +
                ", status=" + status +
                ", \ncart=" + map +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return cartId == cart.cartId && userId == cart.userId && cartService.equals(cart.cartService) && orderId.equals(cart.orderId) && status == cart.status && Objects.equals(map, cart.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartService, cartId, userId, orderId, status, map);
    }
}
