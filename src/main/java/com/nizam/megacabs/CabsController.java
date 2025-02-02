package com.nizam.megacabs;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/cabs")
public class CabsController {
    @Autowired
    private CabsService cabsService;

    @GetMapping
    public ResponseEntity<List<Cabs>> getAllCabs() {
        return new ResponseEntity<List<Cabs>>(cabsService.allCabs(), HttpStatus.OK);
    }
    @GetMapping("/{CabNumber}")
    public ResponseEntity<Optional<Cabs>> getSingleCab(@PathVariable String CabNumber) {
        return new ResponseEntity<Optional<Cabs>>(cabsService.singleCab(CabNumber),HttpStatus.OK);

    }

}
