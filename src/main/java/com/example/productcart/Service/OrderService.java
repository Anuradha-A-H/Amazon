package com.example.productcart.Service;

import com.example.productcart.Entities.Orders;
import com.example.productcart.Entities.Products;
import com.example.productcart.Entities.User;
import com.example.productcart.Enums.Status;
import com.example.productcart.Repository.OrderRepository;
import com.example.productcart.Repository.ProductRepository;
import com.example.productcart.Repository.UserRepository;
import com.example.productcart.RequestDoc.OrderRequest;
import com.example.productcart.Response.OrderResponse;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    public OrderRepository orderRepo;
    @Autowired
    public UserRepository userRepo;
    @Autowired
    ProductRepository productsRepo;

    public OrderResponse placeOrder(OrderRequest orders)throws Exception
    {
        Optional<User> user = userRepo.findById(orders.getUserId());
        if(user.isEmpty())
        {
            throw new Exception("User Not Found");
        }
        List<String> productNames = new ArrayList<>();
        List<Products> productlist = new ArrayList<>();
        for(String id : orders.getProductsList())
        {
            Optional<Products> product = productsRepo.findById(id);
            if(product.isEmpty())
            {
                throw new Exception("Invalid productId ");
            }
            Products products = product.get();
            if(products.getQuantity()<=0)
            {
                throw new Exception("Product is Our of Stock productId: "+products.getProductId());
            }
            productNames.add(products.getProductName());
            productlist.add(products);
        }
        User userdetail = user.get();
        Orders order = Orders.builder()
                .UserId(orders.getUserId())
                .status(Status.ORDER_PLACED)
                .address(orders.getAddress())
                .productsList(productlist)
                .build();
        order = orderRepo.save(order);
        String message = "Order Placed Successfully Track Your order Using the link given below : http://localhost:8080/trackOrder/"+order.getOrderId() ;
        message += "  <br>If you have any questions or concerns regarding your order, feel free to reach out to our customer service team at customerservice@gamil.com .";
        OrderResponse response = OrderResponse.builder()
                .orderId(order.getOrderId())
                .status(order.getStatus())
                .productsList(productNames)
                .address(order.getAddress())
                .message(message)
                .build();
        return response;

    }

    public OrderResponse trackOrder(String id) throws Exception{
        Optional<Orders> order = orderRepo.findById(id);
        if(order.isEmpty())
        {
            throw new Exception(" invalid orderId Entered");
        }
        Orders orders = order.get();
        List<String> productsList = new ArrayList<>();
        for(Products product : orders.getProductsList())
        {
            productsList.add(product.getProductName());
        }
        String message = "Your order will arrive destination on 14th March. ";
        OrderResponse response = OrderResponse.builder()
                .orderId(orders.getOrderId())
                .address(orders.getAddress())
                .status(orders.getStatus())
                .productsList(productsList)
                .message(message)
                .build();
        return response;

    }

}
