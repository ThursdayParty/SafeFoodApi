package me.thursdayParty.safeFoodApi.TakenFood;

import lombok.RequiredArgsConstructor;
import me.thursdayParty.safeFoodApi.TakenFood.dto.DailyTakenFoodDto;
import me.thursdayParty.safeFoodApi.TakenFood.dto.FetchAllTakenFoodResponseDto;
import me.thursdayParty.safeFoodApi.account.Account;
import me.thursdayParty.safeFoodApi.account.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TakenFoodService {

    private final TakenFoodRepository takenFoodRepository;
    private final TakenFoodDao takenFoodDao;
    private final AccountRepository accountRepository;

    public void save(String currentUserId, Long foodId) {

        Account currentUser = accountRepository.findByUid(currentUserId)
                .orElseThrow(RuntimeException::new);

        TakenFood takenFood = new TakenFood();
        takenFood.init(currentUser.getAccountId(), foodId);

        takenFoodRepository.save(takenFood);
    }

    public List<FetchAllTakenFoodResponseDto> fetchAll(String currentUserId) {
        Account currentUser = accountRepository.findByUid(currentUserId)
                .orElseThrow(RuntimeException::new);
        return takenFoodDao.findAllByAccountId(currentUser.getAccountId());
    }

    public List<DailyTakenFoodDto> fetchDaily(String currentUserId) {
        Account currentUser = accountRepository.findByUid(currentUserId)
                .orElseThrow(RuntimeException::new);
        return takenFoodDao.findDailyTakenFoodByAccountId(currentUser.getAccountId());
    }
}
