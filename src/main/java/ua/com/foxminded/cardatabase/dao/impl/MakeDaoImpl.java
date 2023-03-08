package ua.com.foxminded.cardatabase.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.cardatabase.dao.MakeDao;
import ua.com.foxminded.cardatabase.dao.repository.MakeRepository;
import ua.com.foxminded.cardatabase.model.Make;
import java.util.List;
import java.util.Optional;

@Repository
public class MakeDaoImpl implements MakeDao {
    private final MakeRepository makeRepository;

    @Autowired
    public MakeDaoImpl(MakeRepository makeRepository) {
        this.makeRepository = makeRepository;
    }

    public void deleteMake(int makeId) {
        makeRepository.deleteById(makeId);
    }

    public Make addMake(Make make) {
        return makeRepository.save(make);
    }

    public Make updateMake(Make make) {
        return makeRepository.save(make);
    }

    public Optional<Make> getMake(int makeId) {
        return makeRepository.findById(makeId);
    }

    public Optional<Make> getMakeByName(String name){
        return makeRepository.findByName(name);
    }

    public List<Make> getAllMakes(){
        return makeRepository.findAll();
    }
 }
