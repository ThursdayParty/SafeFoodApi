package me.thursdayParty.safeFoodApi.TakenFood;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.thursdayParty.safeFoodApi.TakenFood.dto.DailyTakenFoodDto;
import me.thursdayParty.safeFoodApi.TakenFood.dto.DailyTakenFoodServiceDto;
import me.thursdayParty.safeFoodApi.TakenFood.dto.FetchAllTakenFoodResponseDto;
import me.thursdayParty.safeFoodApi.TakenFood.dto.SaveTakenFoodRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/taken")
public class TakenFoodRestController {
	
	private final TakenFoodService takenFoodService;

	@PostMapping
    public ResponseEntity save(@RequestBody SaveTakenFoodRequestDto saveTakenFoodRequestDto, Principal principal) {
        log.info("/api/taken  POST ::  requestDto: {}, user: {}", saveTakenFoodRequestDto, principal.getName());

        Long foodId = saveTakenFoodRequestDto.getFoodId();
        String currentUserId = principal.getName();
        takenFoodService.save(currentUserId, foodId);
	    return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{takenFoodId}")
    public ResponseEntity delete(Principal principal, @PathVariable Long takenFoodId) {
        log.info("/api/taken/{}  DELETE :: user: {}",takenFoodId , principal.getName());

        String currentUserId = principal.getName();
        takenFoodService.delete(currentUserId, takenFoodId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<FetchAllTakenFoodResponseDto>> all(Principal principal) {
        log.info("/api/taken  GET :: user: {}", principal.getName());

        String currentUserId = principal.getName();
        List<FetchAllTakenFoodResponseDto> body = takenFoodService.fetchAll(currentUserId);
        return ResponseEntity.ok().body(body);
    }

    @GetMapping("/daily")
    public ResponseEntity<List<DailyTakenFoodServiceDto>> daily(Principal principal) {
        log.info("/api/taken/daily  GET :: user: {}", principal.getName());

        String currentUserId = principal.getName();
        List<DailyTakenFoodServiceDto> body = takenFoodService.fetchDaily(currentUserId);
        return ResponseEntity.ok().body(body);
    }

}