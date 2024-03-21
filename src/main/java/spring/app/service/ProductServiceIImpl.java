package spring.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import spring.app.dto.ProductRequest;
import spring.app.dto.ProductResponse;
import spring.app.model.Product;
import spring.app.repository.ProductRepository;
import java.util.Comparator;
import java.util.List;
@Service
public class ProductServiceIImpl implements  ProductService {
    @Autowired
    private  ProductRepository productRepository;

    private ProductResponse mapProductToResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .imageUrl(product.getImageUrl())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
    private Product mapRequestToProduct(ProductRequest request){
        return Product.builder()
                .title(request.title())
                .price(request.price())
                .imageUrl(request.imageUrl())
                .description(request.description())
                .build();
    }

    @Override
    public List<ProductResponse> getAllProduct(String productName) {
        var product=productRepository.getAllProduct();
        if(!productName.isEmpty()){
            product = product.stream().filter(
                    pro-> pro.getTitle().toLowerCase().contains(productName.toLowerCase())
            ).toList();
        }
        return product
                .stream()
                .map(pro -> {
                    return ProductResponse.builder()
                           .id(pro.getId())
                           .imageUrl(pro.getImageUrl())
                           .price(pro.getPrice())
                           .title(pro.getTitle())
                           .description(pro.getDescription())
                           .build();
                }).toList();

    }
    private Product searchProductById(int id){
        return  productRepository.getAllProducts()
                .stream().filter(p->p.getId()==id)
                .findFirst()
                .orElseThrow(()->new HttpClientErrorException(HttpStatus.NOT_FOUND,"Product doesn't exist!!"));
    }


    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder().title(productRequest.title()).price(productRequest.price()).imageUrl(productRequest.imageUrl()).description(productRequest.description()).build();
        var maxId = productRepository.getAllProduct().stream().max(Comparator.comparingInt(Product::getId)).map(Product::getId);
        int newId = 1;
        if (maxId.isPresent()) {
            newId = maxId.get() + 1;
        }
        product.setId(newId);
        productRepository.addAllproduct(product);
        return mapProductToResponse(product);

    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteProduct(searchProductById(id).getId());
    }

    @Override
    public ProductResponse updateProduct(int id,ProductRequest productRequest) {
        var result=searchProductById(id);
        result=mapRequestToProduct(productRequest);
        if (productRequest.title() != null) {
            result.setTitle(productRequest.title());
        }
        if (productRequest.description() != null) {
            result.setDescription(productRequest.description());
        }
        if (productRequest.price() != 0) {
            result.setPrice(productRequest.price());
        }
        if (productRequest.imageUrl() != null) {
            result.setImageUrl(productRequest.imageUrl());
        }
        productRepository.updateProduct(result);
        return mapProductToResponse(result);

    }

    @Override
    public ProductResponse findProductById(int id) {
       Product product= searchProductById(id);
        return mapProductToResponse(product);
    }
}
