package ua.com.foxminded.cardatabase.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.cardatabase.dao.impl.TypeDaoImpl;
import ua.com.foxminded.cardatabase.model.Type;
import ua.com.foxminded.cardatabase.service.TypeService;
import java.util.List;
import java.util.Optional;

@Service
public class TypeServiceImpl implements TypeService {
    private final TypeDaoImpl typeDao;

    @Autowired
    public TypeServiceImpl(TypeDaoImpl typeDao) {
        this.typeDao = typeDao;
    }

    public void deleteType(int typeId){
        typeDao.deleteType(typeId);
    }

    public Type addType(Type type){
        return typeDao.addType(type);
    }

    public Type updateType(Type type){
        Type other = typeDao.getType(type.getId()).get();
        if (type.getName() != null){
            other.setName(type.getName());
        }

        return typeDao.updateType(other);
    }

    public Optional<Type> getType(int typeId){
        return typeDao.getType(typeId);
    }

    public Optional<Type> getTypeByName(String name){
        return typeDao.getTypeByName(name);
    }

    public List<Type> getAllTypes() {
        return typeDao.getAllTypes();
    }
}
