package crudbasic.hello.dto.board;

import crudbasic.hello.domain.board.Board;
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

    public static BoardResponseDto toDto(Board board) {
        return new BoardResponseDto(
                board.getId(),
                board.getTitle(),
                board.getContent());
    }

    public static List<BoardResponseDto> toListDto(List<Board> boards) {
        return boards.stream()
                .map(board -> BoardResponseDto.toDto(board))
                .collect(Collectors.toList());
    }
}
