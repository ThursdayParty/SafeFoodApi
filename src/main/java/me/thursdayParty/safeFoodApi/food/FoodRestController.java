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

	private final String[] allergys = {"대두","땅콩","우유","게","새우","참치","연어","쑥","소고기","닭고기","돼지고기","복숭아","민들레","계란흰자"};

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
	
	
	
	/*
	 * 
	 * @GetMapping("/food.do") public String foodForm(int code, Model model) { Food
	 * food = foodService.getFoodWithCount(code); model.addAttribute("food", food);
	 * 
	 * String[] materials = food.getMaterial().split(","); for (int i = 0; i <
	 * materials.length; i++) { materials[i] = materials[i].trim(); }
	 * 
	 * List<String> foodAllergys = new ArrayList<>(); for (String allergy :
	 * allergys) { for (String material : materials) {
	 * if(material.contains(allergy)) { foodAllergys.add(allergy); break; } } }
	 * 
	 * String allergyStr = foodAllergys.toString(); model.addAttribute("allergys",
	 * allergyStr.substring(1, allergyStr.length()-1));
	 * 
	 * FoodNutrition foodNutrition = foodService.getNutrition(food.getName());
	 * model.addAttribute("foodNutrition", foodNutrition); return "food"; }
	 * 
	 * @ResponseBody
	 * 
	 * @GetMapping("/foodNutrition") public ResponseEntity<FoodNutrition>
	 * getFoodNutrition(int code) { Food food = foodService.getFood(code);
	 * FoodNutrition foodNutrition = foodService.getNutrition(food.getName());
	 * return ResponseEntity.ok().body(foodNutrition); }
	 * 
	 * @ResponseBody
	 * 
	 * @PostMapping("/takeFood") public ResponseEntity takeFood(TakedFoodRequestDto
	 * requestDto, HttpSession session) { User user =
	 * (User)session.getAttribute("userid"); String userId = user.getId();
	 * requestDto.setUserId(userId);
	 * 
	 * foodService.takeFood(requestDto); return ResponseEntity.ok().build(); }
	 * 
	 * @GetMapping("/takedFood.do") public String takedFoodForm(Model model,
	 * HttpSession session) { User user = (User)session.getAttribute("userid");
	 * String userId = user.getId();
	 * 
	 * List<TakedFoodResponseDto> takedFoodResponseDtos =
	 * foodService.fetchTakedFood(userId); model.addAttribute("takedFoods",
	 * takedFoodResponseDtos); return "takedFood"; }
	 */
}