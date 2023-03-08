package ua.com.foxminded.cardatabase.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.cardatabase.dao.impl.MakeDaoImpl;
import ua.com.foxminded.cardatabase.model.Make;
import ua.com.foxminded.cardatabase.service.MakeService;
import java.util.List;
import java.util.Optional;

@Service
public class MakeServiceImpl implements MakeService {
    private final MakeDaoImpl makeDao;

    @Autowired
    public MakeServiceImpl(MakeDaoImpl makeDao) {
        this.makeDao = makeDao;
    }

    public void deleteMake(int makeId){
        makeDao.deleteMake(makeId);
    }

    public Make addMake(Make make){
        return makeDao.addMake(make);
    }

    public Make updateMake(Make make){
        Make other = makeDao.getMake(make.getId()).get();
        if (make.getName() != null) {
            other.setName(make.getName());
        }

        return makeDao.updateMake(make);
    }

    public Optional<Make> getMake(int makeId){
        return makeDao.getMake(makeId);
    }

    public Optional<Make> getMakeByName(String name){
        return makeDao.getMakeByName(name);
    }

    public List<Make> getAllMakes() {
        return makeDao.getAllMakes();
    }
}
