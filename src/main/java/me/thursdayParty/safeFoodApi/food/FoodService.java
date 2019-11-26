package me.thursdayParty.safeFoodApi.food;

import lombok.RequiredArgsConstructor;
import me.thursdayParty.safeFoodApi.food.dto.FetchFoodDetailResponseDto;
import me.thursdayParty.safeFoodApi.food.dto.FetchFoodsResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository repo;
    private final FoodDao dao;

    public List<FetchFoodsResponseDto> fetchAllFood() {
        List<Food> foods = repo.findAll();
        return foods.stream()
                .map(FetchFoodsResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<FetchFoodsResponseDto> fetchBySearch(String searchType, String searchKeyword) {
        return dao.fetchBySearch(searchType, searchKeyword);
    }

    public FetchFoodDetailResponseDto fetchFoodDetail(final long foodId) {
        Food food = repo.findById(foodId)
                .orElseThrow(() -> new RuntimeException());
        food.visit();
        return new FetchFoodDetailResponseDto(food);
    }

}
