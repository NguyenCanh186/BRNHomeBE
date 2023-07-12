package com.example.blogbe.service.home;

import com.example.blogbe.model.home.Home;
import com.example.blogbe.repo.HomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class HomeService implements IHomeService {
    @Autowired
    private HomeRepo homeRepo;
    @Override
    public List<Home> findAll() {
        return homeRepo.findAll();
    }

    @Override
    public Optional<Home> findById(Long id) {
        return homeRepo.findById(id);
    }

    @Override
    public Home save(Home home) {
        return homeRepo.save(home);
    }

    @Override
    public void remove(Long id) {
        homeRepo.deleteById(id);
    }
}
