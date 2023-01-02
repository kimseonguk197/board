package com.example.board.service;

import com.example.board.domain.Post;
import com.example.board.repository.PostRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {

//    외부 접근 불가능
    private final PostRepository repository;


//    생성자
    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> findAll(){
        List<Post> result = repository.findAll();
        return result;
    }

    public void create(Post post){
        repository.save(post);
    }

    public Optional<Post> findById(Long memberId){
        return repository.findById(memberId);
    }

    public Optional<Post> findByTitle(String title){
        return repository.findByTitle(title);
    }
}
