package me.thursdayParty.safeFoodApi.TakenFood.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bactoria
 * @since 2019-11-27 [2019.11월.27]
 */

@Getter
@ToString
@NoArgsConstructor
public class TakenFoodListServiceDto {
    private Long foodId;
    private String name;
    private Double kcal;
    private String maker;
    private LocalDateTime takenDateTime;
    private List<String> allergiesWithUser;

    public TakenFoodListServiceDto(TakenFoodListDto takenFoodListDto, List<String> userAllergies) {
        this.foodId = takenFoodListDto.getFoodId();
        this.name = takenFoodListDto.getName();
        this.kcal = takenFoodListDto.getKcal();
        this.maker = takenFoodListDto.getMaker();
        this.takenDateTime = takenFoodListDto.getTakenDateTime();
        this.allergiesWithUser = userAllergies.stream()
                .filter(allergy -> takenFoodListDto.getMaterials().contains(allergy))
                .collect(Collectors.toList());
    }
}
