package homework.Services.Interfaces;

import homework.Controllers.Order;
import homework.Controllers.User;

import java.util.List;

public interface UserService {
    void addToUserList(User user);
    List<Order> getUserOrders(User user);
    User getUser(int id);
}
