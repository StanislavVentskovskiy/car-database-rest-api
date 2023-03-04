package ua.com.foxminded.cardatabase.dao;

import ua.com.foxminded.cardatabase.model.Make;
import java.util.List;
import java.util.Optional;

public interface MakeDao {

    void deleteMake(int makeId);

    Make addMake(Make make);

    Optional<Make> updateMake(Make make);

    Optional<Make> getMake(int makeId);

    Optional<Make> getMakeByName(String make);

    List<Make> getAllMakes();
}
