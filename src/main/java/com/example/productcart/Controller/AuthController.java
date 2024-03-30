package com.example.productcart.Controller;


import com.example.productcart.Entities.Login;
import com.example.productcart.Entities.User;
import com.example.productcart.RequestDoc.LoginRequest;
import com.example.productcart.RequestDoc.RefreshTokenRequest;
import com.example.productcart.RequestDoc.SignUpRequest;
import com.example.productcart.RequestDoc.SigninRequest;
import com.example.productcart.Response.ErrorResponse;
import com.example.productcart.Response.JwtAuthenticationResponse;
import com.example.productcart.Response.LoginResponse;
import com.example.productcart.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest request){
        return ResponseEntity.ok(authenticationService.signUp(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest signinRequest){

        return ResponseEntity.ok(authenticationService.signin(signinRequest));


    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest signinRequest){
        System.out.println(signinRequest.getToken());
        return ResponseEntity.ok(authenticationService.refreshToken(signinRequest));
        //return null;
    }


}
