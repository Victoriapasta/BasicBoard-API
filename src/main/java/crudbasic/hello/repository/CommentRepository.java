package crudbasic.hello.repository;

import crudbasic.hello.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByMemberId(@Param("memberId") Long memberId);

    List<Comment> findAllByBoardId(@Param("boardId") Long boardId);
}
