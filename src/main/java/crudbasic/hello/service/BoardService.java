package crudbasic.hello.service;

import crudbasic.hello.domain.board.Board;
import crudbasic.hello.dto.board.BoardDto;
import crudbasic.hello.dto.member.MemberDto;
import crudbasic.hello.exception.BoardNotFoundById;
import crudbasic.hello.repository.BoardRepository;
import crudbasic.hello.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BoardService {

    private BoardRepository boardRepository;
    private MemberRepository memberRepository;

    public BoardDto findByBoardId(Long id) {
        return BoardDto.toDto(boardRepository.findById(id).orElseThrow(BoardNotFoundById::new));
    }

}
