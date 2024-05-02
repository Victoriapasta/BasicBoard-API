package crudbasic.hello.controller;

import crudbasic.hello.dto.comment.CommentDto;
import crudbasic.hello.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    @GetMapping("/{id}")
    public String getCommentById(@PathVariable Long id) {
        CommentDto commentDto = commentService.findByCommentId(id);
        return "-";
    }

    @GetMapping("/{username}")
    public String getCommentByUsername(@PathVariable String username) {
        List<CommentDto> commentDtoList = commentService.findByUsername(username);
        return "-";
    }
}
