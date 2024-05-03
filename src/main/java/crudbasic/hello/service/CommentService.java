package crudbasic.hello.service;

import crudbasic.hello.domain.comment.Comment;
import crudbasic.hello.domain.member.Member;
import crudbasic.hello.dto.comment.CommentDto;
import crudbasic.hello.utils.exception.CommentNotFoundException;
import crudbasic.hello.utils.exception.MemberNotFoundException;
import crudbasic.hello.repository.CommentRepository;
import crudbasic.hello.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CommentService {

    private CommentRepository commentRepository;
    private MemberRepository memberRepository;

    public CommentDto findByCommentId(Long id) {
        return CommentDto.toDto(commentRepository.findById(id).orElseThrow(CommentNotFoundException::new));
    }

    public List<CommentDto> findByUsername(String username) {
        List<Comment> comments = commentRepository.findByUsername(username);
        return CommentDto.toListDto(comments);
    }

    public List<CommentDto> findByBoardId(Long boardId) {
        List<Comment> comments = commentRepository.findByBoardId(boardId);
        return CommentDto.toListDto(comments);
    }

    @Transactional
    public CommentDto commentSave(Long boardId, String username, CommentDto commentDto) {
        Member member = memberRepository.findByUsername(username).orElseThrow(MemberNotFoundException::new);
        //TODO: Validation 필요
        Comment comment = commentRepository.save(new Comment(commentDto.getId(), commentDto.getContent(), commentDto.getMember(), commentDto.getBoard()));
        return CommentDto.toDto(comment);
    }

    @Transactional
    public CommentDto commentUpdate(Long id, String username, CommentDto commentDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        comment.updateComment(commentDto);
        return CommentDto.toDto(comment);
    }
}
