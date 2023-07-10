package com.example.blogbe.service;

import com.example.blogbe.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAppUserService extends IGeneralService<User>, UserDetailsService {
}
