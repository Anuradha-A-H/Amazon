package com.example.productcart.Service;
import com.example.productcart.Entities.Login;
import com.example.productcart.Entities.User;
import com.example.productcart.Repository.LoginRepository;
import com.example.productcart.Repository.UserRepository;
import com.example.productcart.RequestDoc.AddNewUserRequest;
import com.example.productcart.RequestDoc.UpdateLoginRequest;
import com.example.productcart.Response.AddUserResponse;
import com.example.productcart.Response.ChangePasswordResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {



    @Autowired
    private UserRepository userRepo;
    @Autowired
    private LoginRepository loginRepo;

    public UserDetailsService userDetailsService(){

        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username)
            {
                return userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
            }
        };

    }

    public AddUserResponse addUser(AddNewUserRequest user){

        Login userLogin = Login.builder()
                .userName(user.getUsername())
                .password(user.getPassword())
                .build();

        User newUser = User.builder()
                                .firstName(user.getFirstName())
                                .lastName(user.getLastName())
                                        .dob(user.getDob())
                                                .address(user.getAddress())
                                                        .login(userLogin)
                                                                .build();
        loginRepo.save(userLogin);
        userRepo.save(newUser);
        return AddUserResponse.builder()
                .userId(newUser.getUserId())
                .username(userLogin.getUserName())
                .password(userLogin.getPassword())
                .message("User Created successfully track the order with below link: "+" http://localhost:8080/trackorder ")
                .build();

    }

    public User getUserDetails(Integer id)throws Exception
    {

        Optional<User> userDetails = userRepo.findById(id);
        return userDetails.get();
    }
    public User updateUserDetails(User userDetails)
    {
        return userRepo.save(userDetails);
    }

    public String deleteUser(Integer id)throws Exception
    {

        Optional<User> user = userRepo.findById(id);
                if(user.isEmpty())
                {
                    throw new Exception("Invalid userId");
                }
        User userDetails = user.get();
        loginRepo.deleteById(userDetails.getLogin().getLoginId());
        userRepo.deleteById(id);
        return "user delete Successfully";
    }
    public ChangePasswordResponse changePassword(UpdateLoginRequest loginRequest) throws Exception
    {
        Login login = loginRepo.findLoginByUserName(loginRequest.getUserName()).get();
        if(login == null || !login.getPassword().equals(loginRequest.getOldPassword()))
        {
            throw new Exception("Old Password Is Incorrect or username is Incorrect Username: "+loginRequest.getUserName());
        }

        if(loginRequest.getNewpassword().length()<8)
        {
            throw new Exception("Password length must be greater then 8");
        }
        login.setPassword(loginRequest.getNewpassword());
        Login newLogin = loginRepo.save(login);
        return ChangePasswordResponse.builder().userName(newLogin.getUserName()).password(newLogin.getPassword()).message("Password Changed Successfully ").build();

    }
}
