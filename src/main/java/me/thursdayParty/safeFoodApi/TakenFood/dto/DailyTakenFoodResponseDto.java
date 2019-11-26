package me.thursdayParty.safeFoodApi.TakenFood.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.thursdayParty.safeFoodApi.TakenFood.TakenFood;
import me.thursdayParty.safeFoodApi.food.Food;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Bactoria
 * @since 2019-11-25 [2019.11월.25]
 */

@Getter
@ToString
@NoArgsConstructor
public class DailyTakenFoodResponseDto {
    private List<DailyTakenFoodDto> DailyTakenFoodVos;
}
