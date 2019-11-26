package me.thursdayParty.safeFoodApi.food;

import me.thursdayParty.safeFoodApi.food.dto.FetchFoodsResponseDto;

import java.util.List;

/**
 * @author Bactoria
 * @since 2019-11-26 [2019.11월.26]
 */

public interface FoodDao {
    List<FetchFoodsResponseDto> fetchBySearch(String searchType, String searchKeyword);
}
