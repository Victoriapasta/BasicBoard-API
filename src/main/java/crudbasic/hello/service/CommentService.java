package crudbasic.hello.service;

import crudbasic.hello.domain.comment.Comment;
import crudbasic.hello.domain.member.Member;
import crudbasic.hello.dto.comment.CommentDto;
import crudbasic.hello.exception.CommentNotFound;
import crudbasic.hello.exception.MemberNotFound;
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
        return CommentDto.toDto(commentRepository.findById(id).orElseThrow(CommentNotFound::new));
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
        Member member = memberRepository.findByUsername(username).orElseThrow(MemberNotFound::new);
        Comment comment = commentRepository.save(new Comment(commentDto.getContent(), commentDto.getMember(), commentDto.getBoard()));
        return CommentDto.toDto(comment);
    }
}
