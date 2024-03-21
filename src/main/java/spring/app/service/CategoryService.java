package spring.app.service;

import spring.app.dto.CategoryRequest;
import spring.app.dto.CategoryRespone;
import spring.app.model.Category;

import java.util.List;

public interface CategoryService {
    public List<CategoryRespone> getAllCategory(String categoryName);
    public CategoryRespone createCategory(CategoryRequest categoryRequest);
    public void deleteProductById(int id);
    public CategoryRespone updateCategory(int id,CategoryRequest categoryRequest);
    public CategoryRespone findCategoryById(int id);
}
