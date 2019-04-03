package com.codegym.controller;

import com.codegym.model.*;
import com.codegym.service.CategoryService;
import com.codegym.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/category", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryBrief>> getAllCategories (HttpServletRequest request){
        HttpStatus httpStatus = null;
        List<CategoryBrief> list = new ArrayList<>();
        try {
            Iterable<Category> listCategories = categoryService.findAll();
            for(Category category : listCategories){
                CategoryBrief categoryBrief = new CategoryBrief(category.getName());
                list.add(categoryBrief);
            }
            httpStatus = HttpStatus.OK;
        }catch (Exception ex){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(list, httpStatus);
    }

    @RequestMapping(value = "/post", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PostBrief>> getAllPosts (HttpServletRequest request){
        HttpStatus httpStatus = null;
        List<PostBrief> list = new ArrayList<>();
        try{
            Iterable<Post> listPosts = postService.findAll();
            for(Post post : listPosts){
                PostBrief postBrief = new PostBrief(post.getAuthor(), post.getName());
                list.add(postBrief);
            }
            httpStatus = HttpStatus.OK;
        }catch (Exception ex){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(list, httpStatus);
    }

    @RequestMapping(value = "/post/category/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PostWithCategory>> getAllPostsByCategory(@PathVariable int id){
        HttpStatus httpStatus = null;
        List<PostWithCategory> list = new ArrayList<>();

        try{
            Optional<Iterable<Post>> posts = postService.findAllPostsByCategoryId(id);
            if(posts.isPresent()){
                for(Post post : posts.get()){
                    PostWithCategory postWithCategory = new PostWithCategory(post.getName(), post.getAuthor(), post.getContent(), post.getCategory().getName());
                    list.add(postWithCategory);
                }
                httpStatus = HttpStatus.OK;
            }else{
                httpStatus = HttpStatus.BAD_REQUEST;
            }

        }catch (Exception ex){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(list, httpStatus);
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostWithCategory> getPostById(@PathVariable int id){
        PostWithCategory post = null;
        HttpStatus httpStatus = null;
        try{
            Post postDB = postService.findById(id);
            post = new PostWithCategory(postDB.getName(), postDB.getAuthor(), postDB.getContent(), postDB.getCategory().getName());
            httpStatus = HttpStatus.OK;
        }catch (Exception ex){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(post, httpStatus);
    }
}
