package com.example.board.controller;

import com.example.board.domain.Author;
import com.example.board.domain.Role;
import com.example.board.service.AuthorService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
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

//        값 세팅 방법1. setter : setter사용의 문제점은 데이터를 필요할때 변경해버릴수 있다는 것.
//        예를 들어, controller이후에 누가 service에 setter를 통해 데이터를 변경하는 로직을 추가 했다고 하자.
//        setter가 여기저기 쓰이기 시작하면, 유지보수가 점점 힘들어진다.
//        Author author = new Author();
//        author.setName(authorPostForm.getName());
//        author.setEmail(authorPostForm.getEmail());
//        author.setPassword(authorPostForm.getPassword());
//        author.setCreateDate(LocalDateTime.now());
//        if(authorPostForm.getRole().equals("user")){
//            author.setRole(Role.USER);
//        }else{
//            author.setRole(Role.ADMIN);
//        }
//        회원가입로직

//        방법2. 생성자 :
//        Author author = new Author();
//        Author author = new Author(authorPostForm.getName, authorPostForm.getEmail, authorPostForm.getPassword, authorPostForm.getRole);

//        방법3. builder : 생성자는 순서 및 갯수도 틀어지면 안되고, 가독성이 떨어진다.
//        내부적으로 static하게 만들어진 method와 동일한 것이라 아래와 같이 사용
//        내부적으로 static하게 만들어진 builder메서드를 호출하면 builder클래스의 객체를 반환
//        해당 클래스의 password(), name() 등의 메서드를 사용해서 값을 세팅 후
//        build메서드를 호출하여 Author객체를 반환
        Author author = Author.builder()
                .password(authorPostForm.getPassword())
                .name(authorPostForm.getName())
                .email(authorPostForm.getEmail())
                .build();
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
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String findById(@RequestParam(value="id")Long id, Model model) throws Exception {
//        model.addAttribute("author",  authorService.findById(id).orElse(null));
//        위와 같이 처리 하면 Controller단위에서 에러가 나는 것이 아닌 null을 화면으로 넘겨 화면에서 에러가 나게 되는 상황
//        또한 Exception처리를 해주지 않고, null로만 return하면 기본 httperror메세지도 나가지 않는다.
//        model.addAttribute("author",  authorService.findById(id).orElseThrow(Exception::new));
//        위와 같이 기본 Exception처리만 하게 되면, 어떤 에러인지 알기가 어렵다. 또한 메서드 단위로  전부 별도로 예외처리를 해주어야함.
//        또한 상태값도 500으로 고정된다.
//        아래도 메시지는 개선하였으나, 상태값은 마찬가지이다.
//        이를 위해 AOP를 적용한다.
        try {
            model.addAttribute("author", authorService.findById(id).orElseThrow(Exception::new));
        }catch (Exception e){
             throw new EntityNotFoundException("postList EntityNotFoundException " + e.getMessage());
        }
//        model.addAttribute("author",  (ResponseEntity<Object>::
//                new ResponseEntity<Object>(
//                "no entity", new HttpHeaders(), HttpStatus.NOT_FOUND));


        return "authors/authorDetail";
    }

}
