package ru.makosiale.coffeemachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.makosiale.coffeemachine.model.Statistic;

import javax.swing.text.html.Option;
import java.util.Optional;


public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    Optional<Statistic> findByDrinkName(String drinkName);

    @Query(value = "SELECT * FROM statistic ORDER BY order_count DESC LIMIT 1", nativeQuery = true)
    Optional<Statistic> findTopStatisticNative();
}
