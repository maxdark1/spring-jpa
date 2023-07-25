package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;
    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }
    @GetMapping("/template")
    public ResponseEntity<List<PizzaEntity>> getAll(){
        return ResponseEntity.ok(this.pizzaService.getAll());
    }

    @GetMapping("/repository")
    public ResponseEntity<Page<PizzaEntity>> getAllRepository(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "8") int elements){
        return ResponseEntity.ok(this.pizzaService.getAllWithRepository(page,elements));
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> getById(@PathVariable(name = "idPizza") Integer id){
        return ResponseEntity.ok(this.pizzaService.get(id));
    }

    @GetMapping("/available")
    public ResponseEntity<List<PizzaEntity>> getAvailable(){
        return ResponseEntity.ok(this.pizzaService.getAvailable());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getAvailableName(@PathVariable(name="name") String name){
        return ResponseEntity.ok(this.pizzaService.getByName(name));
    }

    @PostMapping()
    public ResponseEntity<PizzaEntity> save(@RequestBody PizzaEntity pizza){
        if(pizza.getIdPizza() == null || !this.pizzaService.exists(pizza.getIdPizza())) {
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        } else {
            return  ResponseEntity.badRequest().build();
        }
    }

    @PutMapping()
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza){
        if(pizza.getIdPizza() != null && this.pizzaService.exists(pizza.getIdPizza())) {
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        } else {
            return  ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{idPizza}")
    public  ResponseEntity<Void> delete (@PathVariable(name="idPizza") int idPizza){
        if(this.pizzaService.exists(idPizza)){
            this.pizzaService.delete(idPizza);
            return  ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


}
