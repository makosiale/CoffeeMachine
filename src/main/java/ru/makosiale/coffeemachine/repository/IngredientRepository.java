package ru.makosiale.coffeemachine.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.makosiale.coffeemachine.model.Ingredient;

import java.util.Optional;


public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findByName(String name);
}
