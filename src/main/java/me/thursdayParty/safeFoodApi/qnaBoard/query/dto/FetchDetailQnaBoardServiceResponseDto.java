package me.thursdayParty.safeFoodApi.qnaBoard.query.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Bactoria
 * @since 2019-11-28 [2019.11월.28]
 */

@Getter
@NoArgsConstructor
public class FetchDetailQnaBoardServiceResponseDto {
    private Long boardId;
    private String title;
    private String content;
    private String userName;
    private LocalDateTime createdTime;
    private Long views;
    private boolean updatable;
    private boolean deletable;

    public FetchDetailQnaBoardServiceResponseDto(FetchDetailQnaBoardResponseDto dto, Optional<String> curUserId) {
        this.boardId = dto.getBoardId();
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.userName = dto.getUserName();
        this.createdTime = dto.getCreatedTime();
        this.views = dto.getViews();

        if(!curUserId.isPresent() || !curUserId.get().equals(dto.getUserId()))  {
            updatable = false;
            deletable = false;
        } else {
            updatable = true;
            deletable = true;
        }

    }
}
