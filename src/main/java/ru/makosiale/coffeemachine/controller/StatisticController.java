package ru.makosiale.coffeemachine.controller;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.makosiale.coffeemachine.service.StatisticService;


@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping("/statistic")
    public ResponseEntity<?> getAllStatistic() {
        return statisticService.getAllStatistic();
    }

    @GetMapping("/mostPopular")
    public ResponseEntity<?> getMostPopularDrink() {
        return statisticService.getMostPopularDrink();
    }
}
