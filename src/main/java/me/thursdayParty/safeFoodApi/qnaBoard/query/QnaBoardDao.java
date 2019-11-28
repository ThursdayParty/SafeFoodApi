package me.thursdayParty.safeFoodApi.qnaBoard.query;

import java.util.List;

import me.thursdayParty.safeFoodApi.qnaBoard.query.dto.FetchAllQnaBoardResponseDto;
import me.thursdayParty.safeFoodApi.qnaBoard.query.dto.FetchDetailQnaBoardResponseDto;
import me.thursdayParty.safeFoodApi.qnaBoard.query.dto.PasswordCheckResponseDto;

public interface QnaBoardDao {
	List<FetchAllQnaBoardResponseDto> findAll();
	FetchDetailQnaBoardResponseDto findQnaBoardDetailByBoardId(long boardId);
	PasswordCheckResponseDto isMatchPassword(long boardId, String boardPassword);
}
