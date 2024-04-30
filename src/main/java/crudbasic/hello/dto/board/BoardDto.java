package crudbasic.hello.dto.board;

import crudbasic.hello.domain.board.Board;
import crudbasic.hello.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardDto {

    private String title;
    private String content;
    private Member member;

    public static BoardDto toDto(Board board) {
        return new BoardDto(board.getTitle(), board.getContent(), board.getMember());
    }
}
