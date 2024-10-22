package ru.makosiale.coffeemachine.service;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.makosiale.coffeemachine.dto.DrinkWithRecipeDTO;
import ru.makosiale.coffeemachine.model.Drink;
import ru.makosiale.coffeemachine.model.Ingredient;
import ru.makosiale.coffeemachine.model.Recipe;
import ru.makosiale.coffeemachine.repository.DrinkRepository;
import ru.makosiale.coffeemachine.repository.IngredientRepository;
import ru.makosiale.coffeemachine.repository.RecipeRepository;

import java.util.*;
import java.util.stream.Collectors;


@ComponentScan
@Service
@RequiredArgsConstructor
public class CoffeeService {
    private final DrinkRepository drinkRepository;

    private final IngredientRepository ingredientRepository;

    private final RecipeRepository recipeRepository;

    private final RecipeService recipeService;

    private final StatisticService statisticService;

    private final IngredientService ingredientService;

    public Map<String, Object> prepareDrink(Object T) {
        Optional<Drink> recipeOpt;
        if (T instanceof Long) {
            recipeOpt = drinkRepository.findById((Long) T);
        } else if (T instanceof String) {
            recipeOpt = drinkRepository.findByName((String) T);
        } else {
            return Map.of((String) "error", (Object) "Invalid input type. Expected Long or String.");
        }
        if (recipeOpt.isPresent()) {
            for (Map.Entry<Ingredient, Integer> entry : recipeService.getIngridientForDrinksAsMap(recipeOpt.get()).entrySet()) {
                Ingredient ingredient = entry.getKey();
                int quantity = entry.getValue();

                if (ingredient.getStock() < quantity) {
                    Map<String, Object> errorResponse = new HashMap<>();
                    errorResponse.put("error", "Not enough ingredients");
                    errorResponse.put("missing", ingredient.getName());
                    errorResponse.put("required", quantity);
                    errorResponse.put("available", ingredient.getStock());
                    return errorResponse;
                }
            }

            for (Map.Entry<Ingredient, Integer> entry : recipeService.getIngridientForDrinksAsMap(recipeOpt.get()).entrySet()) {
                Ingredient ingredient = entry.getKey();
                ingredient.setStock(ingredient.getStock() - entry.getValue());
                ingredientRepository.save(ingredient);
            }

            statisticService.updateStatistic(recipeOpt.get().getName());

            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("message", "Your " + recipeOpt.get().getName() + " is ready!");
            return successResponse;
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Recipe not found!");
            return errorResponse;
        }
    }

    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }

    public ResponseEntity<String> addNewDrink(DrinkWithRecipeDTO drinkWithRecipeDTO) {
        if (drinkRepository.findByName(drinkWithRecipeDTO.drinkName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Drink already added!\"");
        }
        Drink drink = new Drink();
        drink.setName(drinkWithRecipeDTO.drinkName());
        drinkRepository.save(drink);

        List<Recipe> recipes = drinkWithRecipeDTO.recipes().stream().map(recipeDTO -> {
            Ingredient ingredient = ingredientRepository.findByName(recipeDTO.ingredientName());

            if (ingredient == null) {
                ingredient = new Ingredient();
                ingredient.setName(recipeDTO.ingredientName());
                ingredientRepository.save(ingredient);
            }

            Recipe recipe = new Recipe();
            recipe.setIngredient(ingredient);
            recipe.setDrink(drink);
            recipe.setAmount(recipeDTO.amount());
            return recipe;
        }).collect(Collectors.toList());
        recipeRepository.saveAll(recipes);
        return ResponseEntity.ok("New drink added successful!");
    }

}
