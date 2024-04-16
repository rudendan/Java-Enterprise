package com.example.homework6.repository.JPA;

import com.example.homework6.model.User;
import com.example.homework6.repository.interfaces.UserRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(value = "useJPA", havingValue = "true")
public interface UserRepositoryImpl extends JpaRepository<User, Long>, UserRepository {

}
