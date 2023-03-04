package ua.com.foxminded.cardatabase.service;

import ua.com.foxminded.cardatabase.model.Car;
import java.util.List;
import java.util.Optional;

public interface CarService {

    void deleteCar(String carId);

    Car addCar(Car car);

    Car updateCar(Car car);

    Optional<Car> getCar(String carId);

    List<Car> getAllCars();
}
