package crudbasic.hello.controller;

import crudbasic.hello.dto.board.BoardDto;
import crudbasic.hello.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    private BoardService boardService;

    @GetMapping("/{id}")
    public String getBoardById(@PathVariable Long id) {
        BoardDto boardDto = boardService.findByBoardId(id);
        return "-"; //TODO : 맵핑하기
    }
}
