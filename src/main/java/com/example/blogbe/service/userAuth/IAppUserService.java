package com.example.blogbe.service.userAuth;

import com.example.blogbe.model.userAuth.User;
import com.example.blogbe.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAppUserService extends IGeneralService<User>, UserDetailsService {
}
