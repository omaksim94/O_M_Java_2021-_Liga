package homework.Services;

import homework.Controllers.Order;
import homework.Repositories.Repository;
import homework.Services.Interfaces.OrderService;

import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private final Repository repository;

    public OrderServiceImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void addToOrderList(Order order) {
        repository.addOrder(order);
    }

    @Override
    public int getOrderPrice(Order order) {
        return repository.getCartList().stream()
                .filter(x -> x.getCartId() == order.getCartId())
                .map(x -> x.cartPrice())
                .reduce(0, (a, b) -> a + b);
    }

    @Override
    public Order getOrder(int id) {
        for (Order order : repository.getOrderList()) {
            if (order.getOrderId() == id) {
                return order;
            }
        }
        return null;
    }

    public List<Order> ordersWithPriceMoreThan(int price) {
        return repository.getOrderList().stream()
                .filter(x -> x.getOrderPrice() >= price)
                .collect(Collectors.toList());

    }
}
