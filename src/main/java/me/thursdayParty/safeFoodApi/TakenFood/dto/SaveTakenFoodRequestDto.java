package me.thursdayParty.safeFoodApi.TakenFood.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Bactoria
 * @since 2019-11-25 [2019.11월.25]
 */

@Getter
@ToString
@NoArgsConstructor
public class SaveTakenFoodRequestDto {
    private Long foodId;
}
