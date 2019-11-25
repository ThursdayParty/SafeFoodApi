package me.thursdayParty.safeFoodApi.TakenFood;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.thursdayParty.safeFoodApi.TakenFood.dto.FetchAllTakenFoodResponseDto;
import me.thursdayParty.safeFoodApi.TakenFood.dto.SaveTakenFoodRequestDto;
import me.thursdayParty.safeFoodApi.account.Account;
import me.thursdayParty.safeFoodApi.food.FoodService;
import me.thursdayParty.safeFoodApi.food.dto.FetchAllFoodsResponseDto;
import me.thursdayParty.safeFoodApi.food.dto.FetchFoodDetailResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
//@RequestMapping("/api/taken")
public class TakenFoodRestController {
	
	private final TakenFoodService takenFoodService;

	@GetMapping("/test")
    public ResponseEntity save() {
        log.info("/api/taken  POST :: ");

        Long foodId = 1L;
        String currentUserId = "bactoria";
        takenFoodService.save(currentUserId, foodId);
	    return ResponseEntity.ok().build();
    }

    @GetMapping("/test2")
    public ResponseEntity<List<FetchAllTakenFoodResponseDto>> all() {

        String currentUserId = "bactoria";
        List<FetchAllTakenFoodResponseDto> body = takenFoodService.fetchAll(currentUserId);
        return ResponseEntity.ok().body(body);
    }

}