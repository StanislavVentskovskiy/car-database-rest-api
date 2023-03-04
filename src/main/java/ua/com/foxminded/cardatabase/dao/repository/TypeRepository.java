package ua.com.foxminded.cardatabase.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.cardatabase.model.Type;
import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {
    Optional<Type> getTypeByName(String name);
}
