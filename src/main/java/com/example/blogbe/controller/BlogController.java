package com.example.blogbe.controller;

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

@Controller
@RequestMapping("/blog")
@CrossOrigin("*")
public class BlogController {
    @Autowired
    private BlogRepo blogRepo;

    @Value("${upload.path}")
    private String fileUpload;

    @PostMapping
    public ResponseEntity<?> createBlog(@ModelAttribute BlogForm blogForm){
        Blog blog = new Blog();
        MultipartFile multipartFile = blogForm.getFileImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(blogForm.getFileImage().getBytes(), new File(this.fileUpload + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        blog.setTitle(blogForm.getTitle());
        blog.setContent(blogForm.getContent());
        blog.setImage(fileName);
        return ResponseEntity.ok(blogRepo.save(blog));
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateBlog(@ModelAttribute BlogForm blogForm){
        Blog blog = blogRepo.findById(blogForm.getId()).get();
        if (blogForm.getFileImage() != null) {
            String fileName = blogForm.getFileImage().getOriginalFilename();
            try {
                FileCopyUtils.copy(blogForm.getFileImage().getBytes(), new File(this.fileUpload + fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
