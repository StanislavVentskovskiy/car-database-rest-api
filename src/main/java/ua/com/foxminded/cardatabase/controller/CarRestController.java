package ua.com.foxminded.cardatabase.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@CrossOrigin(origins = "*")
public class CarRestController {
    public final CarServiceImpl carService;

    @Autowired
    public CarRestController(CarServiceImpl carService) {
        this.carService = carService;
    }

    @GetMapping(getAllCars)
    @Operation(summary = "Get all cars from DB")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Returned all cars if exists",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Car.class)) }) })
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = new ArrayList<>();
        cars = carService.getAllCars();

        return new ResponseEntity<>(cars, HttpStatus.OK);
    }


    @GetMapping(getSingleCar + "{id}")
    @Operation(summary = "Get single сar by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Returned car with given id",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Car.class)) }),
        @ApiResponse(responseCode = "404", description = "No car with given id found",
            content = @Content) })
    public ResponseEntity<Car> getSingleCar(@PathVariable("id") String carId) {
        try {
            Car car = carService.getCar(carId).get();

            return new ResponseEntity<>(car, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(postCar)
    @Operation(summary = "Create new car", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "New car created",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Car.class)) })
        })
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car addedCar = carService.addCar(car);

        return new ResponseEntity<>(addedCar, HttpStatus.CREATED);
    }

    @PutMapping(editCar + "{id}")
    @Operation(summary = "Edit car found by given id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found car with given id and edited",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Car.class)) }),
        @ApiResponse(responseCode = "404", description = "No car with given id found",
            content = @Content) })
    public ResponseEntity<Car> updateCar(@PathVariable("id") String id, @RequestBody Car car) {
        if (carService.getCar(id).isPresent()) {
            car.setId(id);

            return new ResponseEntity<>(carService.updateCar(car), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(editCar + "{id}")
    @Operation(summary = "Delete car found by given id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Car found by id and deleted",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Car.class)) }),
        @ApiResponse(responseCode = "500", description = "No car with given id found",
            content = @Content) })
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable("id") String id) {
        try {
            carService.deleteCar(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(searchCars)
    @Operation(summary = "Get all cars with given make and all years past given one")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Returned filtered cars if exists",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Car.class)) }) })
    public ResponseEntity<List<Car>> searchCars(@RequestParam("make") String make, @RequestParam("minYear") Integer minYear) {
        return new ResponseEntity<>(carService.searchCars(make, minYear), HttpStatus.OK);
    }
 }
