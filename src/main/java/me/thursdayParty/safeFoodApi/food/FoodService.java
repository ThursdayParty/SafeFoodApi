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

    public List<FetchFoodsResponseDto> fetchAllFood() {
        List<Food> foods = repo.findAll();
        return foods.stream()
                .map(FetchFoodsResponseDto::new)
                .collect(Collectors.toList());
    }

    public FetchFoodDetailResponseDto fetchFoodDetail(final long foodId) {
        Food food = repo.findById(foodId)
                .orElseThrow(() -> new RuntimeException());
        food.visit();
        return new FetchFoodDetailResponseDto(food);
    }

    /*
     * public List<Food> searchFoodOrdered(String searchType, String searchData,
     * String sortType) {
     *
     * List<Food> result = new ArrayList<>(); result = fDao.search(searchType,
     * searchData);
     *
     * if (sortType != null) { for (int i = 0; i < result.size(); i++) { for (int j
     * = 1; j < result.size() - i; j++) { Food o1 = result.get(j - 1); Food o2 =
     * result.get(j);
     *
     * if (sortType.equals("name")) { if (o1.getName().compareTo(o2.getName()) > 0)
     * { result.set(j - 1, o2); result.set(j, o1); } } else { if
     * (o1.getMaker().compareTo(o2.getMaker()) > 0) { result.set(j - 1, o2);
     * result.set(j, o1); } } } } }
     *
     * return result; }
     *
     *
     * public List<Food> getAllFoodOrdered(String sortType) { List<Food> result =
     * fDao.findAll(); if (sortType != null) { for (int i = 0; i < result.size();
     * i++) { for (int j = 1; j < result.size() - i; j++) { Food o1 = result.get(j -
     * 1); Food o2 = result.get(j);
     *
     * if (sortType.equals("name")) { if (o1.getName().compareTo(o2.getName()) > 0)
     * { result.set(j - 1, o2); result.set(j, o1); } } else { if
     * (o1.getMaker().compareTo(o2.getMaker()) > 0) { result.set(j - 1, o2);
     * result.set(j, o1); } } } } } return result; }
     *
     * public Food getFood(int code) { return fDao.findByCode(code); }
     *
     * public Food getFoodWithCount(int code) { fDao.increaseView(code); return
     * fDao.findByCode(code); }
     *
     * public FoodNutrition getNutrition(String name) { return
     * fnDao.findByName(name); }
     *
     * public List<Food> searchFood(String searchType, String searchData) { return
     * fDao.search(searchType, searchData); }
     *
     * public void takeFood(TakedFoodRequestDto requestDto) {
     * fDao.takeFood(requestDto); }
     *
     * public List<TakedFoodResponseDto> fetchTakedFood(String userId) { return
     * fDao.findTakedFood(userId); }
     */
}
