package ua.com.foxminded.cardatabase.service;

import ua.com.foxminded.cardatabase.model.Make;
import java.util.List;
import java.util.Optional;

public interface MakeService {

    void deleteMake(int makeId);

    Make addMake(Make make);

    Make updateMake(Make make);

    Optional<Make> getMake(int makeId);

    Optional<Make> getMakeByName(String name);

    List<Make> getAllMakes();
}
