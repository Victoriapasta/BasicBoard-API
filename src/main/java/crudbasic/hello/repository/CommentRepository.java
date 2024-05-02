package crudbasic.hello.repository;

import crudbasic.hello.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByUsername(@Param("username") String username);

    List<Comment> findByBoardId(@Param("boardId") Long boardId);
}
