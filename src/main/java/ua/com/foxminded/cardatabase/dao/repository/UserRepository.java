package ua.com.foxminded.cardatabase.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.cardatabase.model.UserEntity;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findByUsername(String username);
}
