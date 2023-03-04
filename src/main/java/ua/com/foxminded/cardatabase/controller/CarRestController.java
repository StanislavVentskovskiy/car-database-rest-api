package ua.com.foxminded.cardatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.cardatabase.model.Car;
import ua.com.foxminded.cardatabase.service.impl.CarServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarRestController {
    public final CarServiceImpl carService;

    @Autowired
    public CarRestController(CarServiceImpl carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = new ArrayList<>();
        cars = carService.getAllCars();

        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getSingleCar(@PathVariable("id") String carId) {
        try {
            Car car = carService.getCar(carId).get();

            return new ResponseEntity<>(car, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/cars")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car addedCar = carService.addCar(car);

        return new ResponseEntity<>(addedCar, HttpStatus.CREATED);
    }

    @PutMapping("/cars/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Car> updateCar(@PathVariable("id") String id, @RequestBody Car car) {
        Optional<Car> carData = carService.getCar(id);

        if (carData.isPresent()) {
            Car updatedCar = carData.get();
            updatedCar.setType(car.getType());
            updatedCar.setMake(car.getMake());
            updatedCar.setYear(car.getYear());
            updatedCar.setModel(car.getModel());

            return new ResponseEntity<>(carService.updateCar(updatedCar), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cars/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable("id") String id) {
        try {
            carService.deleteCar(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cars/search")
    public ResponseEntity<List<Car>> searchCars(@RequestParam("make") String make, @RequestParam("minYear") Integer minYear) {
        return new ResponseEntity<>(carService.searchCars(make, minYear), HttpStatus.OK);
    }
 }
