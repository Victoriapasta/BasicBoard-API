package crudbasic.hello.domain.comment;

import crudbasic.hello.domain.board.Board;
import crudbasic.hello.domain.member.Member;
import crudbasic.hello.dto.comment.CommentDto;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;

    @Column(name = "CONTENT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    public Comment(String content, Member member, Board board) {
        this.content = content;
        this.member = member;
        this.board = board;
    }

    public Comment() {

    }

    public static Comment toEntity(CommentDto commentDto) {
        return new Comment(commentDto.getContent(), commentDto.getMember(), commentDto.getBoard());
    }
}
