package me.thursdayParty.safeFoodApi.TakenFood;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TakenFoodRepository extends JpaRepository<TakenFood, Long> {
}
