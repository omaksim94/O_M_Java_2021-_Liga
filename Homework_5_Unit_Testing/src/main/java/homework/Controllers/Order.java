package homework.Controllers;

import homework.Services.Interfaces.OrderService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class Order {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.MM.yy HH:mm:ss");
    private static int counter = 0;
    private static OrderService orderService;

    private final String orderDateTime;
    private final int orderId;
    private final int userId;
    private final int cartId;

    public Order(int userId, int cartId, OrderService service) {
        this.orderDateTime = dtf.format(LocalDateTime.now());
        this.orderId = ++counter;
        this.userId = userId;
        this.cartId = cartId;
        this.orderService = service;
        orderService.addToOrderList(this);
    }

    public int getOrderPrice() {
        return orderService.getOrderPrice(this);
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCartId() {
        return cartId;
    }

    public int getUserId() {
        return userId;
    }

    public static Order getOrder(int id) {
        return orderService.getOrder(id);
    }

    public static List<Order> ordersWithPriceMoreThan(int price) {
        return orderService.ordersWithPriceMoreThan(price);
    }

    @Override
    public String toString() {
        return "Order{" +
                ", orderDateTime='" + orderDateTime + '\'' +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", cartId=" + cartId +
                '}';
    }
}
