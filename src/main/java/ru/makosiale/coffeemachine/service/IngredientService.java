package ru.makosiale.coffeemachine.service;



import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.makosiale.coffeemachine.dto.IngredientDTO;
import ru.makosiale.coffeemachine.model.Ingredient;
import ru.makosiale.coffeemachine.repository.IngredientRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }


    public ResponseEntity<?> supplyIngredient(IngredientDTO ingredientDTO) {
        Ingredient ingredient = ingredientRepository.findByName(ingredientDTO.name());

        if (ingredient == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\": \"Such an ingredient is not required\"}");
        } else {
            ingredient.setStock(ingredient.getStock() + ingredientDTO.amount());
            ingredientRepository.save(ingredient);
            return ResponseEntity.ok("The ingredient is supplied!");
        }
    }
}
