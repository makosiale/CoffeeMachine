package ru.makosiale.coffeemachine.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.makosiale.coffeemachine.model.Statistic;
import ru.makosiale.coffeemachine.repository.DrinkRepository;
import ru.makosiale.coffeemachine.repository.StatisticRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final StatisticRepository statisticRepository;
    private final DrinkRepository drinkRepository;

    public void updateStatistic(String drinkName) {
        Statistic statistic = statisticRepository.findByDrinkName(drinkName)
                .orElse(new Statistic(null, drinkRepository.findByName(drinkName).get(), 0, Timestamp.valueOf(LocalDateTime.now())));
        statistic.setOrder_count(statistic.getOrder_count() + 1);
        statistic.setLast_order(Timestamp.valueOf(LocalDateTime.now()));
        statisticRepository.save(statistic);
    }

    public ResponseEntity<?> getAllStatistic() {
        List<Statistic> statisticList = statisticRepository.findAll();

        if (statisticList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"message\": \"Statistic empty\"}");
        }

        return ResponseEntity.ok(statisticList);
    }

    public ResponseEntity<?> getMostPopularDrink() {
        Optional<Statistic> popular = statisticRepository.findTopStatisticNative();
        if (popular.isPresent()) {
            return ResponseEntity.ok(popular.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("{\"message\": \"Statistic empty\"}");
    }
}
