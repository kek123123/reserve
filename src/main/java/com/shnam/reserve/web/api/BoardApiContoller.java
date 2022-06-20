package com.shnam.reserve.web.api;

import com.shnam.reserve.service.BoardService;
import com.shnam.reserve.web.dto.BoardResponseDto;
import com.shnam.reserve.web.dto.BoardSaveRequestDto;
import com.shnam.reserve.web.dto.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardApiContoller {

    private final BoardService boardService;

    @PostMapping("/api/board")
    public Long save(@RequestBody BoardSaveRequestDto requestDto) {
        return boardService.save(requestDto);
    }

    @PutMapping("/api/board/{id}")
    public Long update(@PathVariable Long id, @RequestBody BoardUpdateRequestDto requestDto) {
        return boardService.update(id, requestDto);
    }

    @GetMapping("/api/board/{id}")
    public BoardResponseDto findById(@PathVariable Long id) {
        return boardService.findById(id);
    }
}
