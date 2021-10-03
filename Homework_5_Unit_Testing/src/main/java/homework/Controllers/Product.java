package homework.Controllers;

import homework.Services.Interfaces.ProductService;

import java.util.List;
import java.util.Objects;


public class Product {
    private static int counter = 0;
    private static ProductService productService;

    private final int productId;
    private String name;
    private int price;

    public Product(ProductService service, String name, int price) {
        productId = ++counter;
        this.name = name;
        this.price = price;
        this.productService = service;
        productService.addToProductList(this);
    }

    public static List<Product> getAllProductsPriceBellow(int price){
        return productService.getAllProductsPriceBellow(price);
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public static Product getProduct(int id){
        return productService.getProduct(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
