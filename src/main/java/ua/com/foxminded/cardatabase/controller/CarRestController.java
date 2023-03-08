package ua.com.foxminded.cardatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.cardatabase.model.Car;
import ua.com.foxminded.cardatabase.service.impl.CarServiceImpl;
import java.util.ArrayList;
import java.util.List;
import static ua.com.foxminded.cardatabase.url.UrlContainer.*;

@RestController
public class CarRestController {
    public final CarServiceImpl carService;

    @Autowired
    public CarRestController(CarServiceImpl carService) {
        this.carService = carService;
    }

    @GetMapping(getAllCars)
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = new ArrayList<>();
        cars = carService.getAllCars();

        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping(getSingleCar + "{id}")
    public ResponseEntity<Car> getSingleCar(@PathVariable("id") String carId) {
        try {
            Car car = carService.getCar(carId).get();

            return new ResponseEntity<>(car, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(postCar)
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car addedCar = carService.addCar(car);

        return new ResponseEntity<>(addedCar, HttpStatus.CREATED);
    }

    @PutMapping(editCar + "{id}")
    public ResponseEntity<Car> updateCar(@PathVariable("id") String id, @RequestBody Car car) {
        if (carService.getCar(id).isPresent()) {
            car.setId(id);

            return new ResponseEntity<>(carService.updateCar(car), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(editCar + "{id}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable("id") String id) {
        try {
            carService.deleteCar(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(searchCars)
    public ResponseEntity<List<Car>> searchCars(@RequestParam("make") String make, @RequestParam("minYear") Integer minYear) {
        return new ResponseEntity<>(carService.searchCars(make, minYear), HttpStatus.OK);
    }
 }
