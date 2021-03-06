package com.shnam.reserve.service;

import com.shnam.reserve.domain.board.Board;
import com.shnam.reserve.domain.board.BoardRepository;
import com.shnam.reserve.web.dto.BoardListResponseDto;
import com.shnam.reserve.web.dto.BoardResponseDto;
import com.shnam.reserve.web.dto.BoardSaveRequestDto;
import com.shnam.reserve.web.dto.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardSaveRequestDto requestDto) {
        return boardRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, BoardUpdateRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        board.update(requestDto.getTitle(), requestDto.getContent());

        return board.getId();
    }

    @Transactional
    public void delete(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        boardRepository.delete(board);
    }

    public BoardResponseDto findById(Long id) {
        Board entity = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new BoardResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<BoardListResponseDto> findAllDesc() {
        return boardRepository.findAllDesc().stream()
                .map(BoardListResponseDto::new)
                .collect(toList());
    }
}
