package crudbasic.hello.service;

import crudbasic.hello.domain.board.Board;
import crudbasic.hello.domain.member.Member;
import crudbasic.hello.dto.board.BoardDto;
import crudbasic.hello.utils.exception.BoardNotFoundException;
import crudbasic.hello.utils.exception.MemberNotFoundException;
import crudbasic.hello.repository.BoardRepository;
import crudbasic.hello.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public BoardDto findByBoardId(Long id) {
        return BoardDto.toDto(boardRepository.findById(id).orElseThrow(BoardNotFoundException::new));
    }

    public List<BoardDto> findByUsername(String username) {
        List<Board> boards = boardRepository.findByUsername(username);
        return BoardDto.toListDto(boards);
    }

    @Transactional
    public BoardDto boardSave(BoardDto boardDto, String username) {
        Member member = memberRepository.findByUsername(username).orElseThrow(MemberNotFoundException::new);
        //TODO : 멤버 받은거 이용해서 보드 저장하기
        Board board = boardRepository.save(new Board(boardDto.getTitle(), boardDto.getContent(), boardDto.getMember()));
        return BoardDto.toDto(board);
    }

    @Transactional
    public BoardDto boardUpdate(BoardDto boardDto, Long id) {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        board.updateBoard(boardDto);
        return BoardDto.toDto(board);
    }
}
