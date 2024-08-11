package com.example.amazone_database.Controller;


import com.example.amazone_database.Model.Category;
import com.example.amazone_database.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {


    //Dependency injection
    private final CategoryService categoryService;



    // ---- CRUD ----

    @GetMapping("/get") //get All Category
    public ResponseEntity getCategories() {
        return  ResponseEntity.status(200).body(categoryService.getCategories());
    }



    @PostMapping("/add") // creat new Category
    public ResponseEntity addCategories(@Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        categoryService.addCategories(category);
        return ResponseEntity.status(200).body(" Category Added Successfully");
    }



    @DeleteMapping("/delete/{id}")// Delete Category by id
    public ResponseEntity deleteProduct(@PathVariable Integer id) {

        if (categoryService.removeCategory(id)){
            return ResponseEntity.status(200).body(" Category Deleted Successfully");
        }
        return ResponseEntity.status(404).body(" Category Not Found");
    }



    @PutMapping("/update/{id}")// update Category by id
    public ResponseEntity putCategory(@PathVariable Integer id, @Valid@RequestBody Category category , Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        if (categoryService.updateCategory(id, category)){
            return ResponseEntity.status(200).body(" Category Updated Successfully");
        }
        return ResponseEntity.status(404).body(" Category Not Found");
    }




    @GetMapping("/get-category/{id}") // get category by id
    public ResponseEntity getCategory(@PathVariable Integer id) {
        Category c=categoryService.getCategoryById(id);

        if(c==null){
            return ResponseEntity.status(404).body(" Category Not Found");
        }
        return ResponseEntity.status(200).body(c);
    }



}
