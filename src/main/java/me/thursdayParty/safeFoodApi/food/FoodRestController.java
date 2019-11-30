package me.thursdayParty.safeFoodApi.food;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.thursdayParty.safeFoodApi.food.dto.FetchFoodDetailResponseDto;
import me.thursdayParty.safeFoodApi.food.dto.FetchFoodsResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/foods")
public class FoodRestController {

    private final FoodService foodService;

    @GetMapping
    public ResponseEntity<List<FetchFoodsResponseDto>> fetch(@RequestParam(required = false) String searchType, @RequestParam(required = false) String searchKeyword) {
        log.info("/api/foods  GET :: searchType: {}, searchKeyword: {}", searchType, searchKeyword);

        List<FetchFoodsResponseDto> foods = new ArrayList<>();

        if(searchType == null || searchKeyword == null) {
             foods = foodService.fetchAllFood();
        } else {
            foods = foodService.fetchBySearch(searchType, searchKeyword);
        }

        return ResponseEntity.ok().body(foods);
    }

    @GetMapping("/{foodId}")
    public ResponseEntity<FetchFoodDetailResponseDto> byId(@PathVariable final long foodId) {
        log.info("/api/foods/" + foodId + "  GET :: ");
        FetchFoodDetailResponseDto food = foodService.fetchFoodDetail(foodId);
        return ResponseEntity.ok().body(food);
    }

}