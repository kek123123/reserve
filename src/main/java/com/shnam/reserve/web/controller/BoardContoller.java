package com.shnam.reserve.web.controller;

import com.shnam.reserve.service.BoardService;
import com.shnam.reserve.web.dto.BoardResponseDto;
import com.shnam.reserve.web.dto.BoardSaveRequestDto;
import com.shnam.reserve.web.dto.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardContoller {

    private final BoardService boardService;

    @PostMapping("/board")
    public Long save(@RequestBody BoardSaveRequestDto requestDto) {
        return boardService.save(requestDto);
    }

    @PutMapping("/board/{id}")
    public Long update(@PathVariable Long id, @RequestBody BoardUpdateRequestDto requestDto) {
        return boardService.update(id, requestDto);
    }

    @GetMapping("board/{id}")
    public BoardResponseDto findById(@PathVariable Long id) {
        return boardService.findById(id);
    }
}
