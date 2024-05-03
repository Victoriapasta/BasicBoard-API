package crudbasic.hello.dto.board;

import crudbasic.hello.domain.board.Board;
import crudbasic.hello.domain.member.Member;
import crudbasic.hello.repository.BoardRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class BoardDto {

    private Long id;
    private String title;
    private String content;
    private Member member;

    public static BoardDto toDto(Board board) {
        return new BoardDto(board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getMember());
    }

    public static List<BoardDto> toListDto(List<Board> boards) {
        return boards.stream()
                .map(b -> BoardDto.toDto(b))
                .collect(Collectors.toList());
    }
}
