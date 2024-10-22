package ru.makosiale.coffeemachine.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.makosiale.coffeemachine.model.Drink;
import ru.makosiale.coffeemachine.model.Recipe;

import java.util.List;
import java.util.Optional;


public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findByDrinkName(String name);

    List<Recipe> findByDrink(Drink drink);

}
