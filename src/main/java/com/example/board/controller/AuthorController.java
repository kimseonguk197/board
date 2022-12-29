package com.example.board.controller;

import com.example.board.domain.Author;
import com.example.board.domain.Role;
import com.example.board.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class AuthorController {

//    Autowired를 통해 스프링컨테이너에 등록된 service객체를 가져다 쓰게 된다. 다만
//    생성자가 하나만 정의되어 있고 스프링 빈이라면 @Autowired 어노테이션 생략 가능
//    그래서 아래와 같이 Service호출
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors/new")
    public String createForm(){
        return "authors/createAuthorForm";
    }
//    회원가입시 많은 데이터들이 넘어올때는 post방식을
    @PostMapping("/authors/new")
    public String create(AuthorPostForm authorPostForm){
        Author author = new Author();
        author.setName(authorPostForm.getName());
        author.setEmail(authorPostForm.getEmail());
        author.setPassword(authorPostForm.getPassword());
        author.setCreateDate(LocalDateTime.now());
        if(authorPostForm.getRole().equals("user")){
            author.setRole(Role.USER);
        }else{
            author.setRole(Role.ADMIN);
        }
//        회원가입로직
        authorService.create(author);
        return "redirect:/";
    }


    //화면에다가 db에서 조회한 값을 넘겨주려면 어떻게?!
    @GetMapping("/authors")
    public String authorList(Model model){
//        key, value 값으로 넘겨줘야한다.
        model.addAttribute("authors", authorService.findAll());
        return "authors/authorList";
    }

    @GetMapping("authors/findById")
    public String findById(@RequestParam(value="id")Long id, Model model){
        model.addAttribute("author",  authorService.findById(id).orElse(null));

        return "authors/authorDetail";
    }

}
