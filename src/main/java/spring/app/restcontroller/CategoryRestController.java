package spring.app.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PatchExchange;
import spring.app.dto.CategoryRequest;
import spring.app.service.CategoryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("ap1/v1/category")
public class CategoryRestController {
    @Autowired
    CategoryService categoryService;
    public Map<String,Object> createResponse(Object object,String message,int status){
        HashMap<String, Object> response=new HashMap<>();
        response.put("payload",object);
        response.put("messsage",message);
        response.put("status",status);
        return response;
    }
    @GetMapping("/get-all")
    public Map<String,Object> getAllCategory(@RequestParam(defaultValue = "") String categoryName){
        return createResponse(categoryService.getAllCategory(categoryName),"GET DATA  SUCCESSFULLY", HttpStatus.OK.value());
    }
    @PostMapping("/add-new")
    public Map<String,Object> addCategory(@RequestBody CategoryRequest categoryRequest){
        return createResponse(categoryService.createCategory(categoryRequest),"CREATE CATEGORY SUCCESSFULLY",HttpStatus.CREATED.value());
    }
    @PatchMapping("/{id}")
    public Map<String,Object> update(@PathVariable int id,@RequestBody CategoryRequest categoryRequest){
        return createResponse(categoryService.updateCategory(id,categoryRequest),"UPDATE CATEGORY SUCCESSFULLY",HttpStatus.OK.value());
    }
    @GetMapping("/{id}")
    public Map<String,Object> findCategiryById(@PathVariable int id){
        return createResponse(categoryService.findCategoryById(id),"FOUND CATEGORY",HttpStatus.FOUND.value());
    }
    @DeleteMapping("/{id}")
    public Map<String,Object> deleteCategory(@PathVariable int id){
        categoryService.deleteProductById(id);
        return createResponse(new ArrayList<>(),"DELETE CATEGORY SUCCESSFULLY",HttpStatus.OK.value());
    }
}
