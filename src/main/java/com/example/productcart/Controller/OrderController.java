package com.example.productcart.Controller;


import com.example.productcart.RequestDoc.OrderRequest;
import com.example.productcart.Response.OrderResponse;
import com.example.productcart.Service.OrderService;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity placeOrder(@RequestBody OrderRequest orderRequest)
    {
        try{
            OrderResponse response = orderService.placeOrder(orderRequest);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("trackOrder/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity trackOrder(@PathVariable String id)
    {
        try{
            OrderResponse orders = orderService.trackOrder(id);
            return new ResponseEntity(orders, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
