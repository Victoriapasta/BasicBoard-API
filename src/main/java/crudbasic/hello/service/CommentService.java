package crudbasic.hello.service;

import crudbasic.hello.domain.board.Board;
import crudbasic.hello.domain.comment.Comment;
import crudbasic.hello.domain.member.Member;
import crudbasic.hello.dto.comment.CommentRequestDto;
import crudbasic.hello.dto.comment.CommentResponseDto;
import crudbasic.hello.dto.member.MemberResponseDto;
import crudbasic.hello.repository.BoardRepository;
import crudbasic.hello.utils.exception.BoardNotFoundException;
import crudbasic.hello.utils.exception.CommentNotFoundException;
import crudbasic.hello.utils.exception.MemberNotFoundException;
import crudbasic.hello.repository.CommentRepository;
import crudbasic.hello.repository.MemberRepository;
import crudbasic.hello.utils.validation.CommentOwnerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public CommentResponseDto findByCommentId(Long id) {
        return CommentResponseDto.toDto(commentRepository.findById(id).orElseThrow(CommentNotFoundException::new));
    }

    public List<CommentResponseDto> findAllByUsername(Long memberId) {
        List<Comment> comments = commentRepository.findAllByMemberId(memberId);
        return CommentResponseDto.toListDto(comments);
    }

    public List<CommentResponseDto> findAllByBoardId(Long boardId) {
        List<Comment> comments = commentRepository.findAllByBoardId(boardId);
        return CommentResponseDto.toListDto(comments);
    }

    @Transactional
    public CommentResponseDto commentSave(String username, Long boardId, CommentRequestDto commentRequestDto) {
        Member member = memberRepository.findByUsername(username).orElseThrow(MemberNotFoundException::new);
        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);

        Comment comment = commentRepository.save(new Comment(commentRequestDto.getId(),
                commentRequestDto.getContent(),
                member,
                board));

        return CommentResponseDto.toDto(comment);
    }

    @Transactional
    public CommentResponseDto commentUpdate(Long id, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        comment.updateComment(commentRequestDto);
        return CommentResponseDto.toDto(comment);
    }

    @Transactional
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        commentRepository.delete(comment);
    }
}
