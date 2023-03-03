package com.example.board.domain;


import java.time.LocalDateTime;
import java.util.List;

public class AuthorBuilderExample {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private LocalDateTime createDate;
    private List<Post> posts;


    // 클래스 내에 static 형태의 내부 클래스(inner class) 생성
    protected static class Builder{
        private String name;
        private String email;
        private String password;
        private String role;
        private LocalDateTime createDate;
        private List<Post> posts;

        //name 입력값 받음 : return type을 Builder로 지정 후 this return
        public Builder name(String value) {
            name = value;
            return this;
        }

        //grade 입력값 받음
        public Builder email(String value) {
            email = value;
            return this;
        }

        //age 입력값 받음
        public Builder role(String value) {
            role = value;
            return this;
        }

        // build() 메소드 실행하면 this가 return 되도록 지정
        public AuthorBuilderExample build() {
            return new AuthorBuilderExample(this);
        }
    }

    // 생성자를 private으로 함
    // 외부에서 접근 x + Builder 클래스에서 사용 가능
    private AuthorBuilderExample(Builder builder){
        name = builder.name;
        email = builder.email;
        password = builder.password;
    }

    // 빌더 소환 : 외부에서 Person.builder() 형태로 접근 가능하게 static method로 생성
    public static Builder builder() {
        return new Builder();
    }

    //Geter 생략
}
