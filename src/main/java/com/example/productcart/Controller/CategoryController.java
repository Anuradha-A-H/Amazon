package com.example.productcart.Controller;


import com.example.productcart.Entities.Category;
import com.example.productcart.Entities.Products;
import com.example.productcart.RequestDoc.AddProducts;
import com.example.productcart.Response.CategoryResponse;
import com.example.productcart.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addCategory")
    public ResponseEntity addCategory(@RequestBody Category category)
    {
        try{
            Category cat = categoryService.addCategory(category);
            return new ResponseEntity(cat.toString(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllCategory")
    public ResponseEntity getAllCategory()
    {
        try{
            List<CategoryResponse> category = categoryService.getAllCategory();
            return new ResponseEntity(category, HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getProductByCategoryId/{Id}")
    public ResponseEntity getProductByCategoryId(@PathVariable Integer id)
    {
        try
        {
            List<Products> productsList = categoryService.getProductByCategoryId(id);
            return new ResponseEntity(productsList,HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateCategory")
    public ResponseEntity updateCategory(@RequestBody Category category)
    {
        try{
            Category msg = categoryService.updateCategory(category);
            return new ResponseEntity(msg, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("deleteCategory/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id)
    {
        try{
            String str = categoryService.deleteCategory(id);
            return new ResponseEntity(str.toString(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
