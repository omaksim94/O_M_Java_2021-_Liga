package homework.Services.Interfaces;

import homework.Controllers.Product;

import java.util.List;

public interface ProductService {
    void addToProductList(Product product);
    List<Product> getAllProductsPriceBellow(int price);
    Product getProduct(int id);
}
