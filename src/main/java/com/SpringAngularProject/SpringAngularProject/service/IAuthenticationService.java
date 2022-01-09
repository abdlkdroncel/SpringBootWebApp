package com.SpringAngularProject.SpringAngularProject.service;

import com.SpringAngularProject.SpringAngularProject.model.User;

public interface IAuthenticationService {
    User signInReturnJWT(User signInRequest);
}
