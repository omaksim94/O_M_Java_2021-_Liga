package homework.Services;

import homework.Controllers.Order;
import homework.Controllers.User;
import homework.Repositories.Repository;
import homework.Services.Interfaces.UserService;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final Repository repository;

    public UserServiceImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void addToUserList(User user) {
        repository.addUser(user);
    }

    @Override
    public User getUser(int id) {
            for (User user : repository.getUserList()){
                if (user.getUserId() == id){
                    return user;
                }
            }
            return null;
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return repository.getOrderList().stream()
                .filter(x -> x.getUserId() == user.getUserId())
                .collect(Collectors.toList());
    }

}
