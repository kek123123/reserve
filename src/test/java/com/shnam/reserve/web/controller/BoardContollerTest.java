package com.shnam.reserve.web.controller;

import com.shnam.reserve.domain.board.Board;
import com.shnam.reserve.domain.board.BoardRepository;
import com.shnam.reserve.web.dto.BoardSaveRequestDto;
import com.shnam.reserve.web.dto.BoardUpdateRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BoardContollerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @WithMockUser(roles = "USER")
    public void 글등록() throws Exception {
        //given
        String title = "제목";
        String content = "내용";
        String author = "글쓴이";

        BoardSaveRequestDto boardSaveRequestDto = BoardSaveRequestDto.builder()
                                                                    .title(title)
                                                                    .content(content)
                                                                    .author(author)
                                                                    .build();
        String url = "http://localhost:" + port + "/api/board";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, boardSaveRequestDto, Long.class);

        //then
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        List<Board> boardList = boardRepository.findAll();
        Board board = boardList.get(0);

        assertEquals(board.getTitle(), title);
        assertEquals(board.getContent(), content);
        assertEquals(board.getAuthor(), author);
    }

    @Test
    public void 글수정() throws Exception {
        //given
        String title = "제목";
        String content = "내용";
        String author = "글쓴이";

        Board board = boardRepository.save(Board.builder()
                                    .title(title)
                                    .content(content)
                                    .author(author)
                                    .build());

        Long updateId = board.getId();
        String updateTitle = "수정할 제목";
        String updateContent = "수정할 내용";

        BoardUpdateRequestDto requestDto = BoardUpdateRequestDto.builder()
                                                                .title(updateTitle)
                                                                .content(updateContent)
                                                                .build();
        HttpEntity<BoardUpdateRequestDto> httpEntity = new HttpEntity<>(requestDto);

        String url = "http://localhost:" + port + "/api/board/" + updateId;

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Long.class);

        //then
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        List<Board> boardList = boardRepository.findAll();
        Board updatedBoard = boardList.get(0);

        assertEquals(updatedBoard.getTitle(), updateTitle);
        assertEquals(updatedBoard.getContent(), updateContent);
    }
}