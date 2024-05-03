package crudbasic.hello.service;

import crudbasic.hello.domain.comment.Comment;
import crudbasic.hello.domain.member.Member;
import crudbasic.hello.dto.comment.CommentRequestDto;
import crudbasic.hello.dto.comment.CommentResponseDto;
import crudbasic.hello.utils.exception.CommentNotFoundException;
import crudbasic.hello.utils.exception.MemberNotFoundException;
import crudbasic.hello.repository.CommentRepository;
import crudbasic.hello.repository.MemberRepository;
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

    public CommentResponseDto findByCommentId(Long id) {
        return CommentResponseDto.toDto(commentRepository.findById(id).orElseThrow(CommentNotFoundException::new));
    }

    public List<CommentResponseDto> findByUsername(String username) {
        List<Comment> comments = commentRepository.findByUsername(username);
        return CommentResponseDto.toListDto(comments);
    }

    public List<CommentResponseDto> findByBoardId(Long boardId) {
        List<Comment> comments = commentRepository.findByBoardId(boardId);
        return CommentResponseDto.toListDto(comments);
    }

    @Transactional
    public CommentResponseDto commentSave(Long boardId, String username, CommentRequestDto commentRequestDto) {
        Member member = memberRepository.findByUsername(username).orElseThrow(MemberNotFoundException::new);
        //TODO: Validation 필요
        Comment comment = commentRepository.save(new Comment(commentRequestDto.getId(),
                commentRequestDto.getContent(),
                commentRequestDto.getMember(),
                commentRequestDto.getBoard()));
        return CommentResponseDto.toDto(comment);
    }

    @Transactional
    public CommentResponseDto commentUpdate(Long id, String username, CommentRequestDto commentRequestDto) {
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
