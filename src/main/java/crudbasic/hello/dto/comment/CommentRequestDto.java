package crudbasic.hello.dto.comment;

import crudbasic.hello.domain.board.Board;
import crudbasic.hello.domain.comment.Comment;
import crudbasic.hello.domain.member.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentRequestDto {

    private Long id;
    @NotBlank(message = "내용을 입력해주세요.")
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
