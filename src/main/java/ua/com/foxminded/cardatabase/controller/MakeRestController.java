package ua.com.foxminded.cardatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.cardatabase.model.Make;
import ua.com.foxminded.cardatabase.service.impl.MakeServiceImpl;
import java.util.ArrayList;
import java.util.List;
import static ua.com.foxminded.cardatabase.url.UrlContainer.*;

@RestController
public class MakeRestController {
    private final MakeServiceImpl makeService;

    @Autowired
    public MakeRestController(MakeServiceImpl makeService) {
        this.makeService = makeService;
    }

    @GetMapping(getAllMakes)
    public ResponseEntity<List<Make>> getAllMakes() {
        List<Make> makes = new ArrayList<>();
        makes = makeService.getAllMakes();

        return new ResponseEntity<>(makes, HttpStatus.OK);
    }

    @GetMapping(getSingleMake + "{id}")
    public ResponseEntity<Make> getSingleMake(@PathVariable("id") Integer makeId) {
        try {
            Make make = makeService.getMake(makeId).get();

            return new ResponseEntity<>(make, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(postMake)
    public ResponseEntity<Make> createMake(@RequestBody Make make) {
        Make addedMake = makeService.addMake(make);

        return new ResponseEntity<>(addedMake, HttpStatus.CREATED);
    }

    @PutMapping(editMake + "{id}")
    public ResponseEntity<Make> updateMake(@PathVariable("id") Integer id, @RequestBody Make make) {
        if (makeService.getMake(id).isPresent()) {
            make.setId(id);

            return new ResponseEntity<>(makeService.updateMake(make), HttpStatus.OK);
        }   else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping( editMake + "{id}")
    public ResponseEntity<HttpStatus> deleteMake(@PathVariable("id") Integer id) {
        try {
            makeService.deleteMake(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
