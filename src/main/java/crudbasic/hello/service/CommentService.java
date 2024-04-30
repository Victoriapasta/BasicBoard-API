package crudbasic.hello.service;

import crudbasic.hello.dto.comment.CommentDto;
import crudbasic.hello.exception.CommentNotFoundById;
import crudbasic.hello.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentService {

    private CommentRepository commentRepository;

    public CommentDto findByCommentId(Long id) {
        return CommentDto.toDto(commentRepository.findById(id).orElseThrow(CommentNotFoundById::new));
    }

}
