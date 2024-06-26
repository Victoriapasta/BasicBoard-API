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

    @GetMapping("/boardId/{id}")
    public ResponseEntity<BoardResponseDto> getBoardById(@PathVariable Long id) {
        BoardResponseDto boardResponseDto = boardService.findByBoardId(id);
        return new ResponseEntity<>(boardResponseDto, HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<BoardResponseDto>> getBoardByUsername(@PathVariable String username) {
        List<BoardResponseDto> boardResponseDtoList = boardService.findAllByUsername(username);
        return new ResponseEntity<>(boardResponseDtoList, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> getAllBoards() {
        List<BoardResponseDto> boardResponseDtoList = boardService.findAllBoards();
        return new ResponseEntity<>(boardResponseDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BoardResponseDto> boardSave(@Valid @RequestBody BoardRequestDto boardRequestDto) {
        BoardResponseDto boardResponseDto = boardService.boardSave(boardRequestDto);
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
