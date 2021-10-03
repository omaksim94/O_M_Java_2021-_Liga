package homework.Services.Interfaces;

import homework.Controllers.Order;

import java.util.List;

public interface OrderService {
    void addToOrderList(Order order);
    int getOrderPrice(Order order);
    Order getOrder(int id);
    List<Order> ordersWithPriceMoreThan(int price);
}
