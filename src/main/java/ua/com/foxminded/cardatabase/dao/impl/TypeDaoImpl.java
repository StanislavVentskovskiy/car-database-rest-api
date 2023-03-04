package ua.com.foxminded.cardatabase.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.cardatabase.dao.TypeDao;
import ua.com.foxminded.cardatabase.dao.repository.TypeRepository;
import ua.com.foxminded.cardatabase.model.Type;
import java.util.List;
import java.util.Optional;

@Repository
public class TypeDaoImpl implements TypeDao {
    private final TypeRepository typeRepository;

    @Autowired
    public TypeDaoImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public void deleteType(int typeId){
        typeRepository.deleteById(typeId);
    }

    public Type addType(Type type){
        return typeRepository.save(type);
    }

    public Optional<Type> updateType(Type type){
        Optional<Type> other = typeRepository.findById(type.getId());
        if(other.isPresent()){
            other.get().setName(type.getName());
            typeRepository.save(other.get());
        }

        return other;
    }

    public Optional<Type> getType(int typeId){
        return typeRepository.findById(typeId);
    }

    public Optional<Type> getTypeByName(String name){
        return typeRepository.getTypeByName(name);
    }

    public List<Type> getAllTypes(){
        return typeRepository.findAll();
    }
}
