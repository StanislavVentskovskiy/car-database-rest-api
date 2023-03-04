package ua.com.foxminded.cardatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.cardatabase.model.Make;
import ua.com.foxminded.cardatabase.service.impl.MakeServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MakeRestController {
    private final MakeServiceImpl makeService;

    @Autowired
    public MakeRestController(MakeServiceImpl makeService) {
        this.makeService = makeService;
    }

    @GetMapping("/makes/get")
    public ResponseEntity<List<Make>> getAllMakes() {
        List<Make> makes = new ArrayList<>();
        makes = makeService.getAllMakes();

        return new ResponseEntity<>(makes, HttpStatus.OK);
    }

    @GetMapping("/makes/get/{id}")
    public ResponseEntity<Make> getSingleMake(@PathVariable("id") Integer makeId) {
        try {
            Make make = makeService.getMake(makeId).get();

            return new ResponseEntity<>(make, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/makes")
    public ResponseEntity<Make> createMake(@RequestBody Make make) {
        Make addedMake = makeService.addMake(make);

        return new ResponseEntity<>(addedMake, HttpStatus.CREATED);
    }

    @PutMapping("/makes/{id}")
    public ResponseEntity<Make> updateMake(@PathVariable("id") Integer id, @RequestBody Make make) {
        Optional<Make> makeData = makeService.getMake(id);

        if(makeData.isPresent()){
            Make updatedMake = makeData.get();
            updatedMake.setName(make.getName());

            return new ResponseEntity<>(makeService.addMake(updatedMake), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/makes/{id}")
    public ResponseEntity<HttpStatus> deleteMake(@PathVariable("id") Integer id) {
        try {
            makeService.deleteMake(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
