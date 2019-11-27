package me.thursdayParty.safeFoodApi.TakenFood.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.thursdayParty.safeFoodApi.TakenFood.TakenFood;
import me.thursdayParty.safeFoodApi.food.Food;

import java.time.LocalDateTime;

/**
 * @author Bactoria
 * @since 2019-11-26 [2019.11월.26]
 */

@Getter
@ToString
@NoArgsConstructor
public class TakenFoodListDto {
    private Long foodId;
    private String name;
    private String maker;
    private String materials;
    private Double kcal;
    private LocalDateTime takenDateTime;

    public TakenFoodListDto(TakenFood tf, Food f) {
        this.foodId = tf.getFoodId();
        this.name = f.getName();
        this.maker = f.getMaker();
        this.kcal = f.getCalorie();
        this.takenDateTime = tf.getCreatedDateTime();
        this.materials = f.getMaterials();
    }
}
