//package com.example.board.repository;
//import com.example.board.domain.Author;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class JpaRepository {
//
//    private final EntityManager em;
//
//    public JpaRepository(EntityManager em) {
//        this.em = em;
//    }
//    public void save(Author author){
//        em.persist(author);
//    }
//    public Author findById(Long id){
//        Author m = em.find(Author.class, id);
//        return m;
//    }
//    public List<Author> findAll() {
////        pk가 아닌값으로 조회할때는 jpa에서 쓰는 독특한 jpql라고 하는 객체지향 언어를 사용한다.
////        여기서 m은 alias라고 부르고, 영어로 따지면 as와 동일한 문법이다.
////        Author as m
//        List<Author> authors = em.createQuery("select m from Author m", Author.class).getResultList();
//        return authors;
//    }
//
//}
