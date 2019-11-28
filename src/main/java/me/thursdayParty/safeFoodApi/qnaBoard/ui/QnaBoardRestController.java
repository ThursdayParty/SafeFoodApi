package me.thursdayParty.safeFoodApi.qnaBoard.ui;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import me.thursdayParty.safeFoodApi.qnaBoard.query.dto.FetchDetailQnaBoardServiceResponseDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.thursdayParty.safeFoodApi.qnaBoard.command.QnaBoardService;
import me.thursdayParty.safeFoodApi.qnaBoard.query.FetchQnaBoardService;
import me.thursdayParty.safeFoodApi.qnaBoard.query.dto.FetchAllQnaBoardResponseDto;
import me.thursdayParty.safeFoodApi.qnaBoard.query.dto.FetchDetailQnaBoardResponseDto;
import me.thursdayParty.safeFoodApi.qnaBoard.query.dto.PasswordCheckResponseDto;
import me.thursdayParty.safeFoodApi.qnaBoard.ui.dto.SaveQneBoardRequestDto;
import me.thursdayParty.safeFoodApi.qnaBoard.ui.dto.UpdateQnaBoardRequestDto;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/qnaBoards")
@RequiredArgsConstructor
public class QnaBoardRestController {
	
	private final QnaBoardService service;
	private final FetchQnaBoardService fetchService;
	
	@GetMapping
	public ResponseEntity<List<FetchAllQnaBoardResponseDto>> all() {
		log.info("/api/qnaBoards  Get :: ");
		List<FetchAllQnaBoardResponseDto> list = fetchService.fetchAllQnaBoards();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{boardId}")
	public ResponseEntity<FetchDetailQnaBoardServiceResponseDto> byId(@PathVariable long boardId, Principal principal) {
		log.info("/api/qnaBoards/{}  Get :: ", boardId);

        Optional<String> userId = Optional.empty();
        if (principal != null) {
            userId = Optional.of(principal.getName());
        }

        log.info("userId:: {}", userId);

        FetchDetailQnaBoardServiceResponseDto qnaBoard = fetchService.fetchDetail(boardId, userId);
		return ResponseEntity.ok().body(qnaBoard);
	}
	
	@PostMapping
	public ResponseEntity add(@RequestBody SaveQneBoardRequestDto saveQnaBoardRequestDto, Principal principal) {
		log.info("/api/qnaBoards  POST :: ", saveQnaBoardRequestDto);

		service.save(saveQnaBoardRequestDto, principal.getName());
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{boardId}")
	public ResponseEntity update(@PathVariable long boardId, @RequestBody UpdateQnaBoardRequestDto updateQnaBoardRequestDto) {
		log.info("/api/qnaBoards/"+boardId+"  PUT :: requestDto: {}", updateQnaBoardRequestDto);
		service.update(boardId, updateQnaBoardRequestDto);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{boardId}")
	public ResponseEntity delete(@PathVariable long boardId) {
		log.info("/api/qnaBoards/"+boardId+"  PUT :: ");
		service.delete(boardId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{boardId}/password")
	public ResponseEntity<PasswordCheckResponseDto> checkValidPassword(@PathVariable long boardId, @RequestParam String boardPassword) {
		PasswordCheckResponseDto passwordCheckResponseDto = fetchService.isMatchPassword(boardId, boardPassword);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(passwordCheckResponseDto);
	}
}
