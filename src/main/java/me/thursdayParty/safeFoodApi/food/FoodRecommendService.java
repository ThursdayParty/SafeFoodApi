package me.thursdayParty.safeFoodApi.food;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

/**
 * @author Bactoria
 * @since 2019-11-26 [2019.11월.26]
 */

@Service
@Transactional
@RequiredArgsConstructor
public class FoodRecommendService {

    private final FoodRepository repo;

    public void recommend(Long foodId) {
        Food food = repo.findById(foodId)
                .orElseThrow(EntityNotFoundException::new);

        food.recommend();
    }
}
