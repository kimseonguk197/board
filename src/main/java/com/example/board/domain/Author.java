package com.example.board.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


//@Setter
@Getter
@Entity
//초기화 되지 않은 final 필드와 @NonNull 어노테이션이 붙은 필드에 대한 생성자 생성
//@RequiredArgsConstructor
// 모든 필드에 대한 생성자 생성.
//@AllArgsConstructor
//어노테이션은 파라미터가 없는 기본 생성자를 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Author {

//    Entity로 선언을 할때는, pk가 어떤건지를 정확하게 지정해줘야 한다.
//    Author_basic까지 수행하고 나면 Lombok, Auto-ddl 등으로 변경후 advanced 진행

    //Entity로 선언을 할때는, pk가 어떤건지를 정확하게 지정해줘야 한다.
    @Id @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50, unique = true)
    private String email;

    @Column(length = 100)
    private String password;

//    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private String role;

    @Column
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "author_id", cascade = CascadeType.ALL)
    private List<Post> posts;

////    Builder와 생성자의 차이
////    1)생성자의 경우는 정해진 파라미터 순서대로 꼭 값을 넣어줘야하지만 빌더는 아니다.
////    2)생성자 파라미터가 많을 경우 가독성이 좋지 않다.
    @Builder
    public Author(String name, String email, String password, String role){
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createDate = LocalDateTime.now();
    }

}
