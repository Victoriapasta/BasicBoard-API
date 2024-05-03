package crudbasic.hello.dto.comment;

import crudbasic.hello.domain.board.Board;
import crudbasic.hello.domain.comment.Comment;
import crudbasic.hello.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentRequestDto {

    private Long id;
    private String content;
    private Board board;
    private Member member;

    public CommentRequestDto toDto(Comment comment) {
        return new CommentRequestDto(
                comment.getId(),
                comment.getContent(),
                comment.getBoard(),
                comment.getMember());
    }
}
