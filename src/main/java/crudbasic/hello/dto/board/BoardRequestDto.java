package crudbasic.hello.dto.board;

import crudbasic.hello.domain.board.Board;
import crudbasic.hello.domain.member.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardRequestDto {

    private Long id;
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    private String content;
    private Member member;

    public static BoardRequestDto toDto(Board board) {
        return new BoardRequestDto(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getMember());
    }
}
