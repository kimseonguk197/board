package com.example.board.service;

import com.example.board.domain.Author;
import com.example.board.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

//Transactional을 통해 롤백
@Transactional
@SpringBootTest
class AuthorServiceIntegrationTest {
    @Autowired
    AuthorService authorService;
    @Autowired
    AuthorRepository authorRepository;


    @Test
//    @Commit
    void 회원가입() {
        //given
        Author member = new Author();
        member.setName("seonguk1");
        member.setEmail("seonguk@test.com");
        //when
        authorService.create(member);

        //then
        assertThat(authorService.findByEmail("seonguk@test.com").get().getName()).isEqualTo(member.getName());
    }

    //예외1보다 아래 예외2 방법의 사용추천
    @Test
    void 회원모두찾기() {
        //given

        Author member = new Author();
        member.setName("seonguk1");
        member.setEmail("seonguk@test.com");

        Author member2 = new Author();
        member2.setName("seonguk2");
        member2.setEmail("seonguk2@test.com");
        //when
        authorService.create(member);
        authorService.create(member2);
        List<Author> lst = new ArrayList<>();
        lst.add(member);
        lst.add(member2);
//        Author member3 = new Author();
//        member3.setName("seonguk3");
//        member3.setEmail("seonguk3@test.com");
//        lst.add(member3);
        for(Author a : lst){
            assertThat(authorService.findAll()).contains(a);
        }
    }

}