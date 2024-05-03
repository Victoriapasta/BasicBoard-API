package crudbasic.hello.domain.board;

import crudbasic.hello.domain.member.Member;
import crudbasic.hello.dto.board.BoardDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public Board(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public void updateBoard(BoardDto boardDto) {
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
    }
}
