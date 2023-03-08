package ua.com.foxminded.cardatabase.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.cardatabase.model.Make;
import java.util.Optional;

@Repository
public interface MakeRepository extends JpaRepository<Make, Integer> {
    Optional<Make> findByName(String name);
}
