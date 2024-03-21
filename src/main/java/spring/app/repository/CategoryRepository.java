package spring.app.repository;

import org.springframework.stereotype.Repository;
import spring.app.model.Category;

import java.util.ArrayList;
import java.util.List;
@Repository
public class CategoryRepository {
    private final List<Category> allCategory=new ArrayList<>(){{
        add(Category.builder().id(1).title("title 1").description("description1").build());
        add(Category.builder().id(2).title("title 2").description("description2").build());
        add(Category.builder().id(3).title("title 3").description("description3").build());
        add(Category.builder().id(4).title("title 4").description("description4").build());
    }};
    public void addProduct(Category category){
        allCategory.add(category);
    }
    public List<Category> getAllCategory(){
        return allCategory;
    }
    public void updateCategory(Category category){
        int index=allCategory.indexOf(allCategory.stream().filter(findCategory->findCategory.getId()==category.getId()).findFirst().orElse(null));
        allCategory.set(index,category);
    }
    public void deleteCategory(int id){
        allCategory.remove(allCategory.stream().filter(findCategory -> findCategory.getId() == id).findFirst().orElse(null));
    }
}
