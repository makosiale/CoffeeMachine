package ru.makosiale.coffeemachine.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.makosiale.coffeemachine.model.Drink;
import ru.makosiale.coffeemachine.model.Ingredient;
import ru.makosiale.coffeemachine.model.Recipe;
import ru.makosiale.coffeemachine.repository.RecipeRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public Map<Ingredient, Integer> getIngridientForDrinksAsMap(Drink drink) {

        List<Recipe> recipeList = recipeRepository.findByDrink(drink);
        return recipeList
                .stream()
                .collect(Collectors
                        .toMap(Recipe::getIngredient, Recipe::getAmount));
    }
}
