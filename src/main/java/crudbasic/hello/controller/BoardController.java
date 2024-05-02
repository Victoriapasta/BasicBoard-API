package crudbasic.hello.controller;

import crudbasic.hello.dto.board.BoardDto;
import crudbasic.hello.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private BoardService boardService;

    @GetMapping("/{id}")
    public String getBoardById(@PathVariable Long id) {
        BoardDto boardDto = boardService.findByBoardId(id);
        return "-"; //TODO : 맵핑하기
    }

    @GetMapping("/{username}")
    public String getBoardByUserId(@PathVariable String username) {
        List<BoardDto> boardDtoList = boardService.findByUsername(username);
        return "-";
    }

}
