package com.example.blogbe.service;

import com.example.blogbe.model.User;
import com.example.blogbe.model.UserPrinciple;
import com.example.blogbe.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService implements IAppUserService{

    @Autowired
    private UserRepository appUserRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<User> findAll() {
        return appUserRepo.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return appUserRepo.findById(id);
    }

    @Override
    public User save(User appUser) {
        String password = appUser.getPassword();
        String encodePassword = passwordEncoder.encode(password);//Mã hóa pass của người dùng
        appUser.setPassword(encodePassword);
        return appUserRepo.save(appUser);
    }

    public User saveForm(User appUser) {
        return appUserRepo.save(appUser);
    }

    @Override
    public void remove(Long id) {
        appUserRepo.deleteById(id);
    }

    public User findByUsername(String username) {
        return appUserRepo.findByUsername(username);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userOptional = appUserRepo.findByUsername(username);
        if (userOptional != null) {
            return UserPrinciple.build(userOptional);
        }
        return null;
    }

}
