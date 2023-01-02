package com.example.board.service;

import com.example.board.domain.Author;
import com.example.board.domain.Post;
import com.example.board.repository.AuthorRepository;
import com.example.board.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//Transactional을 통해 롤백
@Transactional
@SpringBootTest
class PostServiceIntegrationTest {
    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;
    @Autowired
    AuthorService authorService;
    @Autowired
    AuthorRepository authorRepository;


    @Test
//    @Commit
    void 게시글등록() {
        //given
        Author member = new Author();
        member.setName("seonguk1");
        member.setEmail("seonguk@test.com");
        //when
        authorService.create(member);
        //given
        Post post1 = new Post();
        post1.setTitle("post1");
        post1.setContents("post1Contents");
//        post1.setAuthor_id(member);
        //when
        postService.create(post1);

        //then
        assertThat(postService.findByTitle("post1").orElse(null).getTitle()).isEqualTo(post1.getTitle());
    }


}