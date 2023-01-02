package com.example.board.repository;
import com.example.board.domain.Author;
import com.example.board.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


//DataJpaTest 사용시
//@Transactional을 기본적으로 내장하고 있으므로, 매 테스트 코드가 종료되면 자동으로 DB가 롤백
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository repository;

    @Test
    public void save(){
        Author author = new Author();
        author.setName("junitName");
        author.setEmail("junitEmail");
        repository.save(author);

        //.get()은 optional객체에서 값을 꺼내는 방법.
        Author repoMember = repository.findByEmail(author.getEmail()).get();
        //확인 방법1.
//        System.out.println("result : " + ( repoMember == author));
        //방법2. : 매번 글자로 확인할 수는 없다. junit.jupiter의 assertions 사용
        //실행시, 정상적인 파란불
//        Assertions.assertEquals(author, repoMember );
        //방법3.
        //실행시, 정상적인 파란불
        assertThat(author).isEqualTo(repoMember);
    }

}
