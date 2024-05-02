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
public class CommentDto {

    private String content;
    private Board board;
    private Member member;

    public static CommentDto toDto(Comment comment) {
        return new CommentDto(comment.getContent(), comment.getBoard(), comment.getMember());
    }

    public static List<CommentDto> toListDto(List<Comment> comments) {
        return comments.stream()
                .map(comment -> CommentDto.toDto(comment))
                .collect(Collectors.toList());
    }
}
