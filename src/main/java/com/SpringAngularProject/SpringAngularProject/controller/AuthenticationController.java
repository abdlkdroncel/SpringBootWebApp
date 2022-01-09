package com.SpringAngularProject.SpringAngularProject.controller;

import com.SpringAngularProject.SpringAngularProject.model.User;
import com.SpringAngularProject.SpringAngularProject.service.IAuthenticationService;
import com.SpringAngularProject.SpringAngularProject.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/authentication")
public class AuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    private IUserService userService;

    @PostMapping("sign-up")
    public ResponseEntity<?> singUp(@RequestBody User user){
        if(userService.findByUserName(user.getUsername()).isPresent()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED);
    }

    @PostMapping("sign-in")
    public ResponseEntity<?> signIn(@RequestBody User user){
        return new ResponseEntity<>(authenticationService.signInReturnJWT(user),HttpStatus.OK);
    }
}
