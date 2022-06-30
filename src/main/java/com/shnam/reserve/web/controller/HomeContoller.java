package com.shnam.reserve.web.controller;

import com.shnam.reserve.config.auth.LoginUser;
import com.shnam.reserve.config.auth.dto.SessionUser;
import com.shnam.reserve.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class HomeContoller {

    private final BoardService boardService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {

        model.addAttribute("boardList", boardService.findAllDesc());

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }
}
