package com.shnam.reserve.web.dto;

import com.shnam.reserve.domain.board.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {

    private String title;
    private String content;
    private String author;

    public BoardResponseDto(Board entity) {
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
