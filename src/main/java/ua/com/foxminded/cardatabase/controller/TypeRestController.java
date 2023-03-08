package ua.com.foxminded.cardatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.cardatabase.model.Type;
import ua.com.foxminded.cardatabase.service.impl.TypeServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static ua.com.foxminded.cardatabase.url.UrlContainer.*;

@RestController
public class TypeRestController {
    private final TypeServiceImpl typeService;

    @Autowired
    public TypeRestController(TypeServiceImpl typeService) {
        this.typeService = typeService;
    }

    @GetMapping(getAllTypes)
    public ResponseEntity<List<Type>> getAllTypes() {
        List<Type> types = new ArrayList<>();
        types = typeService.getAllTypes();

        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @GetMapping(getSingleType + "{id}")
    public ResponseEntity<Type> getSingleType(@PathVariable("id") Integer typeId) {
        try {
            Type type = typeService.getType(typeId).get();

            return new ResponseEntity<>(type, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(postType)
    public ResponseEntity<Type> createType(@RequestBody Type type) {
        Type addedType = typeService.addType(type);

        return new ResponseEntity<>(addedType, HttpStatus.CREATED);
    }

    @PutMapping(editType + "{id}")
    public ResponseEntity<Type> updateType(@PathVariable("id") Integer id, @RequestBody Type type) {
        if (typeService.getType(id).isPresent()) {
            type.setId(id);

            return new ResponseEntity<>(typeService.updateType(type), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
