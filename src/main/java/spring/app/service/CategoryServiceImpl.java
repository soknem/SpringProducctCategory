package spring.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import spring.app.dto.CategoryRequest;
import spring.app.dto.CategoryRespone;
import spring.app.model.Category;
import spring.app.model.Product;
import spring.app.repository.CategoryRepository;
import spring.app.restcontroller.CategoryRestController;

import java.util.Comparator;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    private CategoryRespone mapCategoryToResponse(Category category){
        return CategoryRespone.builder().id(category.getId()).title(category.getTitle()).description(category.getDescription()).build();
    }
    private Category mapRequestToCategory(CategoryRequest categoryRequest){
        return Category.builder().id(categoryRequest.id()).title(categoryRequest.title()).description(categoryRequest.description()).build();
    }
    private Category searchCategoryById(int id){
        return categoryRepository.getAllCategory().stream()
                .filter(category -> category.getId()==id)
                .findFirst()
                .orElseThrow(()->new HttpClientErrorException(HttpStatus.NOT_FOUND,"Product doesn't exist!!"));
    }

    @Override
    public List<CategoryRespone> getAllCategory(String categoryName) {
        var category=categoryRepository.getAllCategory();
        if(!categoryName.isEmpty()){
            category=category.stream().filter(findCategory->findCategory.getTitle().toLowerCase().contains(categoryName.toLowerCase())).toList();
        }
        return category.stream().
                map(this::mapCategoryToResponse).toList();
    }

    @Override
    public CategoryRespone findCategoryById(int id) {
        Category category=searchCategoryById(id);
       return mapCategoryToResponse(category);
    }

    @Override
    public CategoryRespone createCategory(CategoryRequest categoryRequest) {
        var maxId=categoryRepository.getAllCategory().stream().max(Comparator.comparingInt(Category::getId)).map(Category::getId);
        int newId= maxId.map(integer -> (integer + 1)).orElse(1);
        Category category=Category.builder().id(newId).title(categoryRequest.title()).description(categoryRequest.title()).build();
        return mapCategoryToResponse(category);
    }

    @Override
    public void deleteProductById(int id) {
        categoryRepository.deleteCategory(searchCategoryById(id).getId());
    }

    @Override
    public CategoryRespone updateCategory(int id, CategoryRequest categoryRequest) {
        var result=mapRequestToCategory(categoryRequest);
        result.setId(id);
        categoryRepository.updateCategory(result);
        return mapCategoryToResponse(result);
    }

}
