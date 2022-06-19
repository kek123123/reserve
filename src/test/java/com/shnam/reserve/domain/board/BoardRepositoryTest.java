package com.shnam.reserve.domain.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void 글저장() throws Exception {

        //given
        String title = "제목";
        String content = "내용";
        String author = "글쓴이";

        boardRepository.save(Board.builder()
                .title(title)
                .content(content)
                .author(author)
                .build());

        //when
        List<Board> boardList = boardRepository.findAll();

        //then
        Board board = boardList.get(0);

        assertEquals(board.getTitle(), title);
        assertEquals(board.getContent(), content);
        assertEquals(board.getAuthor(), author);
    }

    @Test
    public void BaseTiemEntity_등록() throws Exception {

        //given
        LocalDateTime localDateTime = LocalDateTime.of(2022,1,1,0,0,0);

        String title = "제목";
        String content = "내용";
        String author = "글쓴이";

        boardRepository.save(Board.builder()
                .title(title)
                .content(content)
                .author(author)
                .build());

        //when
        List<Board> boardList = boardRepository.findAll();

        //then
        Board board = boardList.get(0);

        assertEquals(true, board.getCreatedDate().isAfter(localDateTime));
        assertEquals(true, board.getModifiedDate().isAfter(localDateTime));
    }
}