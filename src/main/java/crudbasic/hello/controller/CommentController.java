package crudbasic.hello.controller;

import crudbasic.hello.dto.comment.CommentRequestDto;
import crudbasic.hello.dto.comment.CommentResponseDto;
import crudbasic.hello.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{id}")
    public String getCommentById(@PathVariable Long id) {
        CommentResponseDto commentResponseDto = commentService.findByCommentId(id);
        return "-";
    }

    @GetMapping("/{username}")
    public String getCommentByUsername(@PathVariable String username) {
        List<CommentResponseDto> commentResponseDtoList = commentService.findByUsername(username);
        return "-";
    }

    @GetMapping("{boardId}")
    public String getCommentByBoardId(@PathVariable Long boardId) {
        List<CommentResponseDto> commentResponseDtoList = commentService.findByBoardId(boardId);
        return "-";
    }

    @PostMapping
    public String commentSave(@RequestBody CommentRequestDto commentRequestDto) {

        return "-";
    }

    @DeleteMapping
    public String deleteComment(Long id) {
        commentService.deleteComment(id);
        return "-";
    }
}
