package spring.app.repository;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import spring.app.model.Product;
import java.util.ArrayList;
import java.util.List;
@Getter
@Repository
public class ProductRepository {
    private final List<Product> allProducts =new ArrayList<>(){{
        add(Product.builder()
                .id(1)
                .title("product title one ")
                .description("this is the product one description")
                .price(3.4f)
                .imageUrl("productimage.jpg")
                .build());

        add(Product.builder()
                .id(2)
                .title("product title two ")
                .description("this is the product two description")
                .price(3.4f)
                .imageUrl("productimagetwo.jpg")
                .build());

        add(Product.builder()
                .id(3)
                .title("product title three ")
                .description("this is the product one description")
                .price(3.4f)
                .imageUrl("productimagethree.jpg")
                .build());
    }};

    public void addAllproduct(Product product){
        allProducts.add(product);
    }
    public List<Product> getAllProduct(){
        return allProducts;
    }
    public void updateProduct(Product product){
        int index =   allProducts.indexOf(
                allProducts.stream()
                        .filter(pro->pro.getId()==product.getId())
                        .findFirst()
                        .orElse(null)
        ) ;

        allProducts.set(index,product);
    }
    public void deleteProduct(int id){
        allProducts.remove(allProducts.stream()
                .filter(pro -> pro.getId() == id)
                .findFirst()
                .orElse(null));
    }
}
