package com.example.productcart.Service;

import com.example.productcart.Entities.Category;
import com.example.productcart.Entities.Products;
import com.example.productcart.Repository.CategoryRepository;
import com.example.productcart.Repository.ProductRepository;
import com.example.productcart.RequestDoc.AddProducts;
import com.example.productcart.Response.AllProductsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private CategoryRepository categoryRepo;

    public Products addProduct(AddProducts product) throws Exception
    {
        Integer categoryId = product.getCategoryId();
        Category cat = categoryRepo.findById(categoryId).get();
        Products pro = new Products(product.getProductName(), product.getQuantity(), product.getPrice(),cat);

        List<Products> p = cat.getProductsList();
        p.add(pro);
        cat.setProductsList(p);

        Products productToAdd= productRepo.save(pro);
        Category categoryToAdd= categoryRepo.save(cat);

        if(productToAdd == null)
        {
            throw new Exception("Something went wrong !!");
        }
        return productToAdd;
    }

    public Products getProduct(String id)throws Exception
    {
        Optional<Products> pro = productRepo.findById(id);
        if(pro.isEmpty())
        {
            throw new Exception("Invalid Product Id !!");
        }
        return pro.get();
    }

    public String updateProduct(AddProducts product) throws Exception
    {
        Integer categoryId = product.getCategoryId();
        Category cat = categoryRepo.findById(categoryId).get();
        Products pro = new Products(product.getProductName(), product.getQuantity(), product.getPrice(),cat);

        List<Products> p = cat.getProductsList();
        p.add(pro);
        cat.setProductsList(p);
        Category category = new Category(cat.getCategoryId(),cat.getCategoryName(),cat.getProductsList());
//
        Products productToAdd= productRepo.save(pro);
        Category categoryToAdd= categoryRepo.save(category);
        if(pro == null)
        {
            throw new Exception("Something went wrong !!");
        }
        return productToAdd.toString();
    }

    public String deleteProduct(String id)throws Exception
    {
        try{
            Products pro = productRepo.findById(id).get();
            Category category = pro.getCategory();
            List<Products> products = new ArrayList<>();
            products.remove(pro);
            category.setProductsList(products);


            categoryRepo.save(category);
            productRepo.deleteById(id);
            return "Deleted Successfully";
        }catch(Exception e){
            throw new Exception("Invalid Product Id !!");
        }
    }

    public List<AllProductsResponse> getAllProducts()throws Exception
    {
        List<Products> pro = productRepo.findAll();
        if(pro.isEmpty())
        {
            throw new Exception("Invalid Product Id !!");
        }
        List<AllProductsResponse> responses = new ArrayList<>();
        for(Products products : pro)
        {

            AllProductsResponse response = AllProductsResponse.builder()
                    .productId(products.getProductId())
                    .productName(products.getProductName())
                    .availableStatus(products.getAvailableStatus())
                    .quantity(products.getQuantity())
                    .price(products.getPrice())
                    .category(products.getCategory().getCategoryName())
                    .build();
            responses.add(response);
        }


        return responses;
    }

}
