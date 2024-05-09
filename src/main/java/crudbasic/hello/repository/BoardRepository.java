package crudbasic.hello.repository;

import crudbasic.hello.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByMemberId(@Param("memberId") Long memberId);
}
