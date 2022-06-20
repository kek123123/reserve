package com.shnam.reserve.web.controller;

import com.shnam.reserve.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/new")
    public String boardSaveForm() {
        return "board/boardSaveForm";
    }

    @GetMapping("/board/edit/{id}")
    public String boardUpdateForm(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.findById(id));
        return "board/boardUpdateForm";
    }
}
