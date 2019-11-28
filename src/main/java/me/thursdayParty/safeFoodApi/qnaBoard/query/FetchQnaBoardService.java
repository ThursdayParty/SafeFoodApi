package me.thursdayParty.safeFoodApi.qnaBoard.query;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import me.thursdayParty.safeFoodApi.qnaBoard.query.dto.FetchDetailQnaBoardServiceResponseDto;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.thursdayParty.safeFoodApi.qnaBoard.command.QnaBoardService;
import me.thursdayParty.safeFoodApi.qnaBoard.query.dto.FetchAllQnaBoardResponseDto;
import me.thursdayParty.safeFoodApi.qnaBoard.query.dto.FetchDetailQnaBoardResponseDto;
import me.thursdayParty.safeFoodApi.qnaBoard.query.dto.PasswordCheckResponseDto;

@Service
@RequiredArgsConstructor
public class FetchQnaBoardService {

	private final QnaBoardDao dao;
	private final QnaBoardService service;
	
	public List<FetchAllQnaBoardResponseDto> fetchAllQnaBoards() {
		return dao.findAll();
	}

	public FetchDetailQnaBoardServiceResponseDto fetchDetail(long boardId, Optional<String> userId) {
		service.visit(boardId);
        FetchDetailQnaBoardResponseDto boardDetail = dao.findQnaBoardDetailByBoardId(boardId);
        FetchDetailQnaBoardServiceResponseDto boardDetailResponse = new FetchDetailQnaBoardServiceResponseDto(boardDetail, userId);
        return boardDetailResponse;
	}

	public PasswordCheckResponseDto isMatchPassword(long boardId, String boardPassword) {
		return dao.isMatchPassword(boardId, boardPassword);
	}

}
