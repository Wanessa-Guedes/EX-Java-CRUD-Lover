package com.tastecamp.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tastecamp.api.dto.CarsDTO;
import com.tastecamp.api.model.Cars;
import com.tastecamp.api.repository.CarsRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/cars")
public class CarsController {

    @Autowired
    CarsRepository repository;

    @PostMapping
    public void create(@RequestBody @Valid CarsDTO req) {
        repository.save(new Cars(req));
    }

    @GetMapping
    public List<Cars> listAll() {
        return repository.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Cars update(@PathVariable Long id, @RequestBody @Valid CarsDTO req) {
        Optional<Cars> car = repository.findById(id);
        if (car.isPresent()) {
            car.get().setAnoModelo(req.anoModelo());
            car.get().setModelo(req.modelo());
            car.get().setFabricante(req.fabricante());
            car.get().setDataFabricacao(req.dataFabricacao());
            car.get().setValor(req.valor());
            return repository.save(car.get());

        }
        return null;

    }

}
