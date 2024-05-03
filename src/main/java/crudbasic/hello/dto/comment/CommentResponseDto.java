package crudbasic.hello.dto.comment;

import crudbasic.hello.domain.board.Board;
import crudbasic.hello.domain.comment.Comment;
import crudbasic.hello.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class CommentResponseDto {

    private Long id;
    private String content;
    private Board board;
    private Member member;

    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getBoard(),
                comment.getMember());
    }

    public static List<CommentResponseDto> toListDto(List<Comment> comments) {
        return comments.stream()
                .map(comment -> CommentResponseDto.toDto(comment))
                .collect(Collectors.toList());
    }
}
