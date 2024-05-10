package crudbasic.hello.controller;

import crudbasic.hello.dto.comment.CommentRequestDto;
import crudbasic.hello.dto.comment.CommentResponseDto;
import crudbasic.hello.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> getCommentById(@PathVariable Long commentId) {
        CommentResponseDto commentResponseDto = commentService.findByCommentId(commentId);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{memberId}/userComments")
    public ResponseEntity<List<CommentResponseDto>> getCommentByMemberId(@PathVariable Long memberId) {
        List<CommentResponseDto> commentResponseDtoList = commentService.findAllByUsername(memberId);
        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{boardId}/boardComments")
    public ResponseEntity<List<CommentResponseDto>> getCommentByBoardId(@PathVariable Long boardId) {
        List<CommentResponseDto> commentResponseDtoList = commentService.findAllByBoardId(boardId);
        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommentResponseDto> commentSave(@Valid @RequestBody CommentRequestDto commentRequestDto,
                                                          @RequestParam String username,
                                                          @RequestParam Long boardId) {
        CommentResponseDto commentResponseDto = commentService.commentSave(username, boardId, commentRequestDto);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> commentUpdate(@Valid @RequestBody CommentRequestDto commentRequestDto, @PathVariable Long commentId) {
        CommentResponseDto commentResponseDto = commentService.commentUpdate(commentId, commentRequestDto);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @DeleteMapping
    public void deleteComment(Long id) {
        commentService.deleteComment(id);
    }
}
