package com.example.amazone_database.Service;

import com.example.amazone_database.Model.Category;
import com.example.amazone_database.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;



    // -----  CRUD  ---------

    // get All Categories
    public List<Category> getCategories() {
        return  categoryRepository.findAll();
    }

    // creat new Category
    public void addCategories(Category category) {
        categoryRepository.save(category);
    }


    // Update Category by id
    public boolean updateCategory(Integer id ,Category category) {
        Category c=categoryRepository.getById(id);
        if(c==null) {
            return false;
        }
        c.setName(category.getName());

        categoryRepository.save(c);
        return true;
    }



    // Delete Category by id
    public boolean removeCategory(Integer id ) {
        Category c = categoryRepository.getById(id);
        if (c == null) {
            return false;
        }
        categoryRepository.delete(c);
        return true;
    }



    // get category by id
    public Category getCategoryById(Integer id) {
      Category c=categoryRepository.getById(id);
      if (c == null) {
       return null;
      }
      return c;
    }


}
