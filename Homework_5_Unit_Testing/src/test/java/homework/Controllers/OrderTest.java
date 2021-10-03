package homework.Controllers;

import homework.Services.Interfaces.OrderService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class OrderTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    OrderService orderService;
    Order order;

    @Before
    public void setUp() {
        order = new Order(1, 1, orderService);
    }

    @Test
    public void getOrderPrice() {
        when(orderService.getOrderPrice(order)).thenReturn(1000);
        assertEquals(1000, order.getOrderPrice());
    }

    @Test
    public void getOrder() {
        when(orderService.getOrder(order.getOrderId())).thenReturn(order);
        assertEquals(order, Order.getOrder(order.getOrderId()));

        when(orderService.getOrder(1000)).thenReturn(null);
        assertNull(Order.getOrder(1000));
    }

    @Test
    public void ordersWithPriceMoreThan() {
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        when(orderService.ordersWithPriceMoreThan(100)).thenReturn(orders);
        assertEquals(orders, Order.ordersWithPriceMoreThan(100));

        when(orderService.ordersWithPriceMoreThan(1000)).thenReturn(null);
        assertNull(Order.ordersWithPriceMoreThan(1000));
    }
}