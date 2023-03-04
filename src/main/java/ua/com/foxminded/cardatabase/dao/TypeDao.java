package ua.com.foxminded.cardatabase.dao;

import ua.com.foxminded.cardatabase.model.Type;

import java.util.List;
import java.util.Optional;

public interface TypeDao {

    void deleteType(int typeId);

    Type addType(Type type);

    Optional<Type> updateType(Type type);

    Optional<Type> getType(int typeId);

    Optional<Type> getTypeByName(String name);

    List<Type> getAllTypes();
}
