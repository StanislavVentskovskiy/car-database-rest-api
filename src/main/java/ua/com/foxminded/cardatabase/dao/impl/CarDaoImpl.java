package ua.com.foxminded.cardatabase.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.cardatabase.dao.CarDao;
import ua.com.foxminded.cardatabase.dao.repository.CarRepository;
import ua.com.foxminded.cardatabase.model.Car;
import java.util.List;
import java.util.Optional;

@Repository
public class CarDaoImpl implements CarDao {
    private final CarRepository carRepository;

    @Autowired
    public CarDaoImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void deleteCar(String carId) {
        carRepository.deleteById(carId);
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(Car car) {
        return carRepository.save(car);
    }

    public Optional<Car> getCar(String carId) {
        return carRepository.findById(carId);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public List<Car> searchCars(String make, Integer minYear) {
        return carRepository.searchCars(make, minYear);
    }
}
