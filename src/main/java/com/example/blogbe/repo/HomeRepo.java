package com.example.blogbe.repo;

import com.example.blogbe.model.home.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRepo extends JpaRepository<Home, Long> {
}
