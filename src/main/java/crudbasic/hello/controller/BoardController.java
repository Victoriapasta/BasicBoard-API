package crudbasic.hello.controller;

import crudbasic.hello.dto.board.BoardRequestDto;
import crudbasic.hello.dto.board.BoardResponseDto;
import crudbasic.hello.dto.member.MemberResponseDto;
import crudbasic.hello.service.BoardService;
import crudbasic.hello.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("/{id}")
    public String getBoardById(@PathVariable Long id) {
        BoardResponseDto boardResponseDto = boardService.findByBoardId(id);
        return "-"; //TODO : 맵핑하기
    }

    @GetMapping("/{username}")
    public String getBoardByUserId(@PathVariable String username) {
        List<BoardResponseDto> boardResponseDtoList = boardService.findByUsername(username);
        return "-";
    }

    @PostMapping
    public String boardSave(@RequestBody BoardRequestDto boardRequestDto, @RequestParam String username) {
        MemberResponseDto memberResponseDto = memberService.findByUsername(username);
        BoardResponseDto boardResponseDto = boardService.boardSave(boardRequestDto);
        return "-";
    }

    @PostMapping
    public String boardUpdate(@RequestBody BoardRequestDto boardRequestDto, @RequestParam Long id) {
        BoardResponseDto boardResponseDto = boardService.boardUpdate(boardRequestDto, id);
        return "-";
    }

    @DeleteMapping
    public String boardDelete(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return "-";
    }
}
