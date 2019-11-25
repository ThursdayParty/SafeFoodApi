package me.thursdayParty.safeFoodApi.TakenFood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.thursdayParty.safeFoodApi.TakenFood.TakenFood;
import me.thursdayParty.safeFoodApi.food.Food;

import java.time.LocalDateTime;

/**
 * @author Bactoria
 * @since 2019-11-25 [2019.11월.25]
 */

@Getter
@ToString
@NoArgsConstructor
public class FetchAllTakenFoodResponseDto {
    private Long foodId;
    private LocalDateTime takenDateTime;
    private Integer serving_wt;
    private Double calorie;
    private Double carbohydrate;
    private Double protein;
    private Double fat;
    private Double sugars;
    private Double salt;
    private Double cholesterol;
    private Double saturated_fatty_acid;
    private Double trans_fat;

    public FetchAllTakenFoodResponseDto(TakenFood takenFood, Food food) {
        this.foodId = takenFood.getFoodId();
        this.takenDateTime = takenFood.getCreatedDateTime();
        this.serving_wt = food.getServing_wt();
        this.calorie = food.getCalorie();
        this.carbohydrate = food.getCarbohydrate();
        this.protein = food.getProtein();
        this.fat = food.getFat();
        this.sugars = food.getSugars();
        this.salt = food.getSalt();
        this.cholesterol = food.getCholesterol();
        this.saturated_fatty_acid = food.getSaturated_fatty_acid();
        this.trans_fat = food.getTrans_fat();
    }
}
