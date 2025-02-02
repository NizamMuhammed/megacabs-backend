package com.nizam.megacabs;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CabsService {
    @Autowired
    private CabsRepository cabsRepository;

    public List<Cabs> allCabs() {
        return cabsRepository.findAll();
    }

    public Optional<Cabs> singleCab(String CabNumber){
        return cabsRepository.findCabsByCabNumber(CabNumber);
    }
}