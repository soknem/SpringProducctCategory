package spring.app.service;

import spring.app.dto.ProductRequest;
import spring.app.dto.ProductResponse;
import spring.app.repository.ProductRepository;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProduct(String productName);
    ProductResponse createProduct(ProductRequest productRequest);
    void deleteProduct(int id);
    ProductResponse updateProduct(int id,ProductRequest productRequest);
    ProductResponse findProductById(int id);

}
