package ua.com.foxminded.cardatabase.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.cardatabase.dao.impl.CarDaoImpl;
import ua.com.foxminded.cardatabase.model.Car;
import ua.com.foxminded.cardatabase.service.CarService;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    private final CarDaoImpl carDao;

    @Autowired
    public CarServiceImpl(CarDaoImpl carDao) {
        this.carDao = carDao;
    }

    public void deleteCar(String carId){
        carDao.deleteCar(carId);
    }

    public Car addCar(Car car){
        return carDao.addCar(car);
    }

    public Car updateCar(Car car) {
        Car other = carDao.getCar(car.getId()).get();
        if (car.getYear() != null) {
            other.setYear(car.getYear());
        }
        if (car.getMake() != null){
            other.setMake(car.getMake());
        }
        if (car.getModel() != null) {
            other.setModel(car.getModel());
        }
        if (car.getType() != null){
            other.setType(car.getType());
        }
        return carDao.updateCar(other);
    }

    public Optional<Car> getCar(String carId){
        return carDao.getCar(carId);
    }

    public List<Car> getAllCars() {
        return carDao.getAllCars();
    }

    public List<Car> searchCars(String make, Integer minYear) {
        return carDao.searchCars(make, minYear);
    }
}
