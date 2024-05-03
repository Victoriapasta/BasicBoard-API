package crudbasic.hello.dto.board;

import crudbasic.hello.domain.board.Board;
import crudbasic.hello.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardRequestDto {

    private Long id;
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
