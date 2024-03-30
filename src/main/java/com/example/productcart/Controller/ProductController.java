package com.example.productcart.Controller;


import com.example.productcart.Entities.Products;
import com.example.productcart.RequestDoc.AddProducts;
import com.example.productcart.Response.AllProductsResponse;
import com.example.productcart.Service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductsService productService;

    @PostMapping("/addProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity addProduct(@RequestBody AddProducts product)
    {
        try{
            Products msg = productService.addProduct(product);
            return new ResponseEntity(msg, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity getProduct(@PathVariable String id)
    {
        try{
            Products pro = productService.getProduct(id);
            return new ResponseEntity(pro.toString(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateProduct(@RequestBody AddProducts product)
    {
        try{
            String msg = productService.updateProduct(product);
            return new ResponseEntity(msg, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("deleteProduct/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteProduct(@PathVariable String id)
    {
        try{
            String str = productService.deleteProduct(id);
            return new ResponseEntity(str.toString(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity getAllProducts(){
        try{
            List<AllProductsResponse> str = productService.getAllProducts();
            return new ResponseEntity(str, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
