package me.thursdayParty.safeFoodApi.food;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.thursdayParty.safeFoodApi.food.dto.FetchFoodsResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bactoria
 * @since 2019-11-26 [2019.11월.26]
 */

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommend")
public class FoodRecomendRestController {

    private final FoodRecommendService foodRecommendService;

    @PutMapping("/food/{foodId}")
    public ResponseEntity recommend(@PathVariable Long foodId) {
        log.info("/api/recommend/food/{} PUT :: ", foodId);

        foodRecommendService.recommend(foodId);
        return ResponseEntity.ok().build();
    }

}
