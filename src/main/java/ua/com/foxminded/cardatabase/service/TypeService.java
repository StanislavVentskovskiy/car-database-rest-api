package ua.com.foxminded.cardatabase.service;

import ua.com.foxminded.cardatabase.model.Type;

import java.util.List;
import java.util.Optional;

public interface TypeService {

    void deleteType(int typeId);

    Type addType(Type type);

    Optional<Type> updateType(Type type);

    Optional<Type> getType(int typeId);

    Optional<Type> getTypeByName(String type);

    List<Type> getAllTypes();
}
