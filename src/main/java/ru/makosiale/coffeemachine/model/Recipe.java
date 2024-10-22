package ru.makosiale.coffeemachine.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "drink_id")
    @ManyToOne
    private Drink drink;

    @JoinColumn(name = "ingredient_id")
    @ManyToOne
    private Ingredient ingredient;

    private int amount;
}
