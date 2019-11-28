package me.thursdayParty.safeFoodApi.qnaBoard.query.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FetchAllQnaBoardResponseDto {
	private Long boardId;
	private String title;
	private String userName;
	private LocalDateTime createdTime;
	private Long views;
}
