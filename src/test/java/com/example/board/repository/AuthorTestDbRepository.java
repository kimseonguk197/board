package com.example.board.repository;
import com.example.board.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
//application-test.yml을 사용하기 위한 설정
//그러나, 아래 코드는 H2의 foreginkey naming 방식의 Mysql간의 차이로 인해 오류 발생
@ActiveProfiles("test")
class AuthorTestDbRepository {
    @Autowired
    private AuthorRepository repository;
    @Test
    public void save(){
        Author author = new Author();
        author.setName("junitName");
        author.setEmail("junitEmail");
        repository.save(author);
        Author repoMember = repository.findByEmail(author.getEmail()).get();
        assertThat(author).isEqualTo(repoMember);
    }

}
