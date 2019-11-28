package me.thursdayParty.safeFoodApi.TakenFood;

import lombok.RequiredArgsConstructor;
import me.thursdayParty.safeFoodApi.TakenFood.dto.DailyTakenFoodDto;
import me.thursdayParty.safeFoodApi.TakenFood.dto.DailyTakenFoodServiceDto;
import me.thursdayParty.safeFoodApi.TakenFood.dto.FetchAllTakenFoodResponseDto;
import me.thursdayParty.safeFoodApi.account.Account;
import me.thursdayParty.safeFoodApi.account.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TakenFoodService {

    private final TakenFoodRepository takenFoodRepository;
    private final TakenFoodDao takenFoodDao;
    private final AccountRepository accountRepository;

    public void save(String currentUserId, Long foodId) {

        Account currentUser = accountRepository.findByUidWithAllergies(currentUserId)
                .orElseThrow(RuntimeException::new);

        TakenFood takenFood = new TakenFood();
        takenFood.init(currentUser.getAccountId(), foodId);

        takenFoodRepository.save(takenFood);
    }

    public void delete(String currentUserId, Long takenFoodId) {
        TakenFood takenFood = takenFoodRepository.findById(takenFoodId)
                .orElseThrow(()-> new RuntimeException("섭취한 식품 정보가 없음"));

        Account account = accountRepository.findByUid(currentUserId)
                .orElseThrow(()-> new RuntimeException("해당 유저가 없음"));

        if(!account.getAccountId().equals(takenFood.getAccountId())) {
            throw new RuntimeException("일치하는 유저가 없음");
        }

        takenFoodRepository.delete(takenFood);
    }

    public List<FetchAllTakenFoodResponseDto> fetchAll(String currentUserId) {
        Account currentUser = accountRepository.findByUidWithAllergies(currentUserId)
                .orElseThrow(RuntimeException::new);
        return takenFoodDao.findAllByAccountId(currentUser.getAccountId());
    }

    public List<DailyTakenFoodServiceDto> fetchDaily(String currentUserId) {
        Account currentUser = accountRepository.findByUidWithAllergies(currentUserId)
                .orElseThrow(RuntimeException::new);

        List<String> userAllergies = currentUser.getAllergies();

        List<DailyTakenFoodDto> dailyTakenFoodDtos = takenFoodDao.findDailyTakenFoodByAccountId(currentUser.getAccountId());
        return dailyTakenFoodDtos.stream()
                .map(dto-> new DailyTakenFoodServiceDto(dto, userAllergies))
                .collect(Collectors.toList());
    }
}
