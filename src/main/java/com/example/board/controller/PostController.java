package com.example.board.controller;

import com.example.board.domain.Author;
import com.example.board.domain.Post;
import com.example.board.service.AuthorService;
import com.example.board.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class PostController {

    private final PostService postService;
    private final AuthorService authorService;

    public PostController(PostService postService, AuthorService authorService) {
        this.postService = postService;
        this.authorService = authorService;
    }

    @GetMapping("/posts/new")
    public String createForm(){
        return "posts/createPostForm";
    }

    @PostMapping("/posts/new")
    public String create(PostForm postForm){
        Post post = new Post();
        post.setTitle(postForm.getTitle());
        post.setContents(postForm.getContents());
        post.setEmail(postForm.getEmail());
        post.setCreateDate(LocalDateTime.now());
        Author a1 = authorService.findByEmail(postForm.getEmail()).orElse(null);
        post.setAuthor_id(a1);
        postService.create(post);
        return "redirect:/";
    }

    @GetMapping("/posts")
    public String postList(Model model){
//        key, value 값으로 넘겨줘야한다.
        model.addAttribute("posts", postService.findAll());
        return "posts/postList";
    }

    @GetMapping("posts/findById")
    public String findById(@RequestParam(value="id")Long id, Model model){
        model.addAttribute("post",  postService.findById(id).orElse(null));

        return "posts/postDetail";
    }

}
