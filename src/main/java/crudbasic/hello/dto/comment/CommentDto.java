package crudbasic.hello.dto.comment;

import crudbasic.hello.domain.board.Board;
import crudbasic.hello.domain.comment.Comment;
import crudbasic.hello.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentDto {

    private String content;
    private Board board;
    private Member member;

    public static CommentDto toDto(Comment comment) {
        return new CommentDto(comment.getContent(), comment.getBoard(), comment.getMember());
    }
}
