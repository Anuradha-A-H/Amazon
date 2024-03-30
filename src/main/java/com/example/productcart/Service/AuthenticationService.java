package com.example.productcart.Service;

import com.example.productcart.Entities.Login;
import com.example.productcart.Entities.User;
import com.example.productcart.Enums.Role;
import com.example.productcart.Repository.LoginRepository;
import com.example.productcart.Repository.UserRepository;
import com.example.productcart.RequestDoc.RefreshTokenRequest;
import com.example.productcart.RequestDoc.SignUpRequest;
import lombok.RequiredArgsConstructor;
import com.example.productcart.RequestDoc.SigninRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.productcart.Response.JwtAuthenticationResponse;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    private JavaMailSender javaMailSender;
    public User signUp(SignUpRequest signUpRequest)
    {
        Login login = new Login();

        String[] req = signUpRequest.getEmail().split("@");
        login.setUserName(req[0]);
        login.setPassword(passwordEncoder.encode(req[0]+"@123"));
        Login logindtl = loginRepository.save(login);
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setRole(signUpRequest.getRole());
        user.setLogin(logindtl);
        user.setDob(signUpRequest.getDob());
        user.setAddress(signUpRequest.getAddress());
        userRepository.save(user);
        sendMailtoCustomer(user,logindtl);
        return user;
    }

    public JwtAuthenticationResponse signin(SigninRequest request)
    {
        System.out.println(request.getUserName()+" "+request.getPassword());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getPassword(),request.getPassword()));


        var login = loginRepository.findLoginByUserName(request.getUserName()).orElseThrow(()->new IllegalArgumentException("Invalid Email Or Password."));

        User log = userRepository.findByLogin(login).orElseThrow(()->new IllegalArgumentException("Invalid Email Or Password."));
        var jwt = jwtService.generateToken(log);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),log);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;

    }


    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest request){


        String userEmail = jwtService.extractUserName(request.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if( jwtService.isTokenValid(request.getToken(),user))
        {   System.out.println(request.getToken());
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(request.getToken());
            return jwtAuthenticationResponse;

        }
        return null;


    }


    private void sendMailtoCustomer(User user,Login request){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Registration (Attitude Collection Portal)");
        message.setFrom("anuradhaah667@gmail.com");
        message.setTo(user.getEmail());
        String body = "Dear "+user.getFirstName()+" "+user.getLastName()+"\n" +
                "Your profile has been successfully created on http://localhost:8080/rest/auth/login\n" +
                "\n" +
                "Now you can login on above link with the help of your reference ID.\n" +
                "\n" +
                "Your UserName is :"+ request.getUserName() +"\n" +
                "\n" +
                "\n" +
                "Your password is :"+request.getPassword();
        message.setText(body);
        javaMailSender.send(message);

    }




}
