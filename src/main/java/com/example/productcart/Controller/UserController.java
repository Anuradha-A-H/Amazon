package com.example.productcart.Controller;


import com.example.productcart.Entities.Products;
import com.example.productcart.Entities.User;
import com.example.productcart.RequestDoc.AddNewUserRequest;
import com.example.productcart.RequestDoc.AddProducts;
import com.example.productcart.RequestDoc.UpdateLoginRequest;
import com.example.productcart.Response.AddUserResponse;
import com.example.productcart.Response.ChangePasswordResponse;
import com.example.productcart.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity  addUser(@RequestBody AddNewUserRequest addUser)
    {
        try{
            AddUserResponse str = userService.addUser(addUser);
            return new ResponseEntity(str,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getUserDetails/{id}")
    public ResponseEntity getUserDetails(@PathVariable Integer id)
    {
        try{
            User pro = userService.getUserDetails(id);
            return new ResponseEntity(pro.toString(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateUserDetails")
    public ResponseEntity updateUserDetails(@RequestBody User userDetails)
    {
        try{
            User msg = userService.updateUserDetails(userDetails);
            return new ResponseEntity("Updated Successfully", HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity deleteUser(Integer id)
    {
        try{
            String msg = userService.deleteUser(id);
            return new ResponseEntity(msg, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/changePassword")
    public ResponseEntity changePassword(@RequestBody UpdateLoginRequest changePassword)
    {
        try{
            ChangePasswordResponse msg = userService.changePassword(changePassword);
            return new ResponseEntity(msg, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
