//package com.example.board.repository;
//
//import com.example.board.domain.Author;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Repository
//public class MemoryRepository {
//
//    private static Map<Long, Author> store = new HashMap<>();
////    => DB로 바꿀것이다. h2 DB : java생태계에서 가볍게 테스트용도로 쓰는 DB
//    private static long sequence = 0L;
//
//    public void save(Author author){
////        메모리 변수
//        ++sequence;
//        author.setId(sequence);
//        store.put(sequence, author);
//        System.out.println(store);
//    }
//
//    public List<Author> findAll(){
//        return new ArrayList<>(store.values());
//    }
//
//}
