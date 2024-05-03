package crudbasic.hello.dto.board;

import crudbasic.hello.domain.board.Board;
import crudbasic.hello.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;
    private Member member;

    public static BoardResponseDto toDto(Board board) {
        return new BoardResponseDto(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getMember());
    }

    public static List<BoardResponseDto> toListDto(List<Board> boards) {
        return boards.stream()
                .map(b -> BoardResponseDto.toDto(b))
                .collect(Collectors.toList());
    }
}
