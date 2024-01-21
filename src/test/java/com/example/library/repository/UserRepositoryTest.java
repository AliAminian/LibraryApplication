package com.example.library.repository;

import com.example.library.model.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection =  EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {

    private final UserRepository repository;

    @Autowired
    public UserRepositoryTest(UserRepository repository) {
        this.repository = repository;
    }

    @Test
    public void UserRepository_FindByName_ReturnFoundUser() {
        // GIVEN
        UserEntity user = UserEntity.builder()
                .id(1)
                .username("username")
                .password("password")
                .build();

        // WHEN


        // THEN
        UserEntity actual = repository.save(user);

        // ASSERT
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getId()).isEqualTo(1);
    }

    @Test
    public void existsByUsername() {
    }
}