package homework.Services;

import homework.Controllers.Order;
import homework.Controllers.Product;
import homework.Repositories.Repository;
import homework.Services.Interfaces.ProductService;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private final Repository repository;
    public ProductServiceImpl(Repository repository){
        this.repository = repository;
    }

    public void addToProductList(Product product){
        repository.addProduct(product);
    }

    @Override
    public List<Product> getAllProductsPriceBellow(int price) {
        return repository.getProductList().stream()
                .filter(x -> x.getPrice() <= price)
                .collect(Collectors.toList());
    }

    @Override
    public Product getProduct(int id) {
            for (Product product : repository.getProductList()){
                if (product.getProductId() == id){
                    return product;
                }
            }
            return null;
    }
}
