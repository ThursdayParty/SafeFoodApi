package me.thursdayParty.safeFoodApi.qnaBoard.query.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FetchDetailQnaBoardResponseDto {
	private Long boardId;
	private String title;
    private String content;
    private String userId;
    private String userName;
	private LocalDateTime createdTime;
	private Long views;
	
}
