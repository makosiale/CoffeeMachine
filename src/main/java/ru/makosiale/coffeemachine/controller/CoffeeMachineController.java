package ru.makosiale.coffeemachine.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.makosiale.coffeemachine.dto.DrinkWithRecipeDTO;
import ru.makosiale.coffeemachine.dto.IngredientDTO;
import ru.makosiale.coffeemachine.model.Drink;
import ru.makosiale.coffeemachine.model.Ingredient;
import ru.makosiale.coffeemachine.service.CoffeeService;
import ru.makosiale.coffeemachine.service.IngredientService;


import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CoffeeMachineController {

    private final CoffeeService coffeeService;
    private final IngredientService ingredientService;

    @GetMapping(value = "/drinks")
    public ResponseEntity<List<Drink>> getDrinks() {
        return ResponseEntity.ok(coffeeService.getAllDrinks());
    }

    @GetMapping(value = "/prepareByName/{name}")
    public ResponseEntity<Map<String, Object>> prepareDrinkByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(coffeeService.prepareDrink(name));
    }

    @GetMapping(value = "/prepare/{id}")
    public ResponseEntity<Map<String, Object>> prepareDrinkById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(coffeeService.prepareDrink(id));
    }

    @GetMapping(value = "/ingredients")
    public ResponseEntity<List<Ingredient>> getIngredients() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @PatchMapping(value = "/supplyIngredient")
    public ResponseEntity<?> supplyIngredient(@RequestBody IngredientDTO ingredientDTO) {
        return ingredientService.supplyIngredient(ingredientDTO);
    }

    @PatchMapping("/newDrink")
    public ResponseEntity<String> addNewDrink(@RequestBody DrinkWithRecipeDTO drinkWithRecipeDTO) {
        coffeeService.addNewDrink(drinkWithRecipeDTO);
        return ResponseEntity.ok("Drink and its recipes have been added successfully!");
    }
}

