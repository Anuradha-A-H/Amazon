package com.example.productcart.Service;


import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.productcart.Entities.Category;
import com.example.productcart.Entities.Products;
import com.example.productcart.Repository.CategoryRepository;
import com.example.productcart.Repository.ProductRepository;
import com.example.productcart.Response.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private ProductRepository productRepo;

    public Category addCategory(Category category) throws Exception{

        Category cat = categoryRepo.save(category);
        if(cat == null)
        {
            throw new Exception("Something went wrong !!");
        }
        return cat;
    }

    public  List<CategoryResponse> getAllCategory() throws Exception{
        List<Category> categories = categoryRepo.findAll();
        if(categories.isEmpty())
        {
            throw new Exception(" No Data Found");
        }
        List<CategoryResponse> allCategory = new ArrayList<>();
        for(Category category : categories)
        {
            List<String> productName = new ArrayList<>();
            for(Products products : category.getProductsList())
                productName.add(products.getProductName());

            CategoryResponse categoryResponse = CategoryResponse.builder()
                    .categoryName(category.getCategoryName())
                    .categoryId(category.getCategoryId())
                    .productsList(productName)
                    .build();
            allCategory.add(categoryResponse);


        }
        return allCategory;
    }

    public List<Products> getProductByCategoryId(Integer categoryId) throws Exception
    {
        Category category = categoryRepo.findById(categoryId).get();
        if(category == null)
        {
            throw  new Exception("Category Not Found Or Invalid Category Id");
        }
        List<Products> productsList = category.getProductsList();
        return productsList;
    }

    public  Category updateCategory(Category category)throws Exception
    {
        Category cat = categoryRepo.save(category);
        if(cat == null)
        {
            throw new Exception("Something went wrong !!");
        }
        return cat;
    }

    public String deleteCategory(Integer Id )throws Exception
    {
        try{
            Category category = categoryRepo.findById(Id).get();
            List<Products> allProducts = category.getProductsList();
            for(Products product : allProducts)
            {
                productRepo.deleteById(product.getProductId());
            }

            categoryRepo.deleteById(Id);
            return "Deleted Successfully";
        }catch (Exception e)
        {
            return e.getMessage();
        }

    }
}
