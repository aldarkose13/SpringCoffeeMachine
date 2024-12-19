package com.coffee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/coffee")
public class CoffeeController {
    private final CoffeeService coffeeService;


    @Autowired
    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @ExceptionHandler(CoffeeUnavailableException.class)
    public ResponseEntity<String> handleCoffeeMachineUnavailable(CoffeeUnavailableException e) {
        // Return a custom error message along with HTTP status code 400 (Bad Request)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @PostMapping("/espresso")
    public CoffeeEntity setEspresso() {
        String countryCode = "KZ"; // Or dynamically get from request headers or user settings

        if (!coffeeService.isCoffeeMachineWorking(countryCode)) {
            throw new CoffeeUnavailableException("The coffee machine is closed due to holidays or outside working hours.");
        }

        Map<String, Integer> ingreds = new HashMap<>();
        ingreds.put("coffee", 50);
        CoffeeEntity coffee = new CoffeeEntity();
        coffee.setName("Espresso");
        coffee.setIngredients(ingreds);
        return coffeeService.saveCoffee(coffee);
    }
    @PostMapping("/americano")
    public CoffeeEntity postAmericano() {
        String countryCode = "KZ"; // Or dynamically get from request headers or user settings

        if (!coffeeService.isCoffeeMachineWorking(countryCode)) {
            throw new CoffeeUnavailableException("The coffee machine is closed due to holidays or outside working hours.");
        }
        Map<String, Integer> ingreds = new HashMap<>();
        ingreds.put("coffee", 50);
        ingreds.put("hot_water", 150);
        CoffeeEntity coffee = new CoffeeEntity();
        coffee.setName("Americano");
        coffee.setIngredients(ingreds);
        return coffeeService.saveCoffee(coffee);
    }
    @PostMapping("/capuccino")
    public CoffeeEntity postCapuccino() {
        String countryCode = "KZ"; // Or dynamically get from request headers or user settings

        if (!coffeeService.isCoffeeMachineWorking(countryCode)) {
            throw new CoffeeUnavailableException("The coffee machine is closed due to holidays or outside working hours.");
        }
        Map<String, Integer> ingreds = new HashMap<>();
        ingreds.put("coffee", 50);
        ingreds.put("steamed_milk", 150);
        CoffeeEntity coffee = new CoffeeEntity();
        coffee.setName("Capuccino");
        coffee.setIngredients(ingreds);
        return coffeeService.saveCoffee(coffee);
    }
    @GetMapping
    public List<CoffeeEntity> getAllCoffee(){
        String countryCode = "KZ"; // Or dynamically get from request headers or user settings

        if (!coffeeService.isCoffeeMachineWorking(countryCode)) {
            throw new CoffeeUnavailableException("The coffee machine is closed due to holidays or outside working hours.");
        }
        return coffeeService.getAllCoffee();
    }
    @PostMapping("/CustomCoffee")
    public CoffeeEntity CustomCoffee(@RequestBody CoffeeEntity coffee){
        String countryCode = "KZ"; // Or dynamically get from request headers or user settings

        if (!coffeeService.isCoffeeMachineWorking(countryCode)) {
            throw new CoffeeUnavailableException("The coffee machine is closed due to holidays or outside working hours.");
        }
        return coffeeService.saveCoffee(coffee);
    }
    @GetMapping("/mostOrdered")
    public String getMostOrderedCoffee() {
        String countryCode = "KZ"; // Or dynamically get from request headers or user settings

        if (!coffeeService.isCoffeeMachineWorking(countryCode)) {
            throw new CoffeeUnavailableException("The coffee machine is closed due to holidays or outside working hours.");
        }
        return coffeeService.getMostOrderedCoffeeName();
    }

}
