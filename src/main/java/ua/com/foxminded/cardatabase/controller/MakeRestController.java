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
    @Operation(summary = "Get all makes from DB")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Returned all cars if exists",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Make.class)) }) })
    public ResponseEntity<List<Make>> getAllMakes() {
        List<Make> makes = new ArrayList<>();
        makes = makeService.getAllMakes();

        return new ResponseEntity<>(makes, HttpStatus.OK);
    }

    @GetMapping(getSingleMake + "{id}")
    @Operation(summary = "Get single make by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Returned make with given id",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Make.class)) }),
        @ApiResponse(responseCode = "404", description = "No make with given id found",
            content = @Content) })
    public ResponseEntity<Make> getSingleMake(@PathVariable("id") Integer makeId) {
        try {
            Make make = makeService.getMake(makeId).get();

            return new ResponseEntity<>(make, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(postMake)
    @Operation(summary = "Create new make", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "New make created",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Make.class)) }) })
    public ResponseEntity<Make> createMake(@RequestBody Make make) {
        Make addedMake = makeService.addMake(make);

        return new ResponseEntity<>(addedMake, HttpStatus.CREATED);
    }

    @PutMapping(editMake + "{id}")
    @Operation(summary = "Edit make found by given id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found make with given id and edited",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Make.class)) }),
        @ApiResponse(responseCode = "404", description = "No make with given id found",
            content = @Content) })
    public ResponseEntity<Make> updateMake(@PathVariable("id") Integer id, @RequestBody Make make) {
        if (makeService.getMake(id).isPresent()) {
            make.setId(id);

            return new ResponseEntity<>(makeService.updateMake(make), HttpStatus.OK);
        }   else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping( editMake + "{id}")
    @Operation(summary = "Delete make found by given id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Make found by id and deleted or not exists",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Make.class)) }) })
    public ResponseEntity<HttpStatus> deleteMake(@PathVariable("id") Integer id) {
            makeService.deleteMake(id);

            return new ResponseEntity<>(HttpStatus.OK);
    }
}
