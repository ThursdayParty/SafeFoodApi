package me.thursdayParty.safeFoodApi.qnaBoard.command;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.thursdayParty.safeFoodApi.qnaBoard.command.QnaBoardRepository;
import me.thursdayParty.safeFoodApi.qnaBoard.ui.dto.SaveQneBoardRequestDto;
import me.thursdayParty.safeFoodApi.qnaBoard.ui.dto.UpdateQnaBoardRequestDto;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaBoardService {

	private final QnaBoardRepository repo;

	public void save(SaveQneBoardRequestDto saveQnaBoardRequestDto) {
		repo.save(saveQnaBoardRequestDto.toEntity());
	}

	public void update(long boardId, UpdateQnaBoardRequestDto updateQnaBoardRequestDto) {
		QnaBoard qb = repo.findById(boardId)
				.orElseThrow(RuntimeException::new);
		qb.updateBoard(updateQnaBoardRequestDto);
	}

	public void delete(long boardId) {
		repo.deleteById(boardId);
	}

	public void visit(long boardId) {
		QnaBoard qb = repo.findById(boardId)
				.orElseThrow(RuntimeException::new);
		qb.visit();
	}
}
