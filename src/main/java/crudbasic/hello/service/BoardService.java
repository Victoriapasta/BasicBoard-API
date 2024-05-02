package crudbasic.hello.service;

import crudbasic.hello.domain.board.Board;
import crudbasic.hello.domain.member.Member;
import crudbasic.hello.dto.board.BoardDto;
import crudbasic.hello.exception.BoardNotFound;
import crudbasic.hello.exception.MemberNotFound;
import crudbasic.hello.repository.BoardRepository;
import crudbasic.hello.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BoardService {

    private BoardRepository boardRepository;
    private MemberRepository memberRepository;

    public BoardDto findByBoardId(Long id) {
        return BoardDto.toDto(boardRepository.findById(id).orElseThrow(BoardNotFound::new));
    }

    public List<BoardDto> findByUsername(String username) {
        List<Board> boards = boardRepository.findByUsername(username);
        return BoardDto.toListDto(boards);
    }

    @Transactional
    public BoardDto boardSave(BoardDto boardDto, String username) {
        Member member = memberRepository.findByUsername(username).orElseThrow(MemberNotFound::new);
        Board board = boardRepository.save(new Board(boardDto.getTitle(), boardDto.getContent(), boardDto.getMember()));
        return BoardDto.toDto(board);
    }

//    @Transactional TODO: 이거 구현하기
//    public BoardDto boardUpdate(BoardDto boardDto, Long id) {
//
//    }

}
