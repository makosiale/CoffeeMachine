package ru.makosiale.coffeemachine.dto;


import java.util.List;


public record DrinkWithRecipeDTO(String drinkName, List<RecipeDTO> recipes) {
}
