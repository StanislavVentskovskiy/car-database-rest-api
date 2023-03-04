package ua.com.foxminded.cardatabase.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.cardatabase.model.Car;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
    @Query("select c FROM Car c where c.make.name = :make and c.year > :minYear")
    List<Car> searchCars(String make, Integer minYear);
}
