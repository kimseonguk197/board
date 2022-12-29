package com.example.board.service;

import com.example.board.domain.Author;
//import com.example.board.repository.JpaRepository;
//import com.example.board.repository.JdbcRepository;
//import com.example.board.repository.JdbcRepository;
//import com.example.board.repository.MemoryRepository;
//import com.example.board.repository.SpringDataJpaRepository;
import com.example.board.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorService {

//    외부 접근 불가능
    private final AuthorRepository repository;


//    생성자
    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public List<Author> findAll(){
        List<Author> result = repository.findAll();
        return result;
    }

    public void create(Author author){
        repository.save(author);
    }

//    public Author findById(Long Id){
//        return repository.findById(Id);
//    }

//    SpringDataJpaRepository일경우
    public Optional<Author> findById(Long memberId){
        return repository.findById(memberId);
    }

    public Optional<Author> findByEmail(String email){
        return repository.findByEmail(email);
    }
}
