//package com.example.board.repository;
//import com.example.board.domain.Author;
//import org.springframework.stereotype.Repository;
//import org.springframework.jdbc.datasource.DataSourceUtils;
//import javax.sql.DataSource;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class JdbcRepository {
//    private final DataSource dataSource;
//
//    public JdbcRepository(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    public void save(Author author){
//        String sql = "insert into author(name) values(?)";
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try{
//
//            conn = getConnection();
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, author.getName());
//            pstmt.executeUpdate();
//        }catch (Exception e){
//            throw new IllegalStateException(e);
//        }
//    }
//
//    public List<Author> findAll() {
//        String sql = "select * from author";
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try{
//
//            conn = getConnection();
//            pstmt = conn.prepareStatement(sql);
//
//            rs = pstmt.executeQuery();
//            List<Author> authors = new ArrayList<>();
//
//            while(rs.next()){
//                Author author = new Author();
//                author.setId(rs.getLong("id"));
//                author.setName(rs.getString("name"));
//                authors.add(author);
//            }
//            return authors;
//        }catch (Exception e){
//            throw new IllegalStateException(e);
//        }
//    }
//
//
//    public Author findById(Long id) {
//        String sql = "select * from author where id = ?";
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try{
//
//            conn = getConnection();
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setLong(1, id);
//
//            rs = pstmt.executeQuery();
//            if(rs.next()) {
//                Author m = new Author();
//                m.setId(rs.getLong("id"));
//                m.setName(rs.getString("name"));
//                return m;
//            }
//            else{
//                return null;
//            }
//        }catch (Exception e){
//            throw new IllegalStateException(e);
//        }
//    }
//
//    private Connection getConnection() {
//        return DataSourceUtils.getConnection(dataSource);
//    }
//}
