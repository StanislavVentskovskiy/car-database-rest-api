package ua.com.foxminded.cardatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.cardatabase.model.Type;
import ua.com.foxminded.cardatabase.service.impl.TypeServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TypeRestController {
    private final TypeServiceImpl typeService;

    @Autowired
    public TypeRestController(TypeServiceImpl typeService) {
        this.typeService = typeService;
    }

    @GetMapping("/types")
    public ResponseEntity<List<Type>> getAllTypes() {
        List<Type> types = new ArrayList<>();
        types = typeService.getAllTypes();

        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @GetMapping("/types/{id}")
    public ResponseEntity<Type> getSingleType(@PathVariable("id") Integer typeId) {
        try {
            Type type = typeService.getType(typeId).get();

            return new ResponseEntity<>(type, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/types")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Type> createType(@RequestBody Type type) {
        Type addedType = typeService.addType(type);

        return new ResponseEntity<>(addedType, HttpStatus.CREATED);
    }

    @PutMapping("/types/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Type> updateType(@PathVariable("id") Integer id, @RequestBody Type type) {
        Optional<Type> typeData = typeService.getType(id);

        if (typeData.isPresent()) {
            Type updatedType = typeData.get();
            updatedType.setName(type.getName());

            return new ResponseEntity<>(typeService.addType(updatedType), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
