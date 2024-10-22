package ru.makosiale.coffeemachine.repository;


import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.makosiale.coffeemachine.model.Drink;

import java.util.List;
import java.util.Optional;


public interface DrinkRepository extends JpaRepository<Drink, Long> {
    Optional<Drink> findByName(String name);

    List<Drink> findAll();
}
