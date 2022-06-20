package com.shnam.reserve.web.controller;

import com.shnam.reserve.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class HomeContoller {

    private final BoardService boardService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("boardList", boardService.findAllDesc());
        return "index";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
