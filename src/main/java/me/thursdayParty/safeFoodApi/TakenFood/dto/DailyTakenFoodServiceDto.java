package me.thursdayParty.safeFoodApi.TakenFood.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bactoria
 * @since 2019-11-27 [2019.11월.27]
 */

@Getter
@ToString
@NoArgsConstructor
public class DailyTakenFoodServiceDto {
    private LocalDate localDate;
    private Double kcal;
    private List<TakenFoodListServiceDto> foods;

    public DailyTakenFoodServiceDto(DailyTakenFoodDto dailyTakenFoodDto, List<String> userAllergies) {
        this.localDate = dailyTakenFoodDto.getLocalDate();
        this.kcal = dailyTakenFoodDto.getKcal();
        this.foods = dailyTakenFoodDto.getFoods().stream()
                .map(dto -> new TakenFoodListServiceDto(dto, userAllergies))
                .collect(Collectors.toList());
    }

}
