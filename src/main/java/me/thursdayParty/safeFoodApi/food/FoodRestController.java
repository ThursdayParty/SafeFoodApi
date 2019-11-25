package me.thursdayParty.safeFoodApi.food;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.thursdayParty.safeFoodApi.food.dto.FetchAllFoodsResponseDto;
import me.thursdayParty.safeFoodApi.food.dto.FetchFoodDetailResponseDto;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/foods")
public class FoodRestController {
	
	private final FoodService foodService;

	@GetMapping
	public ResponseEntity<List<FetchAllFoodsResponseDto>> all() { //String searchType, String searchData, String sortType, Model model
		log.info("/api/foods  GET :: ");
		List<FetchAllFoodsResponseDto> foods = foodService.fetchAllFood();
		return ResponseEntity.ok().body(foods);
	}
	
	@GetMapping("/{foodId}")
	public ResponseEntity<FetchFoodDetailResponseDto> byId(@PathVariable final long foodId) {
		log.info("/api/foods/"+foodId+"  GET :: ");
		FetchFoodDetailResponseDto food = foodService.fetchFoodDetail(foodId);
		return ResponseEntity.ok().body(food);
	}

}