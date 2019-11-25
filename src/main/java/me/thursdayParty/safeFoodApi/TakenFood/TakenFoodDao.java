package me.thursdayParty.safeFoodApi.TakenFood;

import me.thursdayParty.safeFoodApi.TakenFood.dto.FetchAllTakenFoodResponseDto;
import me.thursdayParty.safeFoodApi.qnaBoard.query.dto.FetchAllQnaBoardResponseDto;
import me.thursdayParty.safeFoodApi.qnaBoard.query.dto.FetchDetailQnaBoardResponseDto;
import me.thursdayParty.safeFoodApi.qnaBoard.query.dto.PasswordCheckResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TakenFoodDao {
    List<FetchAllTakenFoodResponseDto> findAllByAccountId(Long accountId);
}
