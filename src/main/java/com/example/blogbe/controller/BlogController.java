package com.example.blogbe.controller;

import com.example.blogbe.config.custom.S3Util;
import com.example.blogbe.model.blog.Blog;
import com.example.blogbe.model.blog.BlogForm;
import com.example.blogbe.repo.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/blog")
@CrossOrigin("*")
public class BlogController {
    @Autowired
    private BlogRepo blogRepo;

    @PostMapping
    public ResponseEntity<?> createBlog(@ModelAttribute BlogForm blogForm) throws IOException {
        List<Blog> blogs = blogRepo.findAll();
        for (Blog blog : blogs) {
            if (blog.getTitle().equals(blogForm.getTitle())) {
                return ResponseEntity.ok("Exist");
            }
        }
        Blog blog = new Blog();
        String fileName = blogForm.getFileImage().getOriginalFilename();
        S3Util.uploadFile(fileName, blogForm.getFileImage().getInputStream());
        blog.setTitle(blogForm.getTitle());
        blog.setContent(blogForm.getContent());
        blog.setImage(fileName);
        blogRepo.save(blog);
        return ResponseEntity.ok("Done");
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateBlog(@ModelAttribute BlogForm blogForm) throws IOException {
        Blog blog = blogRepo.findById(blogForm.getId()).get();
        if (blogForm.getFileImage() != null) {
            String fileName = blogForm.getFileImage().getOriginalFilename();
            S3Util.uploadFile(fileName, blogForm.getFileImage().getInputStream());
            blog.setImage(fileName);
            blog.setTitle(blogForm.getTitle());
            blog.setContent(blogForm.getContent());
            blogRepo.save(blog);
        } else {
            blog.setTitle(blogForm.getTitle());
            blog.setContent(blogForm.getContent());
            blogRepo.save(blog);
        }
        return ResponseEntity.ok("Xong");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlogById(@PathVariable Long id){
        blogRepo.deleteById(id);
        return ResponseEntity.ok("Xong");
    }
}
