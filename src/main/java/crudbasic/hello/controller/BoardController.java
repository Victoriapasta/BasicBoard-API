package crudbasic.hello.controller;

import crudbasic.hello.dto.board.BoardRequestDto;
import crudbasic.hello.dto.board.BoardResponseDto;
import crudbasic.hello.dto.member.MemberResponseDto;
import crudbasic.hello.service.BoardService;
import crudbasic.hello.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> getBoardById(@PathVariable Long id) {
        BoardResponseDto boardResponseDto = boardService.findByBoardId(id);
        return new ResponseEntity<>(boardResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<List<BoardResponseDto>> getBoardByUserId(@PathVariable Long memberId) {
        List<BoardResponseDto> boardResponseDtoList = boardService.findAllByMemberId(memberId);
        return new ResponseEntity<>(boardResponseDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BoardResponseDto> boardSave(@Valid @RequestBody BoardRequestDto boardRequestDto, @RequestParam String username) {
        MemberResponseDto memberResponseDto = memberService.findByUsername(username);
        BoardResponseDto boardResponseDto = boardService.boardSave(memberResponseDto.getId(), boardRequestDto);
        return new ResponseEntity<>(boardResponseDto, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<BoardResponseDto> boardUpdate(@RequestBody BoardRequestDto boardRequestDto, @RequestParam Long id) {
        BoardResponseDto boardResponseDto = boardService.boardUpdate(id, boardRequestDto);
        return new ResponseEntity<>(boardResponseDto, HttpStatus.OK);
    }

    @DeleteMapping
    public void boardDelete(@PathVariable Long id) {
        boardService.deleteBoard(id);
    }
}
