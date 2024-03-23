package spring.app.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.app.dto.ProductRequest;
import spring.app.dto.ProductResponse;
import spring.app.service.ProductService;
import spring.app.service.ProductServiceIImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("ap1/v1/products")
public class ProductRestController {
    @Autowired
    private  ProductService productService;

    public Map<String, Object> creatResponse(Object object,String message, int status){
        HashMap<String,Object> response=new HashMap<>();
        response.put("payload",object);
        response.put("message", message);
        response.put("status", status);
        return response;

    }
    @GetMapping("/get-all")
    public Map<String,Object> getAllProduct(@RequestParam(defaultValue = "")String productName){
        return creatResponse(productService.getAllProduct(productName),"DATA RECEIVE SUCCECCFULLY",HttpStatus.OK.value());
    }
    @PostMapping("/add-new")
    public Map<String,Object> addProduct(@RequestBody ProductRequest productRequest){
        return creatResponse(productService.createProduct(productRequest),"Created New Product Successfully!",HttpStatus.CREATED.value());
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Map<String,Object> findProductByID(@PathVariable int id){
        return creatResponse(productService.findProductById(id),"Successfully Retrieved the record!",HttpStatus.FOUND.value());
    }
    @PatchMapping("/{id}")
    public Map<String,Object> updateProduct(@PathVariable int id,@RequestBody ProductRequest productRequest){
       return creatResponse(productService.updateProduct(id,productRequest),"update product",HttpStatus.OK.value());
    }
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
        return creatResponse(new ArrayList<>(),"Delete Successfully",HttpStatus.OK.value());
    }
}
