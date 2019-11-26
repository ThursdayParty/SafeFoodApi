package me.thursdayParty.safeFoodApi.TakenFood.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bactoria
 * @since 2019-11-26 [2019.11월.26]
 */

@Getter
@ToString
@NoArgsConstructor
public class DailyTakenFoodDto {
    private LocalDate localDate;
    private Double kcal;
    private List<TakenFoodListDto> foods;

    public DailyTakenFoodDto(LocalDate localDate, List<TakenFoodListDto> foods) {
        this.localDate = localDate;
        if(foods == null) {
            this.kcal = 0.0;
            this.foods = new ArrayList<>();
        } else {
            this.foods = foods;
            kcal = foods.stream().mapToDouble(TakenFoodListDto::getKcal).sum();
        }
    }
}
