package me.thursdayParty.safeFoodApi.TakenFood;

import me.thursdayParty.safeFoodApi.TakenFood.dto.DailyTakenFoodDto;
import me.thursdayParty.safeFoodApi.TakenFood.dto.FetchAllTakenFoodResponseDto;
import me.thursdayParty.safeFoodApi.TakenFood.dto.TakenFoodListDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface TakenFoodDao {
    List<FetchAllTakenFoodResponseDto> findAllByAccountId(Long accountId);
    List<DailyTakenFoodDto> findDailyTakenFoodByAccountId(Long accountId);
}
